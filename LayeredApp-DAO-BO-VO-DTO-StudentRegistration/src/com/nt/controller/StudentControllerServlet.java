package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.StudentDTO;
import com.nt.service.StudentService;
import com.nt.service.StudentServiceImpl;
import com.nt.vo.StudentVO;

@WebServlet("/controller")
public class StudentControllerServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String  sno=null,sname=null,m1=null,m2=null,m3=null;
		StudentVO vo=null;
		StudentDTO dto=null;
		StudentService service=null;
		String result=null;
		//general Settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		sno=req.getParameter("sno");
		sname=req.getParameter("sname");
		m1=req.getParameter("m1");
		m2=req.getParameter("m2");
		m3=req.getParameter("m3");
		//Store Form Data into StudentVO class object (optional)
		vo=new StudentVO();
		vo.setSno(sno); 
		vo.setSname(sname);
		vo.setM1(m1);
		vo.setM2(m2);
		vo.setM3(m3);
		//Convert StudentVO class obj to StudentDTO class object
		dto=new StudentDTO();
		dto.setSno(Integer.parseInt(vo.getSno()));
		dto.setSname(vo.getSname());
		dto.setM1(Integer.parseInt(vo.getM1()));
		dto.setM2(Integer.parseInt(vo.getM2()));
		dto.setM3(Integer.parseInt(vo.getM3()));
		//create Service class object
		service=new StudentServiceImpl();
		try{
			result=service.generateResult(dto);
			pw.println("<H1> Result :::"+result+"</h1>");
			pw.println("<a href='register.html'>home </a>");
		}
		catch(Exception e){
			e.printStackTrace();
			pw.println("<h1 style='color:red;text-align:center'>Internal Problem <a href='register.html'>Try Again </a></h1>");
		}
		
		//close stream 
		pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-)
}//class
