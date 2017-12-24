package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCapitalServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		int country=0;
		String capitals[]=new String[]{"","New Delhi","Islamabad","pyongyang","seoul"};
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		country=Integer.parseInt(req.getParameter("country"));
		//Display capital name
		if(country==0)
			pw.println("<h1 style='color:red'>SElect Propert country</h1>");
		else{
			pw.println("<h1>Capital name::"+capitals[country]+"</h1>");
		}
		//add hyperlink
		pw.println("<a href='input.html'>home</a>");
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   doGet(req,res);
	}//doPost(-,-)

}
