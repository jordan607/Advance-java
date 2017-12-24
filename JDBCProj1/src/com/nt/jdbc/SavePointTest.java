package com.nt.jdbc;

/*SQL> create table Products (pid number(5) primary key,pname varchar2(20),qty number(5) , price number(8));

Table created.

SQL> create table cust_account(accno number(5) primary key, holder varchar2(20),balance number(9));

Table created.

SQL> insert into products values(1001,'tabe',10,2000);

1 row created.

SQL> insert into products values(1002,'chair',10,3000);

1 row created.

SQL> commit;

Commit complete.

SQL> insert into cust_account values (101,'raja',99999);

1 row created.

SQL> insert into cust_account values (102,'ramesh',44999);

1 row created.

SQL> commit;

Commit complete.
*/
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

public class SavePointTest {
  private static final String BOOKING_QUERY="UPDATE PRODUCTS SET QTY=QTY-1 WHERE pid=?";
  private static final String PAYMENT_QUERY="UPDATE CUST_ACCOUNT SET BALANCE=BALANCE-(SELECT PRICE FROM PRODUCTS WHERE PID=?) WHERE ACCNO=?";
    
	public static void main(String[] args) {
       Scanner sc=null;		
       int pid=0;
       int accno=0;
       Connection con=null;
       PreparedStatement ps1=null,ps2=null;
       int result1=0, result2=0;
       Savepoint sp=null;
		try{
		 //read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Product Id");
				pid=sc.nextInt();
				System.out.println("Enter Account number::");
				accno=sc.nextInt();
			}
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			 //Begin Tx (Enable Tx)
			con.setAutoCommit(false);
			//create Statement objects
			if(con!=null){
				//Orderbooking operations
				ps1=con.prepareStatement(BOOKING_QUERY);
				ps1.setInt(1,pid);
				result1=ps1.executeUpdate();
				
				//Payment Operations in save point area
				sp=con.setSavepoint("p1");
				ps2=con.prepareStatement(PAYMENT_QUERY);
				ps2.setInt(1, pid);
				ps2.setInt(2,accno);
				result2=ps2.executeUpdate();
			}//if
				if(result2==0 && result1!=0){
					con.rollback(sp);
					con.commit();
					System.out.println("Payment failed COD enabled");
				}
				else if(result1!=0 && result2!=0){
					con.commit();
					System.out.println("Payment done Items will be delivered");
				}
				else{
					con.rollback();
					System.out.println("Booking failed..");
				}
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				//close jdbc objs
				try{
					 if(ps1!=null)
						 ps1.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
				try{
					 if(ps2!=null)
						 ps2.close();
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
				
				try{
					 if(sc!=null)
						 sc.close();
					}
					catch(Exception se){
						se.printStackTrace();
					}
			}//finally
	}//main
}//class
