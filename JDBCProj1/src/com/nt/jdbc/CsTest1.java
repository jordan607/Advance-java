/*create or replace  procedure P_FIRST_PRO(X IN  NUMBER,Y OUT NUMBER)AS
BEGIN
  Y:=X*X;
END;
*/
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



public class CsTest1 {
 private static final String CALL_PROCEDURE="{ CALL P_FIRST_PRO(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int value=0;
		Connection con=null;
		CallableStatement cs=null;
		int result=0;
		try{
         //read inputs			
		 sc=new Scanner(System.in);
		 if(sc!=null){
			 System.out.println("Enter value:::");
			 value=sc.nextInt();
		 }
		 //register JDBC Driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create PreparedStatement object
		 if(con!=null)
			 cs=con.prepareCall(CALL_PROCEDURE);
		 //register OUT params with JDBC types
		 if(cs!=null)
			 cs.registerOutParameter(2,Types.INTEGER);
		 //set Values to IN params
		 if(cs!=null)
			 cs.setInt(1,value);
		 //execute /call PL/SQL Procedure
		 if(cs!=null)
			 cs.execute();
		 //get results from OUT params
		 if(cs!=null)
			result=cs.getInt(2);
		 
		 System.out.println("Square::"+result);
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
