package com.nt.listerners;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionDurationAnalyzer implements HttpSessionListener {
   private long start,end;
	public SessionDurationAnalyzer() {
		System.out.println("SDA:0-param constructor");
	}
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext sc=null;
	   start=System.currentTimeMillis();
	   //get ServletContext obj
	   sc=se.getSession().getServletContext();
	   sc.log("Session started at"+new Date());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc=null;
	  end=System.currentTimeMillis();
	   //get ServletContext obj
	   sc=se.getSession().getServletContext();
	   sc.log("Session ended at"+new Date());
	   sc.log("Session duration::"+(end-start)+" ms");
	}
}
