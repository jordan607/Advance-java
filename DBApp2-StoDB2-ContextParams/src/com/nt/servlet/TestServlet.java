package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	public  void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
	  PrintWriter pw=null;
	  ServletConfig cg=null;
	  ServletContext sc=null;
		//general settings
	  pw=res.getWriter();
	  res.setContentType("text/html");
	  //get ServletConfig object
	  cg=getServletConfig();
	  pw.println("<br> dbuser init parameter value::"+cg.getInitParameter("dbuser"));
	  //get SErvletContext
	  sc=getServletContext();
	  pw.println("<br> dbuser context param value::"+sc.getInitParameter("dbuser"));
	  
	  pw.println("<br> SerlvetConfig object hashCode::"+cg.hashCode()+" classname::"+cg.getClass());
	  pw.println("<br> SerlvetContext object hashCode::"+sc.hashCode()+" classname::"+sc.getClass());
	  pw.println("<br> More Info Using ServletContext obj ");
	  
	  pw.println("<br> server info :"+sc.getServerInfo());
	  pw.println("<br> Servlet api version :"+sc.getMajorVersion()+"."+sc.getMinorVersion());
	  pw.println("<br> Context path name/web applicaiton name"+sc.getContextPath());
	  pw.println("<br> Path of web application::"+sc.getRealPath("/"));
	  pw.println("<br> Path of input.html::"+sc.getRealPath("/input.html"));
	  pw.println("<br> MIME type of input.html"+sc.getMimeType("/input.html"));
	  
	  sc.log("Today :::"+new Date());
	  
      //close stream
	  pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
	}//doPost(-,-)
}//class
