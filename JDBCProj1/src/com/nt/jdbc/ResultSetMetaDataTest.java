package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {
	private static final String GET_STUDENTS = "SELECT SNO,SNAME,SADD FROM STUDENT";

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int colCount = 0;
		try {
			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create Statement object
			if (con != null)
				st = con.createStatement();
			// send and execute Query
			if (st != null)
				rs = st.executeQuery(GET_STUDENTS);
			// create ResultSetMetaData object
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
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
