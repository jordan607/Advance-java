package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value="/errurl",name="err")
public class ErrorServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ErrorServlet:: doGet(-,-)");
	    PrintWriter pw=null;
	    RequestDispatcher rd1=null,rd2=null;
	    //general settings
	    	pw=res.getWriter();
	    	res.setContentType("text/html");
	    	//include header content 
			rd1=req.getRequestDispatcher("/headurl");
			//rd1.include(req,res);
	     //Display Error  message
	    	pw.println("<h1 style='color:red;text-align:center'> Internal Problem </h1>");
	    	pw.println("<a href='input.html'>Try Again </a>");
	    	//include footer content 
			rd2=req.getRequestDispatcher("/footer.html");
			//rd2.include(req,res);
	    	//close stream 
	    	pw.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
