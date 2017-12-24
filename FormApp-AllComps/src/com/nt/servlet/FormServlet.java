package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

public class FormServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,addrs=null,gen=null,qlfy=null,ms=null;
		String [] hb=null,crs=null;
		int age=0;
		String msg=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		name=req.getParameter("pname");
		age=Integer.parseInt(req.getParameter("page"));
		addrs=req.getParameter("addrs");
		gen=req.getParameter("gender");
		qlfy=req.getParameter("qlfy");
		ms=req.getParameter("ms");
		if(ms==null){
			ms="single";
		}
		hb=req.getParameterValues("hb");
		if(hb==null)
			hb=new String[]{"no hobies"};
		crs=req.getParameterValues("courses");
		if(crs==null)
			crs=new String[]{"no courses are selected"};
		//write req processing logic
		if(gen.equalsIgnoreCase("M")){
			if(age<5)
				msg="Master."+name+" your baby boy";
			else if(age<=12)
				msg="Master."+name+" your small boy";
			else if(age<=19)
				msg="Mr."+name+" your teenage boy";
			else if(age<=35)
				msg="Mr."+name+" your young man";
			else if(age<=50)
				msg="Mr."+name+" middle-aged man";
			else
				msg="Mr."+name+" Budda man";
		}
		else{
			if(age<5)
				msg="Master."+name+" your baby girl";
			else if(age<=12)
				msg="Master."+name+" your small girl";
			else if(age<=19){
				if(ms.equalsIgnoreCase("married"))
				   msg="Mrs."+name+" your teenage girl";
				else
				  msg="Miss."+name+" your teenage girl";
			}
			else if(age<=35){
				if(ms.equalsIgnoreCase("married"))
					   msg="Mrs."+name+" your young woman";
					else
					  msg="Miss."+name+" your young woman";
			}
			else if(age<=50){
				if(ms.equalsIgnoreCase("married"))
					   msg="Mrs."+name+" your middle-aged woman";
					else
					  msg="Miss."+name+" your middle-aged  woman";
			}
			else{
				if(ms.equalsIgnoreCase("married"))
					   msg="Mrs."+name+" your old woman";
					else
					  msg="Miss."+name+" your old  woman";
			}//else
		}//else
		
		//display message
		pw.println("<h1 style='color:red';text-align:'center'>"+msg+"</h1>");
		
		//read and display form data.....
		pw.println("<h1 style='color:green'>U r details are::</h1>");
		pw.println("<br> name:::"+name);
		pw.println("<br> age:::"+age);
		pw.println("<br> Address:::"+addrs);
		pw.println("<br> Gender:::"+gen);
		pw.println("<br> Marital Status:::"+ms);
		pw.println("<br> Qualification:::"+qlfy);
		pw.println("<br> Hobies:::"+Arrays.toString(hb));
		pw.println("<br> Courses:::"+Arrays.toString(crs));
		//add home hyperlink
		pw.println("<a href='form.html'>home</a>");
		//close stream
		pw.close();
	}//doGet(-,-)
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
