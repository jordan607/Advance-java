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
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String no=null;
		String name=null;
		String m1=null,m2=null,m3=null;
		StudentVO vo=null;
		StudentDTO dto=null;
		StudentService service=null;
		String result=null;
		PrintWriter pw=null;
		//general Settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
			name=req.getParameter("sname");
		m1=req.getParameter("m1");
		m2=req.getParameter("m2");
		m3=req.getParameter("m3");
		//Store form data into  StudentVO class object
		vo=new StudentVO();
		 vo.setSname(name);  
		vo.setM1(m1); vo.setM2(m2); vo.setM3(m3);
		//Covert VO obj to DTO object
		dto=new StudentDTO();
		dto.setSname(vo.getSname());
		dto.setM1(Integer.parseInt(vo.getM1()));
		dto.setM2(Integer.parseInt(vo.getM2()));
		dto.setM3(Integer.parseInt(vo.getM3()));
		// create and use Service class obj
		service=new StudentServiceImpl();
		try{
			result=service.generateResult(dto);
			pw.println("<h1> Student Result::"+result+"</h1>");
		}
		catch(Exception e){
			pw.println("<h1 style='color:red;text-align:center'>Internal Problem </h1>");
			pw.println("<hr>"+e.getMessage());
		}
		
		//add hyperlink
		pw.println("<br><a href='form.jsp'>home</a>");
		pw.println("<br><br> &nbsp;&nbsp;&nbsp;&nbsp; request count::"+getServletContext().getAttribute("reqCount"));

		//close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
