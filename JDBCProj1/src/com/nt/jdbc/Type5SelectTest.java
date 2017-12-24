package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Type5SelectTest {

	public static void main(String[] args) {
		Scanner sc = null;
		int no = 0;
		Connection con = null;
		Statement st = null;
		String query = null;
		ResultSet rs = null;
		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("enter Dept number::");
				no = sc.nextInt();
			}
			// register jdbc driver
			Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;ServiceName=xe", "system", "manager");
			// create Statement object
			if (con != null)
				st = con.createStatement();
			// prepare SQL Query
			query = "select * from Dept where deptno=" + no;
			System.out.println(query);
			// send and execute SQL Query
			if (st != null)
				rs = st.executeQuery(query);
			// process the ResultSet
			if (rs != null) {
				if (rs.next()) {
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3));
				} else {
					System.out.println("No record found");
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
			try{
			 if(sc!=null)
				 sc.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}//finally
	}//main
}//class
