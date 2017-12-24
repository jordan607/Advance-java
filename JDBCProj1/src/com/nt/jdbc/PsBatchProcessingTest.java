package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PsBatchProcessingTest {
 private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		int result[]=null;
		int sum=0;
		try{
		//establish the connection	
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 if(con!=null){
           ps=con.prepareStatement(INSERT_QUERY);			 
		 }
		 //add Query param values to batch
		 if(ps!=null){
			 ps.setInt(1,1111);
			 ps.setString(2,"mahesh");
			 ps.setString(3, "hyd");
			 ps.addBatch();
			 
			 ps.setInt(1,2222);
			 ps.setString(2,"tarak");
			 ps.setString(3, "hyd");
			 ps.addBatch();
		 }//if
		 //executeBatch
		 if(ps!=null)
		   result=ps.executeBatch();
		  //process the result
		  if(result!=null){
			  for(int i=0;i<result.length;++i)
				  sum=sum+result[i];
		  }//if
		 System.out.println("no.of records effected"+sum);	  
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
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
    	} // finally
	}//main
}//class
