package com.nt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class RequestCountFilter extends GenericFilter {
	 int count;


	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		ServletContext sc=null;
		 count++;
		 
		 //keep request count in Servletcontext attribute
		sc=getServletContext();
		sc.setAttribute("reqCount",count);
		 chain.doFilter(req,res);
		
	}//doFilter(-,-)
	
}//class
