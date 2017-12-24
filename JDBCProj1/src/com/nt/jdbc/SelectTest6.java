package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			//register JDBC driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute the SQL query
			if(st!=null)
				rs=st.executeQuery("select count(*) from emp");
			//process the ResultSet
			if(rs!=null){
				rs.next();
				System.out.println("Records count::"+rs.getInt(1));
			}
		   }//try
			
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
