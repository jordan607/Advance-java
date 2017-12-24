package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetrieve {
	private static final String SELECT_DATES_QUERY = "SELECT PID,PNAME,DOB,DOJ FROM PERSON_TAB";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pid = 0;
		String pname = null;
		java.sql.Date sqdob = null, sqdoj = null;
		java.util.Date udob = null, udoj = null;
		String dob = null, doj = null;
		SimpleDateFormat sdf = null;

		try {
/*			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
*/
			
			// register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:mysql:///ntaj49db1", "root", "root");
			
			// create PreparedStatement obj
			if (con != null)
				ps = con.prepareStatement(SELECT_DATES_QUERY);
			// execute the SQL Query
			if (ps != null)
				rs = ps.executeQuery();
			// process the ResultSet
			if (rs != null) {
				while (rs.next()) {
					pid = rs.getInt(1);
					pname = rs.getString(2);
					sqdob = rs.getDate(3);
					sqdoj = rs.getDate(4);
					// Convert java.sql.Date class objs to java.util.Date class
					// objs
					udoj = (java.util.Date) sqdoj;
					udob = (java.util.Date) sqdob;
					// Convert java.util.Date class objs to String date values
					sdf = new SimpleDateFormat("MMM-yyyy-dd");
					dob = sdf.format(udob);
					doj = sdf.format(udoj);
					System.out.println(pid + "  " + pname + "  " + dob + "  " + doj);
				} // while
			} // if
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
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
		} // finally
	}// main
}// class
