package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	public  void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
	  PrintWriter pw=null;
	  ServletConfig cg=null;
		//general settings
	  pw=res.getWriter();
	  res.setContentType("text/html");
	  //get ServletConfig object
	  cg=getServletConfig();
	  pw.println("<br> dbuser init parameter value::"+cg.getInitParameter("dbuser"));
	  pw.println("<br> ServletConfig object hashCode::"+cg.hashCode());
      //close stream
	  pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
	}//doPost(-,-)
}//class
