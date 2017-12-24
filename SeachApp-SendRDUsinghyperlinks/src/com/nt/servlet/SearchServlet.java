package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchurl")
public class SearchServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String ss=null;
	   //general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read form data
		 ss=req.getParameter("search");
		 //Send hyperlink having Google url..
		 pw.println("<h1><a href='https://www.google.co.in/search?q="+ss+"'>Go to Google </a>");
		//close stream
		 pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}//doPost(-,-)
}//class
