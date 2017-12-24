package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieve {
  private static final String GET_STUDENT_DETAILS_BY_NO="SELECT STNO,STNAME,STADD,STRESUME FROM STUDENTALL WHERE STNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Reader reader=null;
		Writer writer=null;
		char[] buffer=null;
		int charsRead=0;
		try{
		  //read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null){
			  System.out.println("Enter student number::");
			  no=sc.nextInt();
		  }
		/*  //register JDBC driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/

 	     //register Driver
		 Class.forName("com.mysql.jdbc.Driver");
		//Establish the connection
		 con=DriverManager.getConnection("jdbc:mysql:///ntaj49db1","root","root");
		  
		  //create PreparedStatement object
		  if(con!=null)
			  ps=con.prepareStatement(GET_STUDENT_DETAILS_BY_NO);
		  //set values to query params
		   if(ps!=null){
			   ps.setInt(1,no);
		   }
		   //execute the Query
		   if(ps!=null){
			 rs=ps.executeQuery();  
		   }
		   if(rs!=null){
			   if(rs.next()){
				  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				  reader=rs.getCharacterStream(4);
				  //copy content to Dest file using buffer
				  buffer=new char[1024];
				  writer =new FileWriter("new_resume.txt");
				  if(writer!=null && reader!=null){
					  while((charsRead=reader.read(buffer))!=-1){
						  writer.write(buffer,0,charsRead);
					  }//while
				  }//if
				  System.out.println("CLOB Value is retrieved and written  to Dest file");
			   }
			 else{
			   System.out.println("record not found");
			  }
		   }//if
			
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
				if (reader!= null)
					reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (writer!= null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
	}//main
}//class
