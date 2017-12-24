package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.JobSeekerBO;

public class JobSeekerDAOImpl implements JobSeekerDAO {
	private static final String INSERT_JOBSEEKER_QUERY="INSERT INTO JOBSEEKER_INFO VALUES(?,?,?,?,?,?,?)";
	
	private Connection getPooledConnection()throws Exception{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		//get DataSource obj from Jndi registry
		ic=new InitialContext();
		ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		con=ds.getConnection();
		return con;
	}

	@Override
	public int saveData(JobSeekerBO bo) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
	   //get PooledConnection object
		con=getPooledConnection();
		//create Preparedstatement object
		ps=con.prepareStatement(INSERT_JOBSEEKER_QUERY);
		//set query param values
		ps.setString(1, bo.getName());
		ps.setString(2,bo.getAddrs());
		ps.setInt(3,bo.getAge());
		ps.setInt(4,bo.getExp());
		ps.setString(5,bo.getSkill());
		ps.setString(6,bo.getCity());
		ps.setInt(7, bo.getSalary());
		//execute the  Query
		result=ps.executeUpdate();
		return result;
	}//saveData(-)
}//class
