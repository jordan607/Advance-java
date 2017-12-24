package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterMetaDataTest {
  private static final String INSERT_STUDENT="INSERT INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ParameterMetaData pmd=null;
		int count=0;
		try{
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:odbc:oradsn","System","manager");
		  //create PreparedStatement object
		  if(con!=null)
			  ps=con.prepareStatement(INSERT_STUDENT);
		  //create ParameterMetaData
		  if(ps!=null)
			  pmd=ps.getParameterMetaData();
		  //get parameter count and other details..
		  if(pmd!=null){
			  count=pmd.getParameterCount();
			for(int i=1;i<=count;++i){
				System.out.println("parameter number::"+i);
				System.out.println("parameter mode::"+pmd.getParameterMode(i));
				System.out.println("parameter type::"+pmd.getParameterTypeName(i));
				System.out.println("parmaeter is Signed::"+pmd.isSigned(i));
				System.out.println("parameter is nullable::"+pmd.isNullable(i));
			}//for
		  }//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
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
		}
	}//main

}
