//WordServlet.java
package com.nt.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class   WordServlet extends HttpServlet
{
	protected void service(HttpServletRequest  req,HttpServletResponse res)throws ServletException,IOException{
		 PrintWriter pw=null;
		 //get PrintWrtier 
		 pw=res.getWriter();
		 //set response content type
		 res.setContentType("application/msword");
		 //writer output to response object
		 pw.println("<table border='1' bgcolor='cyan' align='center'>");
		 pw.println("<tr><th> Crickter Name </th><th> role </th></tr>");
		 pw.println("<tr><td> Kohli   </td><td> Lover boy </td></tr>");
		 pw.println("<tr><td> dhoni   </td><td> super model </td></tr>");
		 pw.println("<tr><td> harhik </td> <td> hair stylist </td></tr>");
		 pw.println("<tr><td> Bumra </td> <td> Fisherman </td></tr>");
		 pw.println("<tr><td> Ashwin </td> <td> the sambar man </td></tr>");
		 pw.println("</table>");
		 //close stream
		 pw.close();
	}//service(-,-)
}//class
