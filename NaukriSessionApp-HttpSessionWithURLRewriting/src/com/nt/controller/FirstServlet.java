package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,addrs=null;
		int age=0;
		HttpSession ses=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/req1 data
		name=req.getParameter("name");
		addrs=req.getParameter("addrs");
		age=Integer.parseInt(req.getParameter("age"));
		//create HttpSession object
		ses=req.getSession(true);
		//put form1/req1 data in Session obj as Session attribute values
		ses.setAttribute("name",name);
		ses.setAttribute("addrs",addrs);
		ses.setAttribute("age",age);
		//generate form2 dynamically
		pw.println("<body bgcolor='orange'>");
		pw.println("<h1 style='color:red;text-align:center'>Provide Experience and Skill </h1>");
		pw.println("<form action="+res.encodeURL("secondurl")+" method='POST'>");
		pw.println("Experience::<input type='text' name='exp'><br>");
		pw.println("SkillSet:: <select name='skill'>");
		pw.println("<option name='java'>Java PKG </option>");
		pw.println("<option name='.net'>.Net PKG </option>");
		pw.println("<option name='oracle'>Oracle PKG </option>");
		pw.println("<option name='php'>PHP PKG </option>");
		pw.println("</select>");
		pw.println("<input type='submit' value='continue'><br>");
		pw.println("</form>");
		pw.println("<br> Session id::"+ses.getId());
		pw.println("</body>");
    }//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}//class
