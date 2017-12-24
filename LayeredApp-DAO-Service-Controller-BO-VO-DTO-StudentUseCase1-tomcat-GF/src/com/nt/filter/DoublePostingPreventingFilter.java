package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class DoublePostingPreventingFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter pw=null;
		HttpServletRequest hreq=null;
		String method=null;
		HttpSession ses=null;
		int clientToken=0, serverToken=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//type casting
		hreq=(HttpServletRequest) req;
		//get req method
		method=hreq.getMethod();
		
		if(method.equalsIgnoreCase("GET")){
			 ses=hreq.getSession();
			 //generaet serverSide Token as Session attribute
			 ses.setAttribute("sToken", new Random().nextInt(100000));
			 //continue process (lanche form page)
			 chain.doFilter(req,res);
		}
		else{
			ses=hreq.getSession();
			 //read ServerToken, clientToken values
			 clientToken=Integer.parseInt(req.getParameter("cToken"));
			 serverToken=(int) ses.getAttribute("sToken");
			  if(clientToken==serverToken){
				  //chanage ServerToken Value
				  ses.setAttribute("sToken",new Random().nextInt(100000));
				  //continue proccess
				  chain.doFilter(req,res);
			  }
			  else{
				  pw.println("<h1 style='color:red;text-align:center'>Double Posting not here </h1> ");
				  pw.println("<br><a href='form.jsp'>home</a>");
				  return;
			  }
		}//else
	}//doFilter(-,-)
}//class
