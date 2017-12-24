package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SensitiveTest {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		String query = null;
		ResultSet rs = null;
		int count=0;
		try {
			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create Statement object
			if (con != null)
				st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						                 ResultSet.CONCUR_UPDATABLE);
			// prepare SQL Query
			// send and execute SQL Query
			if (st != null)
				rs = st.executeQuery("select sno,sname,sadd from student");
			// process the ResultSet
			if (rs != null) {
				while (rs.next()) {
					rs.refreshRow();
					try{
					if(count==0)
						Thread.sleep(40000); //modify db table data from SQL Prompt
					}
					catch(Exception se){
						se.printStackTrace();
					}
					count++;
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3));
				  } 
			} // if
		} // try
		catch (ClassNotFoundException cnf) { // for known Exception
			cnf.printStackTrace();
		} catch (SQLException se) { // for known exception
			se.printStackTrace();
		} catch (Exception e) { // unknown exception
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
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
