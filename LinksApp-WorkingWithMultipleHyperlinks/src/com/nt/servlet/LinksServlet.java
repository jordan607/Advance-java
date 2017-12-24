package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LinksServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String pval=null;
		Locale locales[]=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read p1 additional req param value
		pval=req.getParameter("p1");
		//write logics for hyperlinks
		locales=Locale.getAvailableLocales();
		if(pval.equalsIgnoreCase("link1")){
			pw.println("<h1 style='color:red'>All Counties </h1>");
			for(Locale locale:locales){
				pw.println("<br>"+locale.getDisplayCountry());
			}
		}
		else if(pval.equalsIgnoreCase("link2")){
			pw.println("<h1 style='color:red'>All Languages </h1>");
			for(Locale locale:locales){
				pw.println("<br>"+locale.getDisplayLanguage());
			}
		}
		else{
			pw.println("<h1 style='color:red;text-align:center'>Date and time ::"+new Date()+"</h1>");
		}
		//add hyperlink
		pw.println("<br><a href='links.html'>home</a>");
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}
	

}
