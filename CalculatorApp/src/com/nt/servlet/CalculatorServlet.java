package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		ServletOutputStream sos=null;
		String pval=null;
		int val1=0,val2=0;
		//general settings
		sos=res.getOutputStream();
		//pw=res.getWriter();
		
		//set response content type
		res.setContentType("text/html");
		//read additonal req param value(s1)
		pval=req.getParameter("s1");
		//read form data only when sumbit Buttons are clicked
		if(!pval.equals("link1") && !pval.equals("link2")){
			val1=Integer.parseInt(req.getParameter("t1"));
			val2=Integer.parseInt(req.getParameter("t2"));
		}
		//write req processing logic
		if(pval.equalsIgnoreCase("add")){
			sos.println("<h1>Add :::"+(val1+val2)+"</h1>");
		}
		else if(pval.equals("sub")){
			sos.println("<h1>Sub :::"+(val1-val2)+"</h1>");
		}
		else if(pval.equals("mul")){
			sos.println("<h1>Mul :::"+(val1*val2)+"</h1>");
		}
		else if(pval.equals("link1")){
			sos.println("<h1>System Propeties::"+System.getProperties()+"</h1>");
		}
		else{
			sos.println("<h1>System Date::"+new java.util.Date()+"</h1>");
		}
		
		//add hyperlink
		sos.println("<a href='form.html'>home</a>");
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}//class
