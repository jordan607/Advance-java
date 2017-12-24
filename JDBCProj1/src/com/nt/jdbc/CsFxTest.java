/*CREATE OR REPLACE FUNCTION  FX_GET_EMP_DETAILS_BY_NO(NO IN NUMBER,
                                                                                                                   NAME OUT VARCHAR,
                                                                                                                    DESG OUT VARCHAR)RETURN  NUMBER
AS
  SALARY NUMBER;
BEGIN
   SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP WHERE EMPNO=NO;
RETURN SALARY;
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

public class CsFxTest {
  private static final String CALL_FUNCTION="{?=call FX_GET_EMP_DETAILS_BY_NO(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		int no=0;
		CallableStatement cs=null;
		try{
		  //read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null){
			  System.out.println("Enter Emp number::");
			  no=sc.nextInt();
		  }
		  //register JDBC Driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create CallableStatement object
		 if(con!=null)
			 cs=con.prepareCall(CALL_FUNCTION);
		 //register OUT,RETURN params with JDBC types
		 if(cs!=null){
			 cs.registerOutParameter(1,Types.INTEGER);
			 cs.registerOutParameter(3,Types.VARCHAR);
			 cs.registerOutParameter(4,Types.VARCHAR);
		 }
		 //set values to IN Params
		 if(cs!=null){
			 cs.setInt(2, no);
		 }
		 //call PL/SQL PRocedure
		 if(cs!=null)
			 cs.execute();
		 //gather results from OUT,RETURN PARAMS
		 if(cs!=null){
			 System.out.println("Salary::"+cs.getInt(1));
			 System.out.println("Name ::"+cs.getString(3));
			 System.out.println("Desg::"+cs.getString(4));
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
