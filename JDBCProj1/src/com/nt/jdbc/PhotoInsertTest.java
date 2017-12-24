package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoInsertTest {
  private static final String INSER_EMP_DETAILS="INSERT INTO EMPALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null;
		float  salary=0.0f;
		String photoPath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		int length=0;
		InputStream is=null;
		int result=0;
		try{
		  sc=new Scanner(System.in);
		  if(sc!=null){
			  System.out.println("Enter Emp number::");
			  no=sc.nextInt();
			  System.out.println("Enter Emp name::");
			  name=sc.next();
			  System.out.println("Enter Emp Salary::");
			  salary=sc.nextFloat();
			  System.out.println("Enter Emp Photo Path::");
			  photoPath=sc.next();
		  }//if
	/*	  //register  Driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
		  
		  //register driver
		  Class.forName("com.mysql.jdbc.Driver");
		  //Establish the connection
		  con=DriverManager.getConnection("jdbc:mysql:///ntaj49db1","root","root");
		  
		  //create PreparedStatement object
		  if(con!=null)
			  ps=con.prepareStatement(INSER_EMP_DETAILS);
		  //Locate photo file  and get its length
		     file=new File(photoPath);
		     length=(int) file.length();
		   //create InputStream pointing to the file
		     is=new FileInputStream(file);
		  //set values to query params
		  if(ps!=null){
			  ps.setInt(1,no);
			  ps.setString(2,name);
			  ps.setFloat(3,salary);
			  ps.setBinaryStream(4,is,length);
		  }
		  //execute the Query
		  if(ps!=null)
			result=ps.executeUpdate();
		  //process the Result
		  if(result==0)
			  System.out.println("Record not inserted");
		  else
			  System.out.println("record inserted");
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			System.out.println(cnf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objs
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if (sc!= null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (is!= null)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // finally
	}//main
}//class
