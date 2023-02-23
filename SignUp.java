package ex.com;
import java.sql.*;
import java.util.Scanner;
public class SignUp {

	public static void main(String[] args) {
		Scanner s1=new Scanner(System.in);
		System.out.println("Enter emp no");
		int emp=s1.nextInt();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String query="select empno from emp";
		String query1="insert into emp values(?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?user=root&password=root");
			pstmt=con.prepareStatement(query);
			rst=pstmt.executeQuery();
			int k=0;
			while(rst.next()) {
				int a=rst.getInt(1);
				if(a==emp) {
					System.out.println("logged succesfully");
					k++;
					break;
				}
			}
				if(k==0) {
					System.out.println("PLEASE SIGN UP");
					pstmt=con.prepareStatement(query1);
					System.out.println("enter empno");
					int m1=s1.nextInt();
					pstmt.setInt(1,m1);
					System.out.println("enter name");
					String m2=s1.next();
					pstmt.setString(2,m2);
					System.out.println("enter salary");
					Double m3=s1.nextDouble();
					pstmt.setDouble(3,m3);
					int Z=pstmt.executeUpdate();
					System.out.println("Sign up done succesfully"+Z);
				}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null && pstmt!=null && rst!=null) {
				try {
					con.close();
					pstmt.close();
					rst.close();
					System.out.println("All costly resources are closed succesfully");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
