//DateServlet.java
package com.nt.servlet;

import javax.servlet.*;  //servlet api
import java.io.*;  // IOStreams
import java.util.*;

public class DateServlet extends GenericServlet
{

public  DateServlet(){
  	System.out.println("DateServlet::0-param constructor");
}

public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
	  System.out.println("DateServlet::service(-,-) method");
        PrintWriter pw=null;      
		Date d=null;
	   //set response content type instruct the browser to display web page in a format
	   res.setContentType("text/html");
	   //get PrintWrtier Stream
	   pw=res.getWriter();
	   //write request processing logic 
	   d=new Date();
	   //write output to response using stream
	   pw.println("<h1 style='color:blue;text-align:center'> Date and time :::"+d+"</h1>");
	   pw.println("<br><b> req obj class name:::"+req.getClass()+"</b>");
	   pw.println("<br><b> res obj class name:::"+res.getClass()+"</b>");

	    res.setHeader("refresh","10");

	   //close stream  (commits the response)
	   pw.close();

	}//service(-,-)
}//class