/*SQL> desc  studentall;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 STNO                                      NOT NULL NUMBER(5)
 STNAME                                             VARCHAR2(20)
 STADD                                              VARCHAR2(20)
 STRESUME                                           CLOB
*/
package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsert {
	private static final String INSERT_STUDENTALL_QUERY = "INSERT INTO STUDENTALL VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		int no = 0;
		String name = null;
		String addrs = null;
		String resumePath = null;
		Connection con = null;
		PreparedStatement ps = null;
		File file = null;
		int length = 0;
		Reader reader = null;
		int result = 0;
		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter Student Number::");
				no = sc.nextInt();
				System.out.println("Enter Student name::");
				name = sc.next();
				System.out.println("Enter Student Addrs::");
				addrs = sc.next();
				System.out.println("Enter resume path::");
				resumePath = sc.next();
			}
		/*	// regiser jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");*/
			
			// regiser jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:mysql:///ntaj49db1", "root", "root");
			
			// create PreparedStatement object
			if (con != null)
				ps = con.prepareStatement(INSERT_STUDENTALL_QUERY);
			// Locate file and get its length
			file = new File(resumePath);
			length = (int) file.length();
			// create Reader Stream
			reader = new FileReader(file);
			// set query param values
			if (ps != null) {
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setString(3, addrs);
				ps.setCharacterStream(4, reader, length);
			}
			// execute SQL query
			if (ps != null)
				result = ps.executeUpdate();
			// process the Result
			if (result == 0)
				System.out.println("record insertion failed");
			else
				System.out.println("Record inserted");
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
			try {
				if (sc != null)
					sc.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
		} // finally
	}// main
}// class
