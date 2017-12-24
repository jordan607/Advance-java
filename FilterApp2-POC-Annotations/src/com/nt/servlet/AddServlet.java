package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/addurl",name="add",loadOnStartup=1)
public class AddServlet extends HttpServlet {
	
	static{
		System.out.println("AddServlet::static block");
	}
	
	 public AddServlet() {
		System.out.println("AddServlet:0-param constructor");
	}
	
	 @Override
	public void init() {
	  System.out.println("AddServlet:: init()");
	}
	 
	 @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 System.out.println("AddServlet:: doGet(-,-)");
		 PrintWriter pw=null;
		 int val1=0,val2=0;
		 //general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read form data
		 val1=Integer.parseInt(req.getParameter("t1"));
		 val2=Integer.parseInt(req.getParameter("t2"));
		 //pperform  addition
		 pw.println("<h1 style='color:red;text-align:center'>Sum is :: "+(val1+val2)+"</h1>");
		 //add hyperlink
		 pw.println("<br><a href='form.html'>home</a>");
		 //close stream
		 pw.close();
	}//doGet(-,-)
	 @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 System.out.println("AddServlet:: doPost(-,-)");
		 doGet(req,res);
	}
	 
	 @Override
	public void destroy() {
	   System.out.println("AddServlet::destroy()");
	}
	 
	 

}
