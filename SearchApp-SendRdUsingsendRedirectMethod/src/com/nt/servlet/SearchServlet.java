package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
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
		String url=null;
		String engg=null;
		RequestDispatcher rd=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		//ss=URLEncoder.encode(req.getParameter("ss"));
		ss=req.getParameter("ss");
		engg=req.getParameter("engine");
		//prepare url for sendRedirection
		if(engg.equalsIgnoreCase("google"))
			url="https://www.google.co.in/search?q="+ss;
		else if(engg.equalsIgnoreCase("bing"))
			url="https://www2.bing.com/search?q="+ss;
		else 
			url="https://in.search.yahoo.com/search?p="+ss;
		//perform send Direction
		System.out.println("SerachServlet:: before res.sendRedirect(-)");
		pw.println("<b>Before res.sendRedirect(-) method"); 
		/*rd=req.getRequestDispatcher("/myHome.html");
		rd.include(req, res);*/
		res.sendRedirect(url);
		//res.sendRedirect("myHome.html");
		
		System.out.println("SerachServlet::after res.sendRedirect(-)");
		pw.println("<b>After res.sendRedirect(-) method");
	}///doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
