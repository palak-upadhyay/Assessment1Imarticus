package p1;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.*;
public class Bank 
{
	public static void main(String [] args) 
	{
		Connection con = null;
		Scanner sc = new Scanner(System.in);
		try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assessment1","root","Pass@123");
				int ch=0;
				do
				{
					System.out.println("1 : Login");
					System.out.println("2 : Register");

					ch = sc.nextInt();
			
					if(ch==1)
					{
						System.out.println("enter user name");
						String uname = sc.next();
						System.out.println("enter password");
						String pass = sc.next();
						Statement stmt = con.createStatement();
						//Statement stmt = con.prepareStatement("select * from emp where username="+"\'"+uname+"\'  and pasword="+"\'"+pass+"\'");
						ResultSet rs = stmt.executeQuery("select * from account where username="+"\'"+uname+"\'  and password="+"\'"+pass+"\'");
						int ucount = 0;
						while(rs.next())
						{
							ucount++;
						}
						if(ucount>=1)
						{
							System.out.println("logged in");
						}
						System.out.println("------------------------------------------------------------------");
						System.out.println("1.Add Account");
						System.out.println("2.Check Amount");
						System.out.println("3.Transfer");
						int op = sc.nextInt();
						if(op==1)
						{
							System.out.println("enter name");
							String name = sc.next();
							System.out.println("enter uid");
							int uid = sc.nextInt();
							System.out.println("enter username");
							String username = sc.next();
							System.out.println("enter password");
							String password = sc.next();
							System.out.println("balance");
							int balance = sc.nextInt();
							PreparedStatement pstmt = con.prepareStatement("insert into bank values(?,?,?,?,?)");
							pstmt.setString(1,name);
							pstmt.setInt(2,uid);
							pstmt.setString(3,username);
							pstmt.setString(4,password);
							pstmt.setInt(5,balance);
							pstmt.execute();
						}
						else if(op==2)
						{
							System.out.println("enter user name");
							String usrname = sc.next();
							System.out.println("enter password");
							String pasword = sc.next();
			
							Statement tmt = con.createStatement();
							ResultSet rst = tmt.executeQuery("select * from bank where username="+"\'"+usrname+"\'  and password="+"\'"+pasword+"\'");
						    while(rst.next())
						    {
						    	System.out.println(rst.getInt(5));
						    }
						}
						else if(op==3)
						{
							System.out.println("Tranfer from");
							int sid = sc.nextInt();
							System.out.println("Tranfer to");
							
							int rid = sc.nextInt();
							System.out.println("Amount");
							int amt = sc.nextInt();
							PreparedStatement ps1 = con.prepareStatement("update  bank set balance=balance+? where uid=?");
							PreparedStatement ps2 = con.prepareStatement("update  bank set balance=balance-? where uid=?");
							System.out.println("hii");
							ps1.setInt(1,amt);
							ps2.setInt(1,amt);
							ps1.setInt(2,rid);
							ps2.setInt(2,sid);
							ps1.execute();
							ps2.execute();
							
						}
						
						
					}
					
					
					
					if (ch==2)
					{
						System.out.println("enter first name");
						String fname = sc.next();
						System.out.println("enter last name");
						String lname = sc.next();
						System.out.println("enter user name");
						String uname = sc.next();
						System.out.println("enter password");
						String pass = sc.next();
				
						PreparedStatement pstmt = con.prepareStatement("insert into account values(?,?,?,?)");
						pstmt.setString(1, fname);
						pstmt.setString(2, lname);
						pstmt.setString(3, uname);
						pstmt.setString(4, pass);
						pstmt.execute();
					}
				}while(ch<=2);
		}
		catch(Exception e)
		{
		System.out.println(e);
		}
		sc.close();
	}
}
