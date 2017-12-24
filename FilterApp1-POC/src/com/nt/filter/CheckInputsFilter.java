package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CheckInputsFilter implements Filter {
    FilterConfig fg;
	 static{
		 System.out.println("CheckInputsFilter:: static block");
	 }
	 public CheckInputsFilter() {
	   System.out.println("CheckInputsFilter:: 0-param constructor");
	}
	 
	 @Override
	public void init(FilterConfig fg) throws ServletException {
		 this.fg=fg;
		 System.out.println("CheckInputsFilter::init(cg)");
	}
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter(-,-,-)");
		PrintWriter pw=null;
		int val1=0,val2=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		val1=Integer.parseInt(req.getParameter("t1"));
		val2=Integer.parseInt(req.getParameter("t2"));
		//write pre-request processing logic here
		if(val1<0  || val2<0){
			pw.println("<h1 style='color:red;text-align:center'>Inputs must be positive</h1>");
			pw.println("<br><br><a href='form.html'>home</a>");
			return;
		}
		else{
			System.out.println("CheckInputsFilter:: before chain.doFilter(-,-)  ");
			chain.doFilter(req, res);
			System.out.println("CheckInputsFilter:: after chain.doFilter(-,-)  ");
		  System.out.println("p1 init param value::"+fg.getInitParameter("p1"));
		}
		  System.out.println("browser name "+((HttpServletRequest)req).getHeader("user-agent"));
	}//doFilter(-,-)
	
	@Override
	public void destroy() {
	  System.out.println("CheckInputsFilter:: destroy() method");
	}

}
