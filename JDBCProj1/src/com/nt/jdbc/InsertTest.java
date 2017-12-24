package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
	 Scanner sc=null;
	 int no=0;
	 String name=null,addrs=null;
	 Connection con=null;
	 Statement st=null;
	 String query=null;
	 int result=0;
	 
	 try{
	   //read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter student number::");
			no=sc.nextInt();  //gives 1001
			System.out.println("Enter student name::");
			name=sc.next();  //gives raja
			System.out.println("Enter Student Address::");
			addrs=sc.next(); //gives hyd
		}
		//convert input values as required for the SQL Query
		name="'"+name+"'"; //gives 'raja'
		addrs="'"+addrs+"'"; //gives 'hyd'
		//register driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver1");
		//establish the connection
		con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		//create Statement object
		if(con!=null)
			st=con.createStatement();
		//prepare SQL Query
		  //insert into student values(101,'ramesh','hyd')
		 query="insert into student values("+no+","+name+","+addrs+")";
		 System.out.println(query);
		//send and execute SQL Query
		 if(st!=null)
			result=st.executeUpdate(query);
		 //process the result
		 if(result==0)
			 System.out.println("record not inserted");
		 else
			 System.out.println("record  inserted");
		 
	 }//try
		catch (SQLException se) {
			System.out.println("Constraint problem");
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
