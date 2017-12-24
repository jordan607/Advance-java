/*SQL> select * from  jdbc_account;

      ACNO HOLDER                  BALANCE
---------- -------------------- ----------
       101 raja                       2800
       102 rajesh                    13600
*/
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransferMoneyTx_Test {

	public static void main(String[] args) {
		Scanner sc=null;
		int srcNo=0,destNo=0;
		int amount=0;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try{
		//read inputs	
		 sc=new Scanner(System.in);
		 if(sc!=null){
			 System.out.println("Enter source account number::");
			 srcNo=sc.nextInt();
			 System.out.println("Enter Dest account number::");
			 destNo=sc.nextInt();
			 System.out.println("Enter Amount to Transfer:");
			 amount=sc.nextInt();
		 }//if
		 //register JDBC driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //Enable Transaction management
		  if(con!=null)
			  con.setAutoCommit(false);
		  //create Statement object
		  if(con!=null)
			  st=con.createStatement();
		  //add queries to the batch for (TransferMoney)
		  if(st!=null){
		    //for withdraw operation
		     st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE-"+amount+" WHERE ACNO="+srcNo);
		     //for deposite operation
		     st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE=BALANCE+"+amount+" WHERE ACNO="+destNo);
		  }
		  //execute the Batch
		  if(st!=null){
			 result=st.executeBatch(); 
		  }
		  //Perform Transaction management
		  if(result!=null){
			  for(int i=0;i<result.length;++i){
				  if(result[i]==0){
					  flag=true;
					  break;
				  }//if
			  }//for
		  }//if
		  if(flag==true){
			  con.rollback();
			  System.out.println("Tx Rolled back(Money not tranffered)");
		  }
		  else{
			  con.commit();
			  System.out.println("Tx Committed(Money  tranffered)");  
		  }
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			// close jdbc objs
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} // finally

	}//main
}//class
