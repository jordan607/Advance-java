package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
   private static final String  INSERT_TAX_TAB="INSERT INTO TAX_TAB VALUES(?,?,?,?)";
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		int income=0,tax=0;
		String name=null,fname=null;
		Cookie cookies[]=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form2/req2 data
		income=Integer.parseInt(req.getParameter("income"));
		tax=Integer.parseInt(req.getParameter("tax"));
		//read form1/req1 data from cookies (Session tracking)
		cookies=req.getCookies();
		if(cookies!=null){
			name=cookies[0].getValue();
			fname=cookies[1].getValue();
		}
		//write form1/req1 data and form2/req2 data to DB table as record
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			ps=con.prepareStatement(INSERT_TAX_TAB);
			//set values to query params
			ps.setString(1, name);
			ps.setString(2,fname);
			ps.setInt(3,income);
			ps.setInt(4,tax);
			//execute the query
			result=ps.executeUpdate();
			//process the Result
			if(result==0)
				pw.println("<h1 style='color:red;text-align:center'>Record Not inserted </h1>");
			else
				pw.println("<h1 style='color:red;text-align:center'>Record  inserted </h1>");
	    	}//try
		   catch(SQLException se){
			   se.printStackTrace();
		   }
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc objs
			try{
				if(ps!=null)
					ps.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}//finally
		
			//Display form1/req1 and form2/req2 data .. 
			pw.println("<br><b> form1/req1 data ::</b>"+name+" ....."+fname);
			pw.println("<br><b> form2/req2 data:: "+income+"...."+tax);
			//add hyperlink
			pw.println("<br><a href='form.html'>home </a>");
			//close stream
			pw.close();
		}//doGet(-,-)
	
	@Override
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
		}//doPost(-,-)
	}//class
