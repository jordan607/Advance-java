//HtmlServlet.java
package com.nt.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class   HtmlServlet extends HttpServlet
{
	public void service(HttpServletRequest  req,HttpServletResponse res)throws ServletException,IOException{
		 PrintWriter pw=null;
		 //get PrintWrtier 
		 pw=res.getWriter();
		 //set response content type
		 res.setContentType("text/html");
		 //writer output to response object
		 pw.println("<table border='1' bgcolor='cyan' align='center'>");
		 pw.println("<tr><th> Crickter Name </th><th> role </th></tr>");
		 pw.println("<tr><td> Kohli   </td><td> Lover boy </td></tr>");
		 pw.println("<tr><td> dhoni   </td><td> super model </td></tr>");
		 pw.println("<tr><td> harhik </td> <td> hair stylist </td></tr>");
		 pw.println("<tr><td> Bumra </td> <td> Fisherman </td></tr>");
		 pw.println("<tr><td> Ashwin </td> <td> the sambar man </td></tr>");
		 pw.println("</table>");
		 //display date and time
		 pw.println("<h1 style='color:red;text-align:center'>Date and time"+new java.util.Date() +"</h1>"); 
		 res.setHeader("refresh","10");
		 //close stream
		 pw.close();
	}//service(-,-)
}//class
