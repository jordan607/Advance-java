package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckMarriageElgibilityServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,gender=null;
		int age=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		name=req.getParameter("pname");
		age=Integer.parseInt(req.getParameter("page"));
		gender=req.getParameter("gender");
		//write b.logic or request processing logic
		if(gender.equalsIgnoreCase("M")){
			if(age<21){
				pw.println("<h1 style='color:red;text-align:center'>Mr."+name+" u r not elgible to marray(enjoy freedom)</h1>");
			}
			else{
				pw.println("<h1 style='color:orange;text-align:center'>Mr."+name+" u r  elgible to marray, But think twice</h1>");
			}
		}
		else{
			if(age<18){
				pw.println("<h1 style='color:red;text-align:center'>Miss."+name+" u r not elgible to marray </h1>");
			}
			else{
				pw.println("<h1 style='color:orange;text-align:center'>Miss."+name+" u r  elgible to marray</h1>");
			}
		}
		//add hyperlink
		pw.println("<br> <a href='input.html'>home</a>");
		pw.println("<br> request obj class name::"+req.getClass());
		pw.println("<br> response obj class name::"+res.getClass());
		//close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
	

	
}//class
