package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class PersonAgeCaculatorJava {
  private static final String GET_DOB_PERSON="SELECT DOB FROM PERSON_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		java.sql.Date sqdob=null;
		java.util.Date udob=null,sysDate=null;
		float age=0.0f;
		try{
		  sc=new Scanner(System.in);
		  if(sc!=null){
			 System.out.println("Enter Person Id::");
			 pid=sc.nextInt();
		  }
	/*	  //register JDBC driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
*/
		  
    	//register JDBC driver
		  Class.forName("com.mysql.jdbc.Driver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:mysql:///ntaj49db1","root","root");
		  
		  //create PreparedStatement object	
		 if(con!=null)
			 ps=con.prepareStatement(GET_DOB_PERSON);
		   //set values to IN params of SQL Query
		  if(ps!=null)
			  ps.setInt(1,pid);
		  //execute the Query
		  if(ps!=null)
			  rs=ps.executeQuery();
		  //process the ResultSet
		  if(rs!=null){
			 if(rs.next()){
				 //get java.sql.Date obj from rs
				sqdob=rs.getDate(1); 
				//convert java.sql.Date obj to java.util.Date obj
				udob=(java.util.Date)sqdob;
				//get System Date
				sysDate=new Date();
				//calculate age
				age=(sysDate.getTime()-udob.getTime())/(1000*60*60*24*365.0f);
				System.out.println("Person age::"+age);
				System.out.println((sysDate.getTime()-udob.getTime()));
			 }
			 else{
				 System.out.println("Person not found");
			 }
		  }//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		} 
		catch (ClassNotFoundException cnf) {
			System.out.println(cnf.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objs
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

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

		} // finally

	}//main
}//class
