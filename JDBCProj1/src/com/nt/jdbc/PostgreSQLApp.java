package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLApp {
  private static final String GET_PRODUCTS="SELECT PID,PNAME,PRICE FROM PRODUCT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
		 //register Driver
			//Class.forName("org.postgresql.Driver");
		 //establish the connection
		  //con=DriverManager.getConnection("jdbc:postgresql:ntaj49","postgres","root");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/ntaj49","postgres","root");
		 //create Statement object
		  if(con!=null)
		    st=con.createStatement();
		  //send and execute SQL Query
		  if(st!=null)
			  rs=st.executeQuery(GET_PRODUCTS);
		  //process the ReusltSEt object
		  if(rs!=null){
			  while(rs.next()){
				  System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			  }//while
		  }//if
		  
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		/*catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}*/
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc objs
			try{
			 if(rs!=null)
				 rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				 if(st!=null)
					 st.close();
				}
				catch(SQLException se){
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
