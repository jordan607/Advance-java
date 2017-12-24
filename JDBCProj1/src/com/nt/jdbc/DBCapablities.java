package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 

public class DBCapablities {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		DatabaseMetaData dbmd=null;
		try{
		 //register JDBC driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		 //establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		 //create DatabaseMetaData
		 if(con!=null)
			 dbmd=con.getMetaData();
		 if(dbmd!=null){
			 System.out.println("dbmd obj class name::"+dbmd.getClass());
			 System.out.println("DB name::"+dbmd.getDatabaseProductName());
			 System.out.println("DB version::"+dbmd.getDatabaseProductVersion());
			 //System.out.println("DB Version Details::"+dbmd.getDatabaseMajorVersion()+"."+dbmd.getDatabaseMinorVersion());
			 System.out.println("JDBC version::"+dbmd.getJDBCMajorVersion()+"."+dbmd.getJDBCMinorVersion());
			 System.out.println("All SQL Keywords::"+dbmd.getSQLKeywords());
			 System.out.println("All Numeric functions::"+dbmd.getNumericFunctions());
			 System.out.println("All System Fuctions::"+dbmd.getSystemFunctions());
			 System.out.println("Current username::"+dbmd.getUserName());
			 System.out.println("Max length of username::"+dbmd.getMaxUserNameLength());
			 System.out.println("Max Table name length::"+dbmd.getMaxTableNameLength());
			 System.out.println("Max Col name length::"+dbmd.getMaxColumnNameLength());
			 System.out.println("Supperts Procedures::"+dbmd.supportsStoredProcedures());
			 System.out.println("MAx rowsize::"+dbmd.getMaxRowSize());
			 System.out.println("MAx Connections ::"+dbmd.getMaxConnections());
		   }//if 
		 }//try
		 catch(SQLException se){
			 se.printStackTrace();
		 }
		 catch(ClassNotFoundException cnf){
			 cnf.printStackTrace();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		finally{
			try{
			 if(con!=null)
				 con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
