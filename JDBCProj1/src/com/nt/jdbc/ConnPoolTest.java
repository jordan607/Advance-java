package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {
   private static final String GET_STUDENTS="SELECT SNO,SNAME,SADD FROM STUDENT";
	public static void main(String[] args) {
		OracleConnectionPoolDataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
         //create DataSource object 		 
		ds=new OracleConnectionPoolDataSource();
		//set jdbc properties
		if(ds!=null){
			ds.setDriverType("thin");
			ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ds.setUser("system");
			ds.setPassword("manager");
		  }
		//get con obj from jdbc con pool
		if(ds!=null){
			con=ds.getConnection();
		}
		//create jdbc statement object
		if(con!=null)
			st=con.createStatement();
		//send and execute SQL Query
		if(st!=null)
			rs=st.executeQuery(GET_STUDENTS);
		//process the ResultSet
		if(rs!=null){
			while(rs.next()){
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			}
		}//if
		
   	}//try
	catch(SQLException se){
		se.printStackTrace();
	}
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
				 con.close(); //releases the con obj back to jdbc con pool
			}
			catch(SQLException se){
				se.printStackTrace();
			}
	}//finally
  }//main
}//class
