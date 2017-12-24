package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTest {
	private static final String SELECT_STUDENTS = "SELECT SNO,SNAME,SADD FROM STUDENT";

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// register JDBC driver
			//Class.forName("org.gjt.mm.mysql.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			//
			// Establish the connection
			//con = DriverManager.getConnection("jdbc:mysql:///ntaj49db", "root", "root");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ntaj49db1", "root", "root");
			// create STatement object
			if (con != null)
				st = con.createStatement();
			// send and execute SQL Query in DB s/w
			if (st != null)
				rs = st.executeQuery(SELECT_STUDENTS);
			// process the ReusltSet
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
				} // while
			} // if

		} // try
		/*catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}*/ catch (SQLException se) {
			se.printStackTrace();
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
	}// main
}// class
