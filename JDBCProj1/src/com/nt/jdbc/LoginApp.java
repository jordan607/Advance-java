package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		ResultSet rs=null;
		Statement st=null;
		String query=null;
		int count=0;
		try{
		  //read inputs
		   sc=new Scanner(System.in);
		   if(sc!=null){
			  System.out.println("Enter username::");
			  user=sc.nextLine(); //gives raja rao
			  System.out.println("Enter Password::");
			  pass=sc.nextLine(); //gives  rani
		   }
		   //convert input values as required for SQL Query
		   user="'"+user+"'"; //gives 'raja rao'
		   pass="'"+pass+"'";  //gives 'rani'
		   //register JDBC driver
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		   //create STatement object
		   if(con!=null)
			   st=con.createStatement();
		   //prepare SQL Query
		   //select count(*) from userlist where uname='raja' and pwd='rani1'
		   query="select count(*) from userlist where uname="+user+" and pwd="+pass;
		    //query="select count(*) from userlist where pwd="+pass+" and uname="+user;
		   System.out.println(query);
		   //send and execute SQL Query
		   if(st!=null)
			   rs=st.executeQuery(query);
		   //process the ResultSet
		   if(rs!=null){
			 rs.next();
			   count=rs.getInt(1);
		   }
		   if(count==0)
			   System.out.println("Invalid Credentials");
		   else
			   System.out.println("Valid Credentials");
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
