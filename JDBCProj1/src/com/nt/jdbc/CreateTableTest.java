package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result=0;
		try{
			// register jdbc driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:odbc:oradsn", "system", "manager");
			// create STatement object
			if (con != null)
				st = con.createStatement();
			//send and execute SQL Query
			if(st!=null)
				result=st.executeUpdate("create table temp(sno number(5))");
			//process the result
			if(result==0)
			  System.out.println("Table not created");
			else
			 System.out.println("table created");	
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
		} // finally
	}//main
}//class
