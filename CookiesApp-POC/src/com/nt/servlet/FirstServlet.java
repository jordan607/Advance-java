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
		 Cookie ck1=null,ck2=null,ck3=null,ck4=null;
		//general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //create cookies
		 ck1=new Cookie("TS","hyd");
		 ck2=new Cookie("AP","Amarvathi");
		 res.addCookie(ck1); res.addCookie(ck2); // As InMemory Cookies
		 ck3=new Cookie("MH","Mumbai");
		 ck4=new Cookie("MP","Bhoal");
		 ck3.setMaxAge(120);
		 ck4.setMaxAge(60);
		 res.addCookie(ck3); res.addCookie(ck4);
		 pw.println("<h1> Cookies are added successfully </h1>");
		 //close stream
		 pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}

}
