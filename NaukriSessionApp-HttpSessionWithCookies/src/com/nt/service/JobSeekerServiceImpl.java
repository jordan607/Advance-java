package com.nt.service;

import com.nt.service.JobSeekerService;
import com.nt.bo.JobSeekerBO;
import com.nt.dao.JobSeekerDAO;
import com.nt.dao.JobSeekerDAOImpl;
import com.nt.dto.JobSeekerDTO;

public class JobSeekerServiceImpl implements JobSeekerService {

	@Override
	public String register(JobSeekerDTO dto) throws Exception {
		JobSeekerBO bo=null;
		JobSeekerDAO dao=null;
		int count=0;
		//Convert DTO to BO
		bo=new JobSeekerBO();
		bo.setName(dto.getName());
		bo.setAddrs(dto.getAddrs());
		bo.setAge(dto.getAge());
		bo.setExp(dto.getExp());
		bo.setSkill(dto.getSkill());
		bo.setCity(dto.getCity());
		bo.setSalary(dto.getSalary());
		//use dAO
		dao=new JobSeekerDAOImpl();
		count=dao.saveData(bo);
		//process the result
		if(count==0)
			return "registration Failed";
		else
			return "registration Succded";
	}//method
}//class
