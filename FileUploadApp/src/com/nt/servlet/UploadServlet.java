package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;

public class UploadServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 MultipartFormDataRequest nreq=null;
		 UploadBean bean=null;
		 Hashtable<String,UploadFile> ht=null;
		 Enumeration e=null;
		 UploadFile file=null;
		 //general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 try{
		 //prepare Special Request object
		 nreq=new MultipartFormDataRequest(req);
		 //perform file uploading settings
		bean=new UploadBean();
		bean.setFolderstore("c:/store");
		bean.setFilesizelimit(20*1024);
		bean.setMaxfiles(10);
		bean.setOverwrite(true);
		//perform uploading
		bean.store(nreq);
		 }
		 catch(Exception ex){
			 pw.println("<h1> Problem in file Uploading </h1>");
			 ex.printStackTrace();
			 return ;
		 }
		 //Display names of the files that are uploaded..
		 ht=nreq.getFiles();
		 e=ht.elements();
		 pw.println("<h1> The uploaded files are </h1>");
		 while(e.hasMoreElements()){
			 file=(UploadFile)e.nextElement();
			 pw.println("<b><br>"+file.getFileName()+"....."+file.getFileSize()+"<br></b>");
		 }//while
		 
		 //add hyperlink
		 pw.println("<br><a href='upload.html'> home</a>");
		 //close streams
		 pw.close();
	}//doPost(-,-)
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doPost(req,res);
	}//doGet(-,-)
}//class
