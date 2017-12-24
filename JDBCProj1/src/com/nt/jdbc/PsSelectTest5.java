package com.nt.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PsSelectTest5 {
 private static final String GET_MAX_SAL_EMP_DETAILS="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
		  //register drvier
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		   //create Statement object
		   if(con!=null)
			   ps=con.prepareStatement(GET_MAX_SAL_EMP_DETAILS);
		   //execute query
		     if(ps!=null)
		    	 rs=ps.executeQuery();
		   //Process the ResultSet object
		   if(rs!=null){
			   while(rs.next()){
				   flag=true;
				   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4));
			   }//while
		   }//if
		}//try
		catch (ClassNotFoundException cnf) { // for known Exception
			cnf.printStackTrace();
		} catch (SQLException se) { // for known exception
			se.printStackTrace();
		} catch (Exception e) { // unknown exception
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

		}//finally

	}//main
}//class
