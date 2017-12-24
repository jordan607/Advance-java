package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		int sum=0;
		try{
		  //establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create sTatement object	
			if(con!=null)
				st=con.createStatement();
		  //add queries to the batch
			if(st!=null){
				st.addBatch("insert into student values(3739,'ramesh','hyd')");
				st.addBatch("update student set sadd='old hyd1' where sno>=2000");
				st.addBatch("delete from student where sno<=100");
			}
			//execute the Batch
			if(st!=null){
				result=st.executeBatch();
			}
			//process the result
			if(result!=null){
				for(int i=0;i<result.length;++i)
				  sum=sum+result[i];
			}//if
			System.out.println("No.of records that are effected:::"+sum);
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
    	} // finally
	}//main
}//class
