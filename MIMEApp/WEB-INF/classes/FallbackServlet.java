//HtmlServlet.java
package com.nt.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class   FallbackServlet extends HttpServlet
{
	public void service(HttpServletRequest  req,HttpServletResponse res)throws ServletException,IOException{
		 PrintWriter pw=null;
		 //get PrintWrtier 
		 pw=res.getWriter();
		 //set response content type
		 res.setContentType("text/html");
		 //writer output to response object
		 pw.println("<h1> welcome MIME App Web application </h1>");
		 pw.println("<br><br><h1>No Virtual path or wrong virtual path is given in the request url, please check </h1>");
		 //close stream
		 pw.close();
	}//service(-,-)
}//class
