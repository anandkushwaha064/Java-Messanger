package module;
import java.sql.*;  

public class JdbcConnectivity {

	public static void main(String args[]){  
	try
		{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3308/javaproject?characterEncoding=latin1","root","");  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("Select STR_TO_DATE('18-06-1998','%d-%m-%Y')");  	
		while(rs.next())
		System.out.println(rs.getString("STR_TO_DATE('18-06-1998','%d-%m-%Y')"));
		//STR_TO_DATE('17-09-2010','%d-%m-%Y')
		con.close();  

		}
	catch(Exception e)
		{ System.out.println(e);}  
	}  
	}  

