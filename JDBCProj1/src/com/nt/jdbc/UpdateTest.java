package com.nt.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newName=null,newAddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int result=0;
		try{
		 //read inputs
			sc=new Scanner(System.in);
		  if(sc!=null){
			  System.out.println("Enter student existing number ::");
			  no=sc.nextInt(); //gives 101
			  System.out.println("Enter new Student name::");
			  newName=sc.next(); //gives raja1
			  System.out.println("Enter new Student address::");
			  newAddrs=sc.next(); //gives hyd1
		  }//if
		  
		  //convert input values as as required for SQL Query
		  newName="'"+newName+"'"; //gives 'raja1'
		  newAddrs="'"+newAddrs+"'"; //gives 'hyd1'
		  
		  //register jdbc driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create Statement object 
		  if(con!=null)
			  st=con.createStatement();
		  //prepare SQL Query
		  //update student set sname='raja1',sadd='hyd1' where sno=101
		  query="update student set sname="+newName+",sadd="+newAddrs+" where sno="+no;
		  System.out.println(query);
		  //send and execute SQL query in DB s/w
		  if(st!=null)
			  result=st.executeUpdate(query);
		  //process the result
		  if(result==0)
			  System.out.println("record not found to update");
		  else
			  System.out.println("record found and updated");
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
