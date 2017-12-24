package com.nt.jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ScrollFrame1 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrollFrame1 window = new ScrollFrame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScrollFrame1() {
		initialize();
		initialJdbc();
	}

	private void initialJdbc() {
		try {
			// registr jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create PreparedStatement object
			ps = con.prepareStatement("SELECT SNO,SNAME,SADD FROM STUDENT", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			// excute Query
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// initializeJdbc

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("student Id");
		lblNewLabel.setBounds(49, 42, 77, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student name");
		lblNewLabel_1.setBounds(57, 96, 80, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student Address");
		lblNewLabel_2.setBounds(29, 163, 94, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(162, 39, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(162, 93, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(162, 160, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try{
			 rs.first();
			 textField.setText(rs.getString(1));
			 textField_1.setText(rs.getString(2));
			 textField_2.setText(rs.getString(3));
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			}
		});
		btnNewButton.setBounds(12, 203, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 try{
			   if(!rs.isLast()){	 
			      rs.next();
			   textField.setText(rs.getString(1));
			   textField_1.setText(rs.getString(2));
			   textField_2.setText(rs.getString(3));
			   }//if
			 }//try
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			}
		});
		btnNewButton_1.setBounds(121, 203, 80, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 try{
			  if(!rs.isFirst()){
				  rs.previous();
				  textField.setText(rs.getString(1));
				  textField_1.setText(rs.getString(2));
				  textField_2.setText(rs.getString(3));
			  }//if
			 }//try
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			}
		});
		btnNewButton_2.setBounds(214, 203, 97, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			     rs.last();
			     textField.setText(rs.getString(1));
			     textField_1.setText(rs.getString(2));
			     textField_2.setText(rs.getString(3));
				}//try
				catch(SQLException se){
					se.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(323, 203, 97, 25);
		frame.getContentPane().add(btnNewButton_3);
	}
}
