package com.nt.service;

import com.nt.bo.StudentBO;
import com.nt.dao.StudentDAO;
import com.nt.dao.StudentDAOImpl;
import com.nt.dto.StudentDTO;

public class StudentServiceImpl implements StudentService {

	@Override
	public String generateResult(StudentDTO dto) throws Exception {
		int total=0;
		float avg=0.0f;
		StudentBO bo=null;
		String result=null;
		StudentDAO dao=null;
		int count=0;
		//Write b.logic to calculate total,avg and reuslt
		total=dto.getM1()+dto.getM2()+dto.getM3();
		avg=total/3.0f;
		//calc result
		if(dto.getM1()<35 || dto.getM2()<35 || dto.getM3()<35)
			result="Fail";
		else
			result="Pass";
		
		//prpeare StudentBO class object having persistable Data
		bo=new StudentBO();
		bo.setSname(dto.getSname());
		bo.setTotal(total);
		bo.setAvg(avg);
		bo.setResult(result);
		//Create DAO class obj
		dao=new StudentDAOImpl();
		count=dao.insert(bo);
		//process the result
		if(count==0)
			  return  dto.getSname()+"  registration failed--->result::: "+result;
		else
			 return  dto.getSname()+"  registration succeded--->result::: "+result;
	}//generateResult(-)
}//class
