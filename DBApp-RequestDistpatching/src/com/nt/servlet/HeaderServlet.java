package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/headurl")
public class HeaderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		//general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //place header logic
		 pw.println("<h1 style='color:red'><marquee> N A R E S H Technologies </marquee></h1>");
		 //do not close stream
		  //pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)

}//class
