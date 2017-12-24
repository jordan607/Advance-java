package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	private static final String  INSERT_QUERY="INSERT INTO HIDDEN_PERSON_TAB VALUES(?,?,?,?,?)";
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String name=null,fname=null,ms=null;
		 String f2val1=null,f2val2=null;
		 Connection con=null;
		 PreparedStatement ps=null;
		 int count=0;
		//general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read  form1/req1 data
		 name=req.getParameter("hname");
		 fname=req.getParameter("hfname");
		 ms=req.getParameter("hms");
		 //read form2/req2 data
		 f2val1=req.getParameter("f2t1");
		 f2val2=req.getParameter("f2t2");
		 //write form1/req1 and form2/req2 data to Db table as record
		 try{
			 //register jdbc driver
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //Establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create PreparedStatement object
			 ps=con.prepareStatement(INSERT_QUERY);
			 //set vlaues to Query params
			 ps.setString(1,name);
			 ps.setString(2,fname);
			 ps.setString(3,ms);
			 ps.setString(4, f2val1);
			 ps.setString(5,f2val2);
			 //execute Query
			 count=ps.executeUpdate();
			 //process the result
			 if(count==0)
				 pw.println("<h1> Registration failed </h1>");
			 else
				 pw.println("<h1> Registration succeded </h1>");
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
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			 try{
				 if(con!=null)
					 con.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
		 }//finally
		 
		 //display form1/req1 and form2/req2 data
		 pw.println("<br> form1/req1 data::"+name+"...."+fname+"...."+ms+"<br>");
		 pw.println("<br> form2/req2 data::"+f2val1+"...."+f2val2);
		 
		 pw.println("<br> <a href='details.html'>home</a>");
		 //close stream 
		  pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}

}
