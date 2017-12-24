package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 Cookie  cookies[]=null;
		//general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read all cookies
		 cookies=req.getCookies();
		 //display cookie details
		 pw.println("<h1> Cookie Details are </h1>");
		 pw.println("<table border='1'>");
		 for(Cookie ck:cookies){
			 pw.println("<tr>");
			 pw.println("<td>"+ck.getName()+"</td>");
			 pw.println("<td>"+ck.getValue()+"</td>");
			 pw.println("</tr>");
		 }
		 pw.println("</table>");
			 //close stream
		 pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}

}
