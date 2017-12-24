package com.nt.listerners;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestProcessingAnalyzer implements ServletRequestListener {
    private long start,end;
	public RequestProcessingAnalyzer() {
		System.out.println("RpA:0-param constructor");
	}
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		start=System.currentTimeMillis();
	}
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletContext sc=null;
        end=System.currentTimeMillis();
        //get SerlvetContext obj
        sc=sre.getServletContext();
        sc.log(((HttpServletRequest)sre.getServletRequest()).getRequestURL()+" has taken"+(end-start)+" ms"+" to process the request");
        
	}
	
}
