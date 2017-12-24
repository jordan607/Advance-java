package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/s2url")
public class Servlet2 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd=null;
		PrintWriter pw=null;
		HttpSession ses=null;
		ServletContext sc=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read and display request attribute value.
		pw.println("<br>Servlet2:: request attribute value ::"+req.getAttribute("attr1"));
		//read and display Session attribute value
		ses=req.getSession();
		pw.println("<br>Servlet2:: session attribute value ::"+ses.getAttribute("attr2"));
		//read and disply ServletCotext attribute value
		 sc=getServletContext();
		 pw.println("<br>Servlet2:: ServletContext attribute value ::"+sc.getAttribute("attr3"));
		//forward request to Servlet3
		rd=req.getRequestDispatcher("/s3url");
		rd.forward(req,res);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   doGet(req,res);
	}

}
