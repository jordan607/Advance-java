package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelToOracleTest {
   private static final String ORA_INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
   private static final String EXCEL_SELECT_STUDENT_QUERY="SELECT * FROM  [Sheet1$]";
   
   public static void main(String[] args) {
		Connection oraCon=null,xlsCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int sno=0;
		String sname=null;
		String sadd=null;
		
		try{
			
			//register jdbc drivers
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//establish the connections
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			xlsCon=DriverManager.getConnection("jdbc:odbc:xlsdsn");
		
			//create Statemet objects
			if(xlsCon!=null)
				st=xlsCon.createStatement();
			if(oraCon!=null)
				ps=oraCon.prepareStatement(ORA_INSERT_STUDENT_QUERY);
			
			//execute Select Query on Ms-Excel
			if(st!=null)
			  rs=st.executeQuery(EXCEL_SELECT_STUDENT_QUERY);	
			//Copy the records of ResultSet(Excel) to ORacle DB table
			if(rs!=null &&ps!=null){
				while(rs.next()){
					//get each record from ResultSet object
					sno=rs.getInt(1);
					sname=rs.getString(2);
					sadd=rs.getString(3);
					//set values to InsertQuery params(oracle)
					ps.setInt(1,sno);
					ps.setString(2,sname);
					ps.setString(3,sadd);
					//execute insert query(oracle)
					ps.executeUpdate();
				}//while
				System.out.println("Records are copied from Ms-Excel to Oracle");
			}//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
			System.out.println("Problem in copying records");
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Problem in Copying records");
		}
	 finally{
		 //close objs
		 try{
		   if(rs!=null)
			   rs.close();
		 }
		 catch(SQLException se){
			 se.printStackTrace();
		 }
		 
		   try{
			   if(ps!=null)
				   ps.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
		   
		   try{
			   if(st!=null)
				   st.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
		   
		   try{
			   if(xlsCon!=null)
				   xlsCon.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
		   
		   try{
			   if(oraCon!=null)
				   oraCon.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
	 }//finally
	}//main
}//class
