/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_NO(NO IN NUMBER,
                                                                                                                  NAME OUT VARCHAR,
                                                                                                                   DESG OUT VARCHAR,
                                                                                                                   SALARY OUT NUMBER)
 AS
  BEGIN
   SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP  WHERE EMPNO=NO;
 END;
/
*/

package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest2 {
  private static final String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_NO(?,?,?,?)}";
	public static void main(String[] args) {
	   Scanner sc=null;	
	   int no=0;
	   Connection con=null;
	   CallableStatement cs=null;
		try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
		  System.out.println("Enter Emp no::");
		  no=sc.nextInt();
		}
		 //register JDBC Driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create CallableStatement object
		 if(con!=null)
		  cs=con.prepareCall(CALL_PROCEDURE);
		 //register OUT params with JDBC Types
		 if(cs!=null){
			 cs.registerOutParameter(2,Types.VARCHAR);
			 cs.registerOutParameter(3,Types.VARCHAR);
			 cs.registerOutParameter(4, Types.INTEGER);
		 }
		 //set values to IN params
		 if(cs!=null)
			 cs.setInt(1,no);
		 //call PL/SQL Procedure
		 if(cs!=null)
			 cs.execute();
		 //gather results from OUT params
		 System.out.println("Emp name::"+cs.getString(2));
		 System.out.println("Emp Desg::"+cs.getString(3));
		 System.out.println("Emp Salary::"+cs.getInt(4));
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc objs
           try{			
			if(cs!=null)
				cs.close();
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
           try{			
   			if(sc!=null)
   				sc.close();
              }
              catch(Exception e){
           	   e.printStackTrace();
              }
		}//finally
	}//main
}//class
