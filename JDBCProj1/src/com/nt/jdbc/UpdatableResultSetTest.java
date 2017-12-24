package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSetTest {
	private static final String GET_STUDENTS = "SELECT SNO,SNAME,SADD FROM STUDENT";

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create Statement object
			if (con != null)
				st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						                 ResultSet.CONCUR_UPDATABLE);
			// send and execute SQL Query in DB s/w
			if (st != null)
				rs = st.executeQuery(GET_STUDENTS);
			// process the ReusltSet(read operation)
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
				} // while
			} // if
			System.out.println("---------------");
			
		/*	//insert opreation
			if(rs!=null){
				rs.moveToInsertRow();  //create Empty record
				rs.updateInt(1,890);
				rs.updateString(2,"raja");
				rs.updateString(3,"hyd");
				rs.insertRow();
				System.out.println("Record inserted..");
			}*/
/*		  // update operation..
			if(rs!=null){
				rs.absolute(3);
				rs.updateString(3,"old hyd");
				rs.updateRow(); //updates 3rd col of 3rd record
				System.out.println("Record updated...");
			}
*/			
			//Delete operation
			if(rs!=null){
				rs.absolute(4);
				rs.deleteRow();
				System.out.println("record deleted");
			}
			
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
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try{
				 if(con!=null)
					 con.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
		}//finally
	}//main
}//class
