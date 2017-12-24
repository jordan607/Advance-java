package com.nt.listerners;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeploymentMonitor implements ServletContextListener {
   private  long start,end;
    public DeploymentMonitor() {
		System.out.println("DPA:0-param constructor");
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=null;
		 start=System.currentTimeMillis();
	    //get ServletContext object
		 sc=sce.getServletContext();
		 sc.log(sc.getContextPath()+" is deployed at::"+new Date());
		}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext sc=null;
	   end=System.currentTimeMillis();
	   //get ServletContext object
		 sc=sce.getServletContext();
		 sc.log(sc.getContextPath()+" is undeployed/reloaded/stopped at::"+new Date());
		 sc.log(sc.getContextPath()+"is been running mode for::"+(end-start)+" ms");
	}
	
}
