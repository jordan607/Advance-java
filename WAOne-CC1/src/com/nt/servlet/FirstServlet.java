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

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      PrintWriter pw=null;
      int val=0;
      int square=0;
      ServletContext sc1=null;
      ServletContext sc2=null;
      RequestDispatcher rd=null;
		//general settings
      pw=res.getWriter();
      res.setContentType("text/html");
      //read form data
      val=Integer.parseInt(req.getParameter("t1"));
      square=val*val;
      pw.println("<h1>FirstServlet ::: Square Value::: "+square+"</h1>");
      //get ServletContext obj of current web application(WAOne)
      sc1=getServletContext();
    //get ServletContext obj of remote web application(WATwo)
      sc2=sc1.getContext("/WATwo-CC2");
      //create RequestDispatcher object
      rd=sc2.getRequestDispatcher("/secondurl");
      //include the response
      rd.include(req,res);
      //close stream 
      pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}

}
