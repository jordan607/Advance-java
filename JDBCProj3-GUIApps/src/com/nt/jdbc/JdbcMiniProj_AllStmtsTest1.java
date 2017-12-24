package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JdbcMiniProj_AllStmtsTest1 extends JFrame implements ActionListener{
	private static final String GET_SNOs="SELECT SNO FROM ALL_STUDENT";
	private static final String GET_STUD_DETAILS_BY_NO="SELECT SNAME,M1,M2,M3 FROM ALL_STUDENT WHERE SNO=?";
	private static final String CALL_PROCEDURE="{call P_FIND_PASS_FAIL(?,?,?,?)}";
	 private  JLabel lno,lname,lm1,lm2,lm3,lresult;
	 private  JTextField tname,tm1,tm2,tm3,tresult;
	 private JComboBox tno;
	 private JButton bDetails,bResult;
	 private Connection con;
	 private Statement st; 
	 private PreparedStatement ps;
	 private CallableStatement  cs;
	 private ResultSet rs=null;
	 
	 //Constructor
	 public  JdbcMiniProj_AllStmtsTest1(){
		 super("Jdbc MiniProj");
		 System.out.println("constructor");
		 setSize(400,400);
		 setBackground(Color.cyan);
		 setLayout(new FlowLayout());
		 //add comps
		 lno=new JLabel("student number::");
		 add(lno);
		 tno=new JComboBox();
		 add(tno);
		 
		 bDetails=new JButton("details");
		 bDetails.addActionListener(this);
		 add(bDetails);
		 
		 
		 lname=new JLabel("student name::");
		 add(lname);
		 tname=new JTextField(10);
		 tname.setEditable(false);
		 add(tname);
		 
		 lm1=new JLabel("Marks1::");
		 add(lm1);
		 tm1=new JTextField(10);
		 tm1.setEditable(false);
		 add(tm1);
		 
		 lm2=new JLabel("Marks2::");
		 add(lm2);
		 tm2=new JTextField(10);
		 tm2.setEditable(false);
		 add(tm2);
		 
		 lm3=new JLabel("Marks3::");
		 add(lm3);
		 tm3=new JTextField(10);
		 tm3.setEditable(false);
		 add(tm3);
		 
		 bResult=new JButton("result");
		 bResult.addActionListener(this);
		 add(bResult);
		 
		 lresult=new JLabel("Result::");
		 add(lresult);
		 tresult=new JTextField(10);
		 tresult.setEditable(false);
		 add(tresult);
		 
		 addWindowListener(new MyWindowAdapter());
		 
		 setVisible(true);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 initialize();
	 }//constructor
	 
	 private void initialize(){
		 System.out.println("initialize()");
		 try{
		  //register JDBC driver
			 Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create Statement object
			 st=con.createStatement();
		  //Get Student numbers to ComboBox	 
			rs=st.executeQuery(GET_SNOs);
			while(rs.next()){
				tno.addItem(rs.getInt(1));
			}
			//create PreparedStatement object
			ps=con.prepareStatement(GET_STUD_DETAILS_BY_NO);
			//create CallableSTateement object
			cs=con.prepareCall(CALL_PROCEDURE);
			cs.registerOutParameter(4,Types.VARCHAR);
		 }//try
		 catch(SQLException se){
			 se.printStackTrace();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }

	public static void main(String[] args) {
		System.out.println("main(-)");
		new JdbcMiniProj_AllStmtsTest1();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("actionPerformed(-)");
		int no=0;
		ResultSet rs=null;
		int m1=0,m2=0,m3=0;
		if(ae.getSource()==bDetails){
			System.out.println("Details button is clicked");
			try{
			 //get selected number from choice box
			 no=(int)tno.getSelectedItem();
			 //set selected item as the query param value
			 ps.setInt(1,no);
			 //execute Query
			 rs=ps.executeQuery();
			 //process the ResultSet
			 if(rs.next()){
				 tname.setText(rs.getString(1));
				 tm1.setText(rs.getString(2));
				 tm2.setText(rs.getString(3));
				 tm3.setText(rs.getString(4));
			 }//if
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}//if
		else{
			System.out.println("Result button is clicked");
			try{
			 //gather text box values(tm1,tm2,tm3)
				m1=Integer.parseInt(tm1.getText());
				m2=Integer.parseInt(tm2.getText());
				m3=Integer.parseInt(tm3.getText());
			  //set values to IN params
				cs.setInt(1,m1);
				cs.setInt(2,m2);
				cs.setInt(3,m3);
			  //execute PL/SQL Procedure
				cs.execute();
			  //gather result from OUT param and set to TextBox
				tresult.setText(cs.getString(4));
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e ){
				e.printStackTrace();
			}
		}//else
		
	}//method

	private class MyWindowAdapter extends WindowAdapter{
		
		@Override
		public void windowClosing(WindowEvent e) {
		   System.out.println("windowClosing(-) method");
		   //close jdbc objs
		   try{
			 if(rs!=null)
				 rs.close();
		   }
		   catch(SQLException se){
			   se.printStackTrace();
		   }
		   try{
				 if(cs!=null)
					 cs.close();
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

		}//method
	}//innner class
  }//outer class
