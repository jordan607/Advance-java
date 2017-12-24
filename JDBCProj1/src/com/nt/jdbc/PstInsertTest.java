package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PstInsertTest {
	private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		int no = 0;
		String name = null, addrs = null;
		int result = 0;

		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter Students count::");
				count = sc.nextInt();
			}
			// register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create PreparedStatement obj having pre-compiled SQL Query
			if (con != null)
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			// read n student details from enduser and set them Query params
			if (ps != null && sc != null) {
				for (int i = 1; i <= count; ++i) {
					// read each Student details
					System.out.println("Enter " + i + " student details");
					System.out.println("Enter number::");
					no = sc.nextInt();
					System.out.println("Enter name::");
					name = sc.next();
					System.out.println("Enter Address::");
					addrs = sc.next();
					// set each Student details query params
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					// execute the Query
					result = ps.executeUpdate();
					// process the result
					if (result == 0)
						System.out.println(i + " student details are not inserted");
					else
						System.out.println(i + " student details are inserted");
				} // for
			} // if
		} // try
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objs
			try {
				if (ps != null)
					ps.close();
			} // try
			catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {

				se.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
	}// main
}// class
