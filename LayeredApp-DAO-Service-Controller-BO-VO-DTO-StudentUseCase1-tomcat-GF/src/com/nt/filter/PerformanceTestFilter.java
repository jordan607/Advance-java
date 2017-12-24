package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter("/controller")
public class PerformanceTestFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter pw=null;
		long start=0,end=0;
		ServletContext sc=null;
		//general settings
		pw=res.getWriter();
		
		start=System.currentTimeMillis();
		chain.doFilter(req,res);
		end=System.currentTimeMillis();
		pw.println(((HttpServletRequest)req).getRequestURL()+" has taken "+(end-start)+" ms to process the request");
	   System.out.println(((HttpServletRequest)req).getRequestURL()+" has taken "+(end-start)+" ms to process the request");
	   //get SerlvetContext obj
	   sc=getServletContext();
	   sc.log(((HttpServletRequest)req).getRequestURL()+" has taken "+(end-start)+" ms to process the request");
	}

}
