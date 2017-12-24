package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

public class CsFxCursorTest {
   private static final String CALL_FUNCTION="{?=call FX_VIEW_STUDENT_DELETE(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int count=0;
		try{
		 sc=new Scanner(System.in);
		 if(sc!=null){
			 System.out.println("Enter Student number::");
			 no=sc.nextInt();
		 }
		 //register JDBC driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //Establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		 //create CallableStatement object
		 if(con!=null)
			 cs=con.prepareCall(CALL_FUNCTION);
		 //register REURN,OUT parameter with JDBC Types
		 if(cs!=null){
			 cs.registerOutParameter(1,OracleTypes.CURSOR);//return Param
			 cs.registerOutParameter(3, Types.INTEGER);// OUT param
		 }
		 //set value to IN param
		 if(cs!=null)
			 cs.setInt(2,no);
		 //call PL/SQL function
		 if(cs!=null)
			 cs.execute();
		 //gather results from OUT,return params
		 if(cs!=null)
			 rs=(ResultSet) cs.getObject(1);
		 //process the ReusltSet
		 System.out.println("View Record");
		 if(rs!=null){
			 while(rs.next()){
				 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
			 }//while
		 }//if
		 
		 count=cs.getInt(3); //OUT param
		 if(count==0)
			 System.out.println("Record not deleted");
		 else
			 System.out.println("Record  deleted");
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
				if(rs!=null)
					rs.close();
	           }
	           catch(SQLException se){
	        	   se.printStackTrace();
	           }
			
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
