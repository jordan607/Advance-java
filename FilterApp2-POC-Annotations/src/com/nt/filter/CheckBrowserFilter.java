package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(value="/*")
public class CheckBrowserFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		 PrintWriter pw=null;
		 String brName=null;
		   //general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //get Brower name
		 brName=((HttpServletRequest)req).getHeader("user-agent");
		 if(brName.indexOf("Chrome")==-1){
			 pw.println("<br><h1> Request must be given from Google Chrome</h1>");
			 return;
		 }
		 else{
			 System.out.println("CBF:: before chain.doFilter(-,-)");
			 chain.doFilter(req,res);
			 System.out.println("CBF:: after chain.doFilter(-,-)");
		 }
	}//doFilter(-,-)
}//class
