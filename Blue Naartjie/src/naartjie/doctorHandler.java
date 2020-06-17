package naartjie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class doctorHandler 
{
	private String sqlStatement;
	private PreparedStatement stmt;
	
	protected void insertDoctor(Naartjie_userDoctor doctor) 
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

	protected void updateDoctor(Naartjie_userDoctor doctor) 
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
	
	protected void deleteDoctor(Naartjie_userDoctor doctor) 
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
	
	protected void findDoctor(Naartjie_userDoctor doctor) 
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
