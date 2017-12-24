package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SelectTestTWR {
  private static final String GET_STUDETS_QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		
		  //establish connection
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager")){
			  //create Statement 
			try(Statement st=con.createStatement()){
				 //reate ResutSet
				try(ResultSet rs=st.executeQuery(GET_STUDETS_QUERY)){
					//process the ResultSet
					while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
					}//while
				}//try3
			}//try2
		}//try1
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//main
}//class
