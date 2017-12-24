package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTestTWR {
	private static final String GET_STUDENTS = "SELECT SNO,SNAME,SADD FROM STUDENT";

	public static void main(String[] args) {
		int colCount = 0;
		ResultSetMetaData rsmd=null;
		// Establish the connection
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager")){
			// create Statement object
			if (con != null)
				try(Statement st = con.createStatement()){
			// send and execute Query
			if (st != null)
				try(ResultSet rs = st.executeQuery(GET_STUDENTS)){
   	      		//  create ResultSetMetaData object
			       if (rs != null)
				       rsmd = rs.getMetaData();
			       	// Display col names and col datatype
			       	if (rsmd != null) {
			       		colCount = rsmd.getColumnCount();
			       		for (int i = 1; i <= colCount; ++i) {
			       			System.out.print(rsmd.getColumnLabel(i) + " ");
			       		} // for
			       		System.out.println();
			       		for (int i = 1; i <= colCount; ++i) {
			       			System.out.print(rsmd.getColumnTypeName(i) + " ");
			       		} // for
			       		System.out.println();
			       	}
			       	// print col values
			       	if (rs != null && rsmd != null) {
			       		while (rs.next()) {
			       			for (int i = 1; i <= colCount; ++i) {
			       				System.out.print(rs.getString(i) + "  ");
			       			} // for
			       			System.out.println();
			       		} // while
		  	 } // if
		  } // try
		}//try
	  }//try		
	catch (SQLException se) {
		se.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	 }
	}// main
}// class
