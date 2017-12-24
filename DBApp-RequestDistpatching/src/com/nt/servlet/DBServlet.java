//DBServlet.java (Approach2)
package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

import java.sql.*;


public class DBServlet extends HttpServlet
{	private static final String GET_EMP_DETAILS_BY_NO="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO=?";
	
public void doGet(HttpServletRequest req,
		                             HttpServletResponse res)throws ServletException,IOException{
		 PrintWriter pw=null;
		 int no=0;
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 ServletConfig cg=null;
		 String driver=null,url=null,dbuser=null,dbpwd=null;
		 RequestDispatcher rd=null,rd1=null,rd2=null;
		 ServletContext sc=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		try{
		 //include header content
			rd1=req.getRequestDispatcher("/headurl");
			rd1.include(req,res);
         //read form data
		 no=Integer.parseInt(req.getParameter("teno"));
		  //JDBC code
		 //get Access to ServletConfig obj
		 cg=getServletConfig();
		 driver=cg.getInitParameter("driver");
		 url=cg.getInitParameter("url");
		 dbuser=cg.getInitParameter("dbuser");
		 dbpwd=cg.getInitParameter("dbpwd");
		 //register driver class
		 Class.forName(driver);
		 //establihs the connection
          con=DriverManager.getConnection(url,dbuser,dbpwd);
		  //create PreparedStatement   object
		  ps=con.prepareStatement(GET_EMP_DETAILS_BY_NO);
		  //set values to query params
		  ps.setInt(1,no);
		  //execute the Query
		  rs=ps.executeQuery();
		  //process the ResultSet
		  if(rs.next()){
             pw.println("<h1 style='color:red;text-align:center'>EmpDetails are </h1>");
			 pw.println("<br><b> Emp number::"+rs.getInt(1)+"</b>"); 
			 pw.println("<br><b> Emp name::"+rs.getString(2)+"</b>"); 
			 pw.println("<br><b> Emp Desg::"+rs.getString(3)+"</b>"); 
			 pw.println("<br><b> Emp Salary :: "+rs.getInt(4)+"</b>");
		  }//if
		  else{
		  pw.println("<h1 style='color:red;text-align:center'> Records not found </h1><br>");
		  }
		  pw.println("<br><a href='input.html'>home </a><br>");
		  
			 //include header content
			rd2=req.getRequestDispatcher("/footer.html");
			pw.close();
			rd2.include(req,res);
		}//try
		catch(Exception e){
		   rd=req.getRequestDispatcher("/errurl");
           	 rd.forward(req,res);
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
		}//doGet(-,-)

		public void  doPost(HttpServletRequest req,
			                               HttpServletResponse res)throws ServletException,IOException{
			  doGet(req,res);
		}
}//class