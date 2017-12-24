package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		int val=0;
		int cube=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		val=Integer.parseInt(req.getParameter("t1"));
		//calculate cube value..
		cube=val*val*val;
		pw.println("<br><h1> Cube value :: "+cube+"</h1>");
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}//class
