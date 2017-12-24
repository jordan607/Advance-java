package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		int exp=0;
		String skill=null;
		HttpSession ses=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form2/req2 data
		exp=Integer.parseInt(req.getParameter("exp"));
		skill=req.getParameter("skill");
		//get Access to HttpSession object
		ses=req.getSession(false);
		//keep form2/req2 data in Session attributes
		ses.setAttribute("exp",exp);
		ses.setAttribute("skill",skill);
		//Generate form3 dymically here...
		pw.println("<body bgcolor='pink'>");
		pw.println("<h1 style='color:red;text-align:center'>Provide PrefferedLocation & Salary </h1>");
		pw.println("<form action='thirdurl' method='POST'>");
		pw.println("Expected Salary :: <input type='text' name='sal'><br>");
		pw.println("Preffered Location:: <input type='text' name='city'><br>");
		pw.println("<input type='submit'  value='submit'>");
		pw.println("</form>");
		pw.println("<br> Session id::"+ses.getId());
		pw.println("</body>");
		//close stream
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
