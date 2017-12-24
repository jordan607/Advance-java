package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
   
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String name=null,fname=null;
		 Cookie cookie1=null,cookie2=null;
		//general settings
		 pw=res.getWriter();
		 //set response contentn type
		 res.setContentType("text/html");
		 //read form1/req1 data
		 name=req.getParameter("name");
		 fname=req.getParameter("fname");
		 //create Two In-Memory Cookies having form1/req1 data
		 cookie1=new Cookie("name",name);
		 cookie2=new Cookie("fname",fname);
		 //add cookies to response
		 res.addCookie(cookie1); res.addCookie(cookie2);
		 //generate form2 dynamically
		 pw.println("<h1 style='color:red;text-align:center'>Provide IncomeTax Details </h1>");
		 pw.println("<form action='secondurl' method='POST'>");
		 pw.println("Income of this Year:: <input type='text' name='income'><br>");
		 pw.println("Tax of this Year:: <input type='text' name='tax'><br>");
		 pw.println("<input type='submit' value='register'>");
		 pw.println("</form>");
		 //close stream
		 pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}//calss
