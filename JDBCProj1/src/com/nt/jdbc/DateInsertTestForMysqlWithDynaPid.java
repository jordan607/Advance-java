/*SQL> desc person_tab;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 PID                                                NUMBER(5)
 PNAME                                              VARCHAR2(20)
 DOB                                                DATE
 DOJ                                                DATE
*/
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTestForMysqlWithDynaPid {
  private static final String DATE_INSERT_QUERY="INSERT INTO PERSON_TAB(PNAME,DOB,DOJ) VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0;
		String sname=null;
		String dob=null,doj=null;
		Connection con=null;
		Statement st=null;
		SimpleDateFormat sdf=null;
		java.util.Date udob=null,udoj=null;
		java.sql.Date sdob=null,sdoj=null;
		PreparedStatement ps=null;
		int result=0;
		try{
		 sc=new Scanner(System.in);
		 if(sc!=null){
			 System.out.println("Enter person name::");
			 sname=sc.next();
			 System.out.println("Enter DOB(dd-MM-yyyy");
			 dob=sc.next();
			 System.out.println("Enter DOJ(yyyy-MM-dd");
			 doj=sc.next();
		 }//if
		/* //register jdbc driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");*/
		 
		 //register jdbc driver
		 Class.forName("com.mysql.jdbc.Driver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:mysql:///ntaj49db1","root","root");
		 
		 //convert String date values to java.sql.Date class objs
		    //for DOB
		      //Convert String date value to java.util.Date class object
		      sdf=new SimpleDateFormat("dd-MM-yyyy");
		      if(sdf!=null)
		    	  udob=sdf.parse(dob);
		      //Convert java.util.Date class obj to java.sql.Date class obj
		      sdob=new java.sql.Date(udob.getTime());
		    //for DOJ
		      //Convert String date value to java.sql.Date class object
		      sdoj=java.sql.Date.valueOf(doj);
        //create PrpearedStatement object	      
		   if(con!=null)
			   ps=con.prepareStatement(DATE_INSERT_QUERY);
          //set values query params
		   if(ps!=null){
			   ps.setString(1,sname);
			   ps.setDate(2,sdob);
			   ps.setDate(3,sdoj);
		   }
		 //execute query
		   if(ps!=null)
			result=ps.executeUpdate();
		   //process the result
		   if(result==0)
			   System.out.println("Record not inserted");
		   else
			   System.out.println("Record inserted");
		}//try
		catch (SQLException se) {
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
			  if(ps!=null)
				  ps.close();
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





