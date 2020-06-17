package naartjie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugHandler 
{
	private String sqlStatement;
	private PreparedStatement stmt;
	
	protected Naartjie_drug countDrugs(Naartjie_drug drug) {
		Connection con = Naartjie_dBConnection.createConnection();
		try 
		{
			sqlStatement = "SELECT COUNT(drugId) FROM drug";
			stmt = con.prepareStatement(sqlStatement);
			ResultSet res = stmt.executeQuery();
			res.next();
			drug.setNumberOfDrugs(res.getInt(1));
			con.close();
			return drug;
		}
		catch(SQLException e)
		{
			System.out.println(e.fillInStackTrace());
		}
		
		return null;
	}
	
	protected boolean deleteDrug(Naartjie_drug drug) {
		Connection con = Naartjie_dBConnection.createConnection();
		try 
		{
			sqlStatement = "DELETE FROM drug WHERE drugId = ? OR name = ?";
			stmt = con.prepareStatement(sqlStatement);
			stmt.setString(1, drug.getDrugId());
			stmt.setString(2, drug.getDrugId());
			stmt.executeUpdate();
			con.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.fillInStackTrace());
		}
		
		return false;
		
	}
	
	protected boolean updateDrug(Naartjie_drug drug) 
	{
		Connection con = Naartjie_dBConnection.createConnection();
		try 
		{
			sqlStatement = "UPDATE drug SET name = ?, drugInfo = ? WHERE drugId = ?";
			stmt = con.prepareStatement(sqlStatement);
			stmt.setString(1, drug.getDrugName());
			stmt.setString(2, drug.getDrugDescription());
			stmt.setString(3, drug.getDrugId());
			stmt.executeUpdate();
			con.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.fillInStackTrace());
		}
		
		return false;
	}
	
	
	protected Naartjie_drug findExactDrug(Naartjie_drug drug) 
	{
		Connection con = Naartjie_dBConnection.createConnection();
		try
		{	
			sqlStatement ="SELECT drugId, name, drugInfo FROM drug WHERE drugId=? OR name=?";
			stmt = con.prepareStatement(sqlStatement);
			stmt.setString(1, drug.getDrugId());
			stmt.setString(2, drug.getDrugName());
			ResultSet res = stmt.executeQuery();
			if(res.next()) 
			{
				Naartjie_drug d = new Naartjie_drug(res.getString(1),res.getString(2),res.getString(3));
				con.close();
				return d;
			}
			con.close();
		}
		catch(SQLException e) 
		{
			System.out.println(e.fillInStackTrace());
		}
	
		return null;
	}
	
	protected Naartjie_drug[] findDrugs(Naartjie_drug drug)
	{
		Connection con = Naartjie_dBConnection.createConnection();
	
		try
		{	
			int count = 0;
			sqlStatement ="SELECT drugId, name, drugInfo FROM drug WHERE drugId LIKE '%"+drug.getDrugId()+"%' OR name LIKE '%"+drug.getDrugName()+"%'";
			stmt = con.prepareStatement(sqlStatement);
			ResultSet res = stmt.executeQuery();
			res.last();
			Naartjie_drug[] lists = new Naartjie_drug[res.getRow()];
			res.beforeFirst();
			if(res.next()) {
				res.previous();
				while(res.next())
				{
					Naartjie_drug d = new Naartjie_drug(res.getString(1),res.getString(2),res.getString(3));
					lists[count] = d;
					count++;
				}
				con.close();
				return lists;
			}
		}
		catch(SQLException e) 
		{
			System.out.println(e.fillInStackTrace());
		}
		
		return null;
	}
	
	protected String addDrug(Naartjie_drug drug) {

		Connection con = Naartjie_dBConnection.createConnection();
		try
		{
			
			sqlStatement ="SELECT drugId FROM drug WHERE drugId=? OR name=?";
			stmt = con.prepareStatement(sqlStatement);
			stmt.setString(1, drug.getDrugId());
			stmt.setString(2, drug.getDrugName());
			ResultSet res = stmt.executeQuery();
			if(res.next())
			{
				con.close();
				return "Failed";
				
			}
			else 
			{
				sqlStatement ="INSERT INTO drug (drugId, name, drugInfo) VALUES (?, ?, ?)";
				stmt = con.prepareStatement(sqlStatement);
				stmt.setString(1, drug.getDrugId());
				stmt.setString(2, drug.getDrugName());
				stmt.setString(3, drug.getDrugDescription());
				stmt.executeUpdate();
				con.close();
				return "Inserted";
			}
			
		}
		catch(SQLException e) 
		{
			System.out.println(e.fillInStackTrace());
		}
		return "Failed";
	}
	
	protected Naartjie_drug scriptDrugs(Naartjie_drug drug)
	{
		Connection con = Naartjie_dBConnection.createConnection();
		try
		{
			sqlStatement ="SELECT drugId, name, drugInfo FROM drug WHERE drugId=? OR name=?";
			stmt = con.prepareStatement(sqlStatement);
			stmt.setString(1, drug.getDrugId());
			stmt.setString(2, drug.getDrugId());
			ResultSet res = stmt.executeQuery();
			if(res.next()) 
			{
				drug.setDrugId(res.getString(1));
				drug.setDrugName(res.getString(2));
				drug.setDrugDescription(res.getString(3));
				con.close();
				return drug;
			}
			con.close();
		}
		catch(SQLException e) 
		{
			System.out.println(e.fillInStackTrace());
		}
		drug.setDrugId("Incorrect");
		return drug;
	}
}
