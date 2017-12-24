package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.StudentBO;

public class StudentDAOImpl implements StudentDAO {
	private static final String INSERT_LAYERED_STUDENT_QUERY="INSERT INTO LAYERED_STUDENT VALUES(?,?,?,?,?)";
	private static final String  GET_SNO_SEQ="SELECT LAYERED_SNO_SEQ.NEXTVAL FROM DUAL";
	
	/*  @Resource(name="DsJndi")
	  private DataSource ds;*/
	
	private Connection getPooledConnection()throws Exception{
		  InitialContext ic=null;
		  DataSource ds=null;
		  Connection con=null;
		  //create InitaialContext object
		  ic=new InitialContext();
		  //get DataSource obj from Jndi Registry
		  ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		  //get Pooled con obj from jdbc con pool
		  con=ds.getConnection();
		  return con;
	}
	
	public  int  getSnoUsingSequence()throws  Exception{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
		//get jdbc con obj from jdbc con pool
		con=getPooledConnection();
		//create PrepaparedStement object
		ps=con.prepareStatement(GET_SNO_SEQ);
		//execute Query
		rs=ps.executeQuery();
		if(rs.next())
			no=rs.getInt(1);
		
		rs.close();
		ps.close();
		con.close();
		
		return no;
	}

	@Override
	public int insert(StudentBO bo) throws Exception {
		Connection con=null;
		PreparedStatement  ps=null;
		int result=0;
		
		//get Pooled jdbc con object
		con=getPooledConnection();
		//con=ds.getConnection();
		//create DataSource object
		ps=con.prepareStatement(INSERT_LAYERED_STUDENT_QUERY);
		//set values to query params
		ps.setInt(1,getSnoUsingSequence());
		ps.setString(2,bo.getSname());
		ps.setInt(3,bo.getTotal());
		ps.setFloat(4,bo.getAvg());
		ps.setString(5,bo.getResult());
		//execute Query
		result=ps.executeUpdate();
		ps.close();
		con.close();
		return result; 
	}

}
