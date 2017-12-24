package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelTest {

	public static void main(String[] args) {
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	boolean flag=false;
	try{
		//register jdbc driver
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:odbc:xlsdsn");
		  //create STatement object
		  if(con!=null)
     		  st=con.createStatement();
		  //send and SQL execute Query
		  if(st!=null)
			  rs=st.executeQuery("select * from [Sheet1$]");
		  //Process the ResultSet
		  if(rs!=null){
			  while(rs.next()){
				  flag=true;
				  System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
			  }
			  if(flag==false)
				  System.out.println("Records not found");
			  
			/*  PreparedStatement ps=con.prepareStatement("insert into [Sheet1$] values(?,?,?)");
			  ps.setInt(1,3445);
			  ps.setString(2,"Ramesh");
			  ps.setString(3,"hyd");
			  ps.executeUpdate();*/
			  
			  
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
				  if(st!=null)
					  st.close();
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
			}//finally
	}//main
}//class
