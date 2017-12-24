package com.nt.basics;

import java.text.SimpleDateFormat;

public class TestApp {

	public static void main(String[] args)throws Exception {
		
		//Converting String date value to java.util.Date class object
		String s1="32-22-1989"; //dd-MM-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf.parse(s1);
		System.out.println("util date::"+ud1);
		
		//Coverting java.util.Date class obj to java.sql.Date class object
		long ms=ud1.getTime();
		java.sql.Date sqd1=new java.sql.Date(ms);
		System.out.println("sql date::"+sqd1);
		
		//if given String date value is there in yyyy-MM-dd Pattern then
		// it can be converted directly java.sql.Date class obj with out
		//converting to java.util.Date class obk
		String s2="2010-20-34"; //yyyy-MM-dd
		java.sql.Date sqd2=java.sql.Date.valueOf(s2);
		System.out.println("sql date::"+sqd2);
		
		//Converting java.sql.Date class obj to java.util.Date class obj
		java.util.Date ud2=(java.util.Date)sqd2;
		System.out.println("util date::"+ud2);
		
		//Converting java.util.Date class obj to String date value..
		SimpleDateFormat sdf1=new SimpleDateFormat("MM-yyyy-dd");
		String s3=sdf1.format(ud2);
		System.out.println("String date::"+s3);

		
		
	}
}
