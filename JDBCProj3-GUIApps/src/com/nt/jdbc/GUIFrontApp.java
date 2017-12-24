package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIFrontApp extends JFrame implements ActionListener,WindowListener {
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
   private JLabel lsno,lsname,lsadd,lresult;
   private JTextField tsno,tsname,tsadd;
   private JButton btn;
   private Connection con;
   private PreparedStatement ps=null;
   
   //costructor
   public GUIFrontApp() {
	   System.out.println("GUIFrontApp:: constructor");
	   setTitle("GUI Frame App");
	   setSize(400,400);
	   setLayout(new FlowLayout());
	   //initialize comps
	   lsno=new JLabel("student ID::");
	   add(lsno);
	   
	   tsno=new JTextField(10);
	   add(tsno);
	   
	   lsname=new JLabel("Student name::");
	   add(lsname);
	   
	   tsname=new JTextField(10);
	   add(tsname);
	   
	   lsadd=new JLabel("Student Address");
	   add(lsadd);
	   
	   tsadd=new JTextField(10);
	   add(tsadd);
	   
	   btn=new JButton("register");
	   add(btn);
	   btn.addActionListener(this);
	   
	   lresult=new JLabel("");
	   add(lresult);
	   
	   addWindowListener(this);
	   
	   setVisible(true);
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	   initialize();
     }//constructor
   
   private  void initialize(){
	   System.out.println("initialize()");
	   try{
		   //register jdbc driver
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //Establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		   //create PreparedStatement object
		   ps=con.prepareStatement(STUDENT_INSERT_QUERY);
	   }
	   catch(ClassNotFoundException cnf){
		   cnf.printStackTrace();
	   }
	   catch(SQLException se){
		   se.printStackTrace();
	   }
   }//initialize()
	
	public static void main(String[] args) {
		System.out.println("main(-) method...");
		new GUIFrontApp();
		System.out.println("End  of main(-) method...");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed(-)");
		int no=0;
		String sname=null,sadd=null;
		int result=0;
		try{
		 //read text box values
		 no=Integer.parseInt(tsno.getText());
		 sname=tsname.getText();
		 sadd=tsadd.getText();
		 //set values to Query params
		 ps.setInt(1,no);
		 ps.setString(2,sname);
		 ps.setString(3,sadd);
		 //execute Query
		 result=ps.executeUpdate();
		 if(result==0){
			 lresult.setForeground(Color.RED);
			 lresult.setText("Registration failed");
		    }
		 else{
			 lresult.setForeground(Color.GREEN);
			 lresult.setText("Registration Succeded");
		 }
		}
		catch(SQLException se){
			 lresult.setForeground(Color.RED);
			lresult.setText("Registration failed");
		}
		catch(Exception ex){
			 lresult.setForeground(Color.RED);
			lresult.setText("Registration failed");
		}
	}//method

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("window Closing");
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
	}//method

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}//class
