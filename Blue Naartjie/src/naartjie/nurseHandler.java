package naartjie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class nurseHandler 
{
	private String sqlStatement;
	private PreparedStatement stmt;
	
	/*protected void createNurse(String title, String name, String address, String email, int telephone, String dOB,
			char gender, String staffNo, String username, String password, Date dateJoined, String role,
			String pharmacy)
	{
		Naartjie_userNurse nurse = new Naartjie_userNurse(title, name, address, email, telephone, dOB, gender, staffNo, username, password, dateJoined, role, pharmacy);
		insertNurse(nurse);
	}*/
	
	protected void insertNurse(Naartjie_userNurse nurse) 
	{
		Connection con = Naartjie_dBConnection.createConnection();
		try
		{
			sqlStatement ="";
			stmt = con.prepareStatement(sqlStatement);
			stmt.executeUpdate(sqlStatement);
		}
		catch(SQLException e) 
		{
			System.out.println(e);
		}
	}

	protected void updateNurse(Naartjie_userNurse nurse)
	{
		Connection con = Naartjie_dBConnection.createConnection();
		try
		{
			sqlStatement ="";
			stmt = con.prepareStatement(sqlStatement);
			stmt.executeUpdate(sqlStatement);
		}
		catch(SQLException e) 
		{
			System.out.println(e);
		}	
	}
	
	protected void deleteNurse(String staffId)
	{
		Connection con = Naartjie_dBConnection.createConnection();
		try
		{
			sqlStatement ="";
			stmt = con.prepareStatement(sqlStatement);
			stmt.executeUpdate(sqlStatement);
		}
		catch(SQLException e) 
		{
			System.out.println(e);
		}
	}
	
	protected void findNurse(String staffId)
	{
		Connection con = Naartjie_dBConnection.createConnection();
		try
		{
			sqlStatement ="";
			stmt = con.prepareStatement(sqlStatement);
			stmt.executeUpdate(sqlStatement);
		}
		catch(SQLException e) 
		{
			System.out.println(e);
		}
	}
}