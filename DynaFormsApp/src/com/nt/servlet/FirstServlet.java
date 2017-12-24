package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String name=null,fname=null,ms=null;
		//general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read  form1/req1 data
		 name=req.getParameter("name");
		 fname=req.getParameter("fname");
		 ms=req.getParameter("ms");
		 //handle unselected state of checkBox
		  if(ms==null)
			  ms="single";
		  //Generate form2 dynamically form here...
		  if(ms.equalsIgnoreCase("married")){
			  pw.println("<h1 style='color:red'>Marriage Life details </h1>");
			  pw.println("<form action='secondurl' method='POST'>");
			  pw.println("Spouse name:: <input type='text' name='f2t1'><br>");
			  pw.println("No.of kids:: <input type='text' name='f2t2'><br>");
			  pw.println("<input type='submit' value='register'>");
			  pw.println("</form>");
		  }
		  else{
			  pw.println("<h1 style='color:red'>Bachelor Life Details </h1>");
			  pw.println("<form action='secondurl' method='POST'>");
			  pw.println("When do u want to marry:: <input type='text' name='f2t1'><br>");
			  pw.println("Why do u want to marry:::: <input type='text' name='f2t2'><br>");
			  pw.println("<input type='submit' value='register'>");
			  pw.println("</form>"); 
		  }
		 //close stream 
		  pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}

}
