package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PsBatchProcessingTest1 {
 private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		int result[]=null;
		int sum=0;
		Scanner sc=null;
		int count=0;
		int no=0;
		String name=null,addrs=null;
		
		try{
			//read inputs
		 sc=new Scanner(System.in);
		 if(sc!=null){
			System.out.println("Students count::");
			count=sc.nextInt();
		 }
		//establish the connection	
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 if(con!=null){
           ps=con.prepareStatement(INSERT_QUERY);			 
		 }
		 //add Query param values to batch
		 if(ps!=null){
		   for(int i=1;i<=count;++i){
			   System.out.println("Enter "+i+" student details");
			   System.out.println("Enter student number::");
			   no=sc.nextInt();
			   System.out.println("Emter student name::");
			   name=sc.next();
			   System.out.println("Enter student addrs::");
			   addrs=sc.next();
			   //add each student details to batch
			   ps.setInt(1,no); ps.setString(2,name); ps.setString(3,addrs);
			   ps.addBatch();
		   }
		 }
		 //executeBatch
		 if(ps!=null)
		   result=ps.executeBatch();
		  //process the result
		  if(result!=null){
			  for(int i=0;i<result.length;++i)
				  sum=sum+result[i];
		  }//if
		 System.out.println("no.of records effected"+sum);	  
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
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
    	} // finally
	}//main
}//class
