package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CheckTimingsFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter pw=null;
		Calendar calendar=null;
		int hour=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//get System Date
		calendar=Calendar.getInstance();
		//get current hour of the day
		hour=calendar.get(Calendar.HOUR_OF_DAY);   //24 hours format
		if(hour<9 || hour>17 ){
			pw.println("<h1 style='color:red;text-align:center'> Please Visit website in between 9am to 5pm </h1>");
			return;
		}
		else{
			System.out.println("CheckTimingsFilter::before chain.doFilter(-,-,-)");
			chain.doFilter(req,res);
			System.out.println("CheckTimingsFilter::after chain.doFilter(-,-,-)");
		}
	}//doFilter(-,-,-)
}//class
