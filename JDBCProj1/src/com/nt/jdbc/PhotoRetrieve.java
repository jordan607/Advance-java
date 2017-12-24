package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoRetrieve {
  private static final String GET_EMP_BY_NO="SELECT * FROM EMPALL WHERE ENO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		OutputStream os=null;
		byte[] buffer=null;
		int bytesRead=0;
		int no=0;
		//read inputs
		
		try{
		 sc=new Scanner(System.in);
		 if(sc!=null){
			 System.out.println("enter Person id::");
			 no=sc.nextInt();
		 }
		 
/*		 //register JDBC driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //Estalblish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
*/
		 //register JDBC driver
		 Class.forName("com.mysql.jdbc.Driver");
		 //Establish the connection
		 con=DriverManager.getConnection("jdbc:mysql:///ntaj49db1","root","root");
		 
		 //create PreparedStatement object
		 if(con!=null)
			 ps=con.prepareStatement(GET_EMP_BY_NO);
		 //set input values to query params
		 if(ps!=null)
			 ps.setInt(1,no);
		 //execute Query
		 if(ps!=null)
			 rs=ps.executeQuery();
		 //process the ResultSet
		 if(rs!=null){
			 if(rs.next()){
				is=rs.getBinaryStream(4);
				 //create OutputStream pointing to Dest file
				 os=new FileOutputStream("new_james.png");
				 //write buffer based logic to complete photo retrieving
				 if(is!=null && os!=null){
					 buffer=new byte[4096];
					 while((bytesRead=is.read(buffer))!=-1){
						 os.write(buffer,0,bytesRead);
					 }//while
				 }//if
				 System.out.println("Photo Retrieved Successfully");
			 }//if
			 else{
				 System.out.println("record not found");
			 }
		 }//if
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
				 if(is!=null)
					 is.close();
				}
				catch(IOException se){
					se.printStackTrace();
				}
			try{
				 if(os!=null)
					 os.close();
				}
				catch(IOException se){
					se.printStackTrace();
				}
			
			try{
				 if(sc!=null)
					 sc.close();
				}
				catch(Exception se){
					se.printStackTrace();
				}
		}//finally
	}//main
}//class
