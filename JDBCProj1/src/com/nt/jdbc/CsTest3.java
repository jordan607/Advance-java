/*CREATE OR REPLACE PROCEDURE P_AUTH_PRO(USER IN VARCHAR,
                                        PASS IN VARCHAR,
                                       RESULT OUT VARCHAR)
AS
  CNT NUMBER;
BEGIN
   SELECT   COUNT(*)   INTO  CNT  FROM  USERLIST  WHERE UNAME=USER AND PWD=PASS;
 IF(CNT<>0) THEN
   RESULT:='VALID CREDENTIALS';
  ELSE
  RESULT:='INVALID CREDENTIALS';
 END IF;
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

public class CsTest3 {
 private static final String CALL_PROCEDURE="{CALL P_AUTH_PRO(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String user=null;
		String pass=null;
		Connection con=null; 
		CallableStatement cs=null;
		try{
		 //read inputs
			sc=new Scanner(System.in);
		  if(sc!=null){
			 System.out.println("Enter username::");
			 user=sc.next();
			 System.out.println("Enter Password::");
			 pass=sc.next();
		  }//if
		  //register JDBC driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		  //create CallableStatement object
		  if(con!=null)
			  cs=con.prepareCall(CALL_PROCEDURE);
		  //register OUT params with JDBC types
		  if(cs!=null){
			  cs.registerOutParameter(3,Types.VARCHAR);
		  }
		  //set values to IN params
		  if(cs!=null){
			  cs.setString(1,user);
			  cs.setString(2,pass);
		  }
		  //call PL/SQL PROCEDURE
		  if(cs!=null)
			  cs.execute();
		  //Gather Result FROM OUT params
		  if(cs!=null){
			  System.out.println(cs.getString(3));
		  }
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
