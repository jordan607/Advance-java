package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCaculatorSQL {
 private static final String GET_AGE_QUERY="SELECT (SYSDATE-DOB)/365 FROM PERSON_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		float age=0.0f;
		try{
		  sc=new Scanner(System.in);
		  if(sc!=null){
			 System.out.println("Enter Person Id::");
			 pid=sc.nextInt();
		  }
		  //register JDBC driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  
		  
		  //create PreparedStatement object	
		 if(con!=null)
			 ps=con.prepareStatement(GET_AGE_QUERY);
		   //set values to IN params of SQL Query
		  if(ps!=null)
			  ps.setInt(1,pid);
		  //execute the Query
		  if(ps!=null)
			  rs=ps.executeQuery();
		  //process the ResultSet
		  if(rs!=null){
			 if(rs.next()){
				 age=rs.getFloat(1);
				 System.out.println("age of person::"+age);
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
