package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.dto.JobSeekerDTO;
import com.nt.service.JobSeekerService;
import com.nt.service.JobSeekerServiceImpl;

@WebServlet("/thirdurl")
public class ThirdServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
         int salary=0;
		String city=null;
		HttpSession ses=null;
		String name=null,addrs=null,skill=null;
		int age=0,exp=0;
		JobSeekerDTO dto=null;
		JobSeekerService service=null;
		String finalResult=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form3/req3 data
		city=req.getParameter("city");
		salary=Integer.parseInt(req.getParameter("sal"));
		//get Access to HttpSession object
		ses=req.getSession(false);
		//read form1/req1 and form2/req2 data from Session attribute(Session tracking)
		name=(String)ses.getAttribute("name");
		addrs=(String)ses.getAttribute("addrs");
		age=(Integer)ses.getAttribute("age");
		exp=(Integer)ses.getAttribute("exp");
		skill=(String)ses.getAttribute("skill");
		//store 3 forms/requests data to DB table as record
		dto=new JobSeekerDTO();
		dto.setName(name);
		dto.setAddrs(addrs);
		dto.setAge(age);
		dto.setSkill(skill);
		dto.setExp(exp);
		dto.setCity(city);
		dto.setSalary(salary);
		//use Service class
		service=new JobSeekerServiceImpl();
		try{
			finalResult=service.register(dto);
			pw.println("<h1 style='color:red;text-align:center'>"+finalResult+"</h1>");
		}//try
		catch(Exception e){
			e.printStackTrace();
			pw.println("<h1 style='color:red;text-align:center'>Internal Problem</h1>");
		}
		pw.println("<br> Session id::"+ses.getId());
		//invlidate Session
		ses.invalidate();
		//add hyperlink
		pw.println("<br> <a href='personal.html'>home</a>");
		//close stream
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
