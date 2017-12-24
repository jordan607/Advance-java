package com.nt.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		boolean flag=false;
		ResultSet rs=null;
		int count=0;
		try{
		  //read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null){
			  System.out.println("Enter SQL Query(Select or non-select)");
			  query=sc.nextLine();
		  }
		  //register jdbc driver
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		  //create Statement object 
		  if(con!=null)
			  st=con.createStatement();
		  //send and execute SQL Query
		  if(st!=null)
			  flag=st.execute(query);
		  if(flag==true){
			  System.out.println("Select Query executed");
			  //get ResultSet object
			  if(st!=null)
			    rs=st.getResultSet();
			  //process the Resultset
			  if(rs!=null){
				  while(rs.next()){
					  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				  }//while
			  }//if
		  }//if
		  else{
			  System.out.println("Non-Select Query Executed");
			  if(st!=null)
				  count=st.getUpdateCount();
			  System.out.println(count+" no.of records are updated");
		  }//else
			
			
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
			
			try {
				if (sc!= null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // finally
	}//main
}//class
