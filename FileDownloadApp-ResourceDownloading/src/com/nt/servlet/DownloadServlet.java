package com.nt.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fileName=null;
		ServletContext sc=null;
		File file=null;
		InputStream is=null;
		ServletOutputStream sos=null;
		byte[] buffer=null;
		int bytesRead=0;
		//get Servletcontext
		sc=getServletContext();
		//read file name from request
		fileName=req.getParameter("file");
		//get the MIME type of the File and set it as response content type
		res.setContentType(sc.getMimeType("/"+fileName));
		System.out.println(sc.getMimeType("/"+fileName));
		//locate the file
		file=new File(sc.getRealPath("/"+fileName));
		System.out.println(sc.getRealPath("/")+fileName);
		//get File content length and make it as response content length
		res.setContentLengthLong(file.length());
		//given instruction to browser to make recived output as downloadable file
		res.addHeader("Content-disposition","attachment;fileName="+fileName);
		//create InputSTream pointing to file
		is=new FileInputStream(file);
		//create OutputStream pointing response object
		sos=res.getOutputStream();
		//write buffer based logic to read file content and to write to response object
		buffer=new byte[1024];
	   while( (bytesRead=is.read(buffer))!=-1){
		   sos.write(buffer,0,bytesRead);
	   }
	   //close stream
	   is.close();
	   sos.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}
}///class
