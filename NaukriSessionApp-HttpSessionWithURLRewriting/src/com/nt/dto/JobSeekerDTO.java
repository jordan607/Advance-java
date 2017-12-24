package com.nt.dto;

import java.io.Serializable;

public class JobSeekerDTO implements Serializable {
	private String name;
	private String addrs;
	private int age;
	private  String skill;
	private int exp;
	private String city;
	private int salary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddrs() {
		return addrs;
	}
	public void setAddrs(String addrs) {
		this.addrs = addrs;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

}
