package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollTest {
   private static final String GET_STUDENTS_QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
		 //register jdbc Driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //Establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		 //create Statement object having type,mode
		 if(con!=null){
			// st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				//	                ResultSet.CONCUR_READ_ONLY);
			 st=con.createStatement(1005,1007);
			 //st=con.createStatement();
			 
		 }
		 //send and execute SQL Query
		 if(st!=null)
			 rs=st.executeQuery(GET_STUDENTS_QUERY);
		 //process the ResultSet obj
		 System.out.println("Records (Top--Bottom)");
		 if(rs!=null){
			 while(rs.next()){
				 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			 }//while
		 }//if
		 System.out.println("------------------");
		 System.out.println("Records (Bottom----Top)");
		 if(rs!=null){
			 rs.afterLast();
			 while(rs.previous()){
				 System.out.println(rs.getInt(1)+" "+rs.getString(2)+rs.getString(3));
			 }
		 }
		 
		 //first record
		 rs.first();
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+" "+rs.getString(2)+rs.getString(3));
		 //last record
		 rs.last();
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+" "+rs.getString(2)+rs.getString(3));
		 //absolute(-) method
		 rs.absolute(4);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+" "+rs.getString(2)+rs.getString(3));
		//absolute(-) method
		 rs.absolute(-3);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+" "+rs.getString(2)+rs.getString(3));
		//relative(-) method
		 rs.relative(2);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+" "+rs.getString(2)+rs.getString(3));
		//relative(-) method
		 rs.relative(-5);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+" "+rs.getString(2)+rs.getString(3));
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
