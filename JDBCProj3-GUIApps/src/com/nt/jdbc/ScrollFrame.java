package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollFrame extends JFrame implements ActionListener {
	private static final String GET_STUDENTS = "SELECT SNO,SNAME,SADD FROM STUDENT";
	private JLabel lno, lname, ladd;
	private JTextField tno, tname, tadd;
	private JButton bFirst, bNext, bLast, bPrevious;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ScrollFrame() {
		System.out.println("Constructor...");
		setTitle("ScrollFrame App");
		setSize(300, 400);
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		// add Comps
		lno = new JLabel("Student Id");
		add(lno);
		tno = new JTextField(10);
		add(tno);

		lname = new JLabel("Student name");
		add(lname);
		tname = new JTextField(10);
		add(tname);

		ladd = new JLabel("Student Addrs");
		add(ladd);
		tadd = new JTextField(10);
		add(tadd);

		bFirst = new JButton("First");
		bFirst.addActionListener(this);
		add(bFirst);

		bNext = new JButton("Next");
		bNext.addActionListener(this);
		add(bNext);

		bPrevious = new JButton("Previous");
		bPrevious.addActionListener(this);
		add(bPrevious);

		bLast = new JButton("Last");
		bLast.addActionListener(this);
		add(bLast);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("windowClosing(-)");
				//close jdbc objs
				try{
				 if(rs!=null)
					 rs.close();
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
					 if(con!=null)
						 con.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
			}//windowClosing(-)
		});

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
	}// constrctor

	private void initialize() {
		System.out.println("initialize() method");
		try {
			// register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create PreparedStatement
			ps = con.prepareStatement(GET_STUDENTS, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// create Scrollable ResultSet object
			rs = ps.executeQuery();
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// initialize()

	public static void main(String[] args) {
		System.out.println("main(-) method");
		new ScrollFrame();
	}// main

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("actionPerformed(-)-->" + ae.getActionCommand());
		boolean flag=false;
		try {
			if (ae.getSource() == bFirst) {
				System.out.println("first Button is clicked");
				rs.first();
				flag=true;
			} else if (ae.getSource() == bNext) {
				System.out.println("Next Button is clicked");
				if(!rs.isLast()){
					rs.next();
					flag=true;
				}
				
			} else if (ae.getSource() == bPrevious) {
				System.out.println("Previous Button is clicked");
				if(!rs.isFirst()){
					rs.previous();
					flag=true;
				}
			} else {
				
				rs.last();
				flag=true;
				System.out.println("Last Button is clicked");
			}
			
			if(flag==true){
				//read record values and set to text boxes
				tno.setText(rs.getString(1));
				tname.setText(rs.getString(2));
				tadd.setText(rs.getString(3));
			}//if

		} // try
		catch (SQLException se) {
			se.printStackTrace();
		}

	}//main
}//class
