package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckMarriageElgibilityServlet extends HttpServlet {
	
	public void init(ServletConfig cg){
		System.out.println("logical name::"+cg.getServletName());
		System.out.println("dbuser init param::"+cg.getInitParameter("dbuser"));
		System.out.println("dbpwd init param::"+cg.getInitParameter("dbpwd"));
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,gender=null,tage=null;
		String vstatus=null;
		int age=0;
		List<String> list=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		name=req.getParameter("pname");
		tage=req.getParameter("page");
		gender=req.getParameter("gender");
		
		//read vflag value
		vstatus=req.getParameter("vflag");
		
	if(vstatus.equalsIgnoreCase("no")){
		//Form validationn logic  (Server side)
		list=new ArrayList();
		System.out.println("Server side form validations.......");
		if(name.equals("") ||name.length()==0 || name==null){ //required
			list.add("Person name is required");
		}
		
		if(tage.equals("") ||tage.length()==0 ||tage==null){  //required
			list.add("Person age is requred");
		}
		else{
			try{
			age=Integer.parseInt(tage);  //age must be number
			  if(age<1 || age>125){  //age range
                  list.add("Person age must be in the range of 1 to 125");
			  }
			}
			catch(NumberFormatException nfe){
                list.add("Person age must be Numeric value");
			}
		}
		
		if(gender==null){
			list.add("Person gender must be selected");
		}
		//Display form validation erorr messages
		if(list.size()>0){
			for(String errMsg:list){
				pw.println("<li style='color:red'>"+errMsg+"</li>");
			}//for
			return;
		}//if
	}//if
	else{
		age=Integer.parseInt(tage);
		
	}
			
		//write b.logic or request processing logic
		if(gender.equalsIgnoreCase("M")){
			if(age<21){
				pw.println("<h1 style='color:red;text-align:center'>Mr."+name+" u r not elgible to marray(enjoy freedom)</h1>");
			}
			else{
				pw.println("<h1 style='color:orange;text-align:center'>Mr."+name+" u r  elgible to marray, But think twice</h1>");
			}
		}
		else{
			if(age<18){
				pw.println("<h1 style='color:red;text-align:center'>Miss."+name+" u r not elgible to marray </h1>");
			}
			else{
				pw.println("<h1 style='color:orange;text-align:center'>Miss."+name+" u r  elgible to marray</h1>");
			}
		}
		//add hyperlink
		pw.println("<br> <a href='input.html'>home</a>");
		pw.println("<br> request obj class name::"+req.getClass());
		pw.println("<br> response obj class name::"+res.getClass());
		//close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
	
	public static void main(String args[]){
		System.out.println("CMES::main(-) method");
	}
}//class
