package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/poolurl")
public class PoolServlet extends HttpServlet {
	
	@Resource(name="DsJndi")
	private DataSource ds;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String tabName=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		int colCount=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		tabName=req.getParameter("table");
		try{
		//get Jdbc con obj from jdbc con pool
		//con=makeConnection();
			con=ds.getConnection();
		//create Jdbc statement object
		st=con.createStatement();
		//send and execute SQL Query in DB s/w
		rs=st.executeQuery("SELECT * FROM   "+tabName);
		//get Column count and display col names
		rsmd=rs.getMetaData();
		colCount=rsmd.getColumnCount();
		pw.println("<table border='1' bgcolor='red'>");
		pw.println("<tr>");
		for(int i=1;i<=colCount;++i){
			pw.println("<th>"+rsmd.getColumnLabel(i)+"</th>");
		}
		pw.println("</tr>");
		//process the ResultSet object
		while(rs.next()){
		  pw.println("<tr>");	
			for(int i=1;i<=colCount;++i){
				pw.println("<td>"+rs.getString(i)+"</td>");
			}//for
			pw.println("</tr>");
		}//while
		pw.println("</table>");
		}//try
		catch(SQLException se){
			se.printStackTrace();
			pw.println("<h1> DB table not found or Internal Problem </h1>");
		}
		catch(Exception e){
			e.printStackTrace();
			pw.println("<h1> DB table not found or Internal Problem </h1>");
		}
		finally{
			//close jdbc objs
			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();  //returns the jdbc con obj back jdbc con pool
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}//finally
		pw.println("<a href='index.html'>home</a>");
		//clos stream
		pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/*private Connection makeConnection()throws Exception{
		Connection con=null;
		InitialContext ic=null;
		DataSource ds=null;
		//create InitialContext pointing to Jndi Registry
		ic=new InitialContext();
		//get DataSource obj ref from Jndi registry
		//ds=(DataSource)ic.lookup("DsJndi");  //for any server
		ds=(DataSource)ic.lookup("java:/comp/env/DsJndi"); //for tomcat server
		//get Connection obj from jdbc con pool
		con=ds.getConnection();
		return con;
	}//method
*/
}
