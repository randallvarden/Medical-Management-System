package naartjie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class adminHandler 
{
		private String sqlStatement;
		private PreparedStatement stmt;

		protected void updateAdmin(Naartjie_userAdmin admin)
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
		
		protected void deleteAdmin(String adminId) 
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
		
		protected void findAdmin(String search, JTable table) 
		{
			Connection con = Naartjie_dBConnection.createConnection();
			try
			{
				sqlStatement ="SELECT staffNo, username, name FROM staff INNER JOIN person ON staff.personId = person.personId WHERE username LIKE '%"+ search+"%'";
				stmt = con.prepareStatement(sqlStatement);
				ResultSet resultset = stmt.executeQuery(sqlStatement);
				
				while(resultset.next())
				{
					String staffno = resultset.getString("staffNo");
					String username = resultset.getString("username");
					String name = resultset.getString("name");
					table.add(table, new Object[] {staffno, username, name});
				}
				con.close();
			}
			catch(SQLException e) 
			{
				System.out.println(e);
			}
		}

	public void fAdmin(String search, JTable table) 
	{
		String sqlStatement;
		PreparedStatement stmt;
		Connection con = Naartjie_dBConnection.createConnection();
		try
		{
			DefaultTableModel model = new DefaultTableModel(new String[] {"StaffNo", "Username","Name"},  0);
			sqlStatement ="SELECT staffNo, username, name FROM staff INNER JOIN person ON staff.personId = person.personId WHERE username LIKE '%"+ search+"%' OR name LIKE'%"+search+"%' OR staffNo LIKE'%"+search+"%'";
			stmt = con.prepareStatement(sqlStatement);
			ResultSet resultset = stmt.executeQuery(sqlStatement);
			while(resultset.next())
			{
				String staffno = resultset.getString("staffNo");
				String username = resultset.getString("username");
				String name = resultset.getString("name");
				model.addRow(new Object[] {staffno, username, name});
			}
			table.setModel(model);
			con.close();
		}
		catch(SQLException e) 
		{
			System.out.println(e);
		}
	}
	
	public String dateJoined()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = Calendar.getInstance().getTime(); 
		return (dateFormat.format(date));
	}
	
	public void insertNurse(String Title, String name, String address, String email, int tel, String DOB, char gender, String staffno, String username, String password, Date Joined, String pharm )
	{
		String role = "Nurse";
		Naartjie_userNurse r = new Naartjie_userNurse(Title, name, address, email, tel, DOB, gender, staffno, username, password, Joined, role, pharm);
		
		Title = r.getTitle();
		name = r.getName();
		address = r.getAddress();
		email = r.getEmail();
		tel = r.getTelephone();
		DOB = r.getdOB();
		gender = r.getGender();
		staffno = r.getStaffNo();
		username = r.getUsername();
		password = r.getPassword();
		Joined = r.getDateJoined();
		pharm = r.getPharmacy();
		String g = gender+"";
		boolean recordAdded = false;

		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = Calendar.getInstance().getTime();
		String doB = dateFormat.format(date);
		Date DoB = null;
		try {
			DoB = new SimpleDateFormat("yyyy-MM-dd").parse(doB);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
	
		java.sql.Date sDOB = new java.sql.Date(DoB.getTime());
		

		
		java.sql.Date sJoined = new java.sql.Date(Joined.getTime());
		
	
		try
		{
			Connection con = Naartjie_dBConnection.createConnection();
			
			String checkID = "SELECT staffNo FROM staff WHERE staffNo = ?";
			PreparedStatement ps = con.prepareStatement(checkID);
			ps.setString(1, staffno);
			ResultSet rs1 = ps.executeQuery();
			if(!rs1.next())
				{
					recordAdded = true;
				}
			
			if(recordAdded)
			{
				String personSqlStament = "INSERT INTO person (title, name, address, email, telephone, dateOfBirth, gender)" + "VALUES(?,?,?,?,?,?,?)";
				PreparedStatement personStmt = con.prepareStatement(personSqlStament);
				personStmt.setString(1, Title);
				personStmt.setString(2, name);
				personStmt.setString(3, address);
				personStmt.setString(4, email);
				personStmt.setInt(5, tel);
				personStmt.setDate(6, sDOB);
				personStmt.setString(7, g);
				personStmt.executeUpdate();
				
				String pID = "SELECT max(personId) FROM person";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(pID);
				int mid =0;
				while(rs.next())
				{
					mid = rs.getInt(1);
				}
				System.out.println(mid);
				
				String staffSqlStatement = "INSERT INTO staff (staffNo, username, password, dateJoined, role, personId)" + "VALUES(?,?,?,?,?,?)";
				PreparedStatement staffStmt = con.prepareStatement(staffSqlStatement);
				staffStmt.setString(1, staffno);
				staffStmt.setString(2, username);
				staffStmt.setString(3, password);
				staffStmt.setDate(4, sJoined);
				staffStmt.setString(5, role);
				staffStmt.setInt(6, mid);
				staffStmt.executeUpdate();
				
				String sqlStatement = "INSERT INTO nurse (staffNo, pharmacy)" + "VALUES(?,?)";
				PreparedStatement stmt = con.prepareStatement(sqlStatement);
				stmt.setString(1, staffno);
				stmt.setString(2, pharm);
				stmt.executeUpdate();
				con.close();
				
				JOptionPane.showMessageDialog(null, name + " has been successfully added to the system", "Successful", JOptionPane.OK_OPTION);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"StaffNo already exists","Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	public void insertDoctor(Naartjie_userDoctor doc)
	//public void insertDoctor(String Title, String name, String address, String email, int tel, String DOB, char gender, String staffno, String username, String password, Date Joined, String speciality, String officeNo)
	{
		String role = "Doctor";
		String Title = doc.getTitle();
		String name = doc.getName();
		String email = doc.getEmail();
		int tel = doc.getTelephone();
		String address = doc.getAddress();
		char gender = doc.getGender();
		String staffno = doc.getStaffNo();
		String username = doc.getUsername();
		String password = doc.getPassword();
		Date Joined = doc.getDateJoined();
		String speciality = doc.getSpeciality();
		String officeNo = doc.getOfficeNumber();
		boolean recordAdded = false;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = Calendar.getInstance().getTime();
		String doB = dateFormat.format(date);
		Date DoB = null;
		try {
			DoB = new SimpleDateFormat("yyyy-MM-dd").parse(doB);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		@SuppressWarnings("deprecation")
		java.sql.Date sDOB = new java.sql.Date(DoB.getDate());
		java.sql.Date sJoined = new java.sql.Date(Joined.getTime());
		
		try
		{
			Connection con = Naartjie_dBConnection.createConnection();
			
			String checkID = "SELECT staffNo FROM staff WHERE staffNo = ?";
			PreparedStatement ps = con.prepareStatement(checkID);
			ps.setString(1, staffno);
			ResultSet rs1 = ps.executeQuery();
			if(!rs1.next())
				{
					recordAdded = true;
				}
			if(recordAdded)
			{
				String personSqlStament = "INSERT INTO person (title, name, address, email, telephone, dateOfBirth, gender)" + "VALUES(?,?,?,?,?,?,?)";
				PreparedStatement personStmt = con.prepareStatement(personSqlStament);
				personStmt.setString(1, Title);
				personStmt.setString(2, name);
				personStmt.setString(3, address);
				personStmt.setString(4, email);
				personStmt.setInt(5, tel);
				personStmt.setDate(6, sDOB);
				personStmt.setString(7, gender+"");
				personStmt.executeUpdate();
				
				String pID = "SELECT max(personId) FROM person";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(pID);
				int mid =0;
				while(rs.next())
				{
					mid = rs.getInt(1);
				}
				
				String staffSqlStatement = "INSERT INTO staff (staffNo, username, password, dateJoined, role, personId)" + "VALUES(?,?,?,?,?,?)";
				PreparedStatement staffStmt = con.prepareStatement(staffSqlStatement);
				staffStmt.setString(1, staffno);
				staffStmt.setString(2, username);
				staffStmt.setString(3, password);
				staffStmt.setDate(4, sJoined);
				staffStmt.setString(5, role);
				staffStmt.setInt(6, mid);
				staffStmt.executeUpdate();
				
				String sqlStatementDoctor = "INSERT INTO doctor (staffNo, speciality, officeNumber)" + "VALUES(?,?,?)";
				PreparedStatement stmtD = con.prepareStatement(sqlStatementDoctor);
				stmtD.setString(1, staffno);
				stmtD.setString(2, speciality);
				stmtD.setString(3, officeNo);
				stmtD.executeUpdate();
				JOptionPane.showMessageDialog(null, name + " has been successfully added to the system");
				con.close();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"StaffNo already exists","Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void insertSecretary(Naartjie_userSecretary secretary)
	{
		String Title = secretary.getTitle();
		String name = secretary.getName();
		String address = secretary.getAddress();
		String email = secretary.getEmail();
		int tel = secretary.getTelephone();
		Date Joined = secretary.getDateJoined();
		char gender = secretary.getGender();
		String staffno = secretary.getStaffNo();
		String username = secretary.getUsername();
		String password = secretary.getPassword();
		String deskNo = secretary.getDeskNumber();
		String role = "Secretary";
		boolean recordAdded = false;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = new Date();
		String doB = dateFormat.format(date);
		Date DoB = null;
		try {
			DoB = new SimpleDateFormat("yyyy-MM-dd").parse(doB);
		} catch (ParseException e1) {
	
			e1.printStackTrace();
		}
		
		java.sql.Date sDOB = new java.sql.Date(DoB.getTime());
		java.sql.Date sJoined = new java.sql.Date(Joined.getTime());
		
		try
		{
			Connection con = Naartjie_dBConnection.createConnection();
			
			String checkID = "SELECT staffNo FROM staff WHERE staffNo = ?";
			PreparedStatement ps = con.prepareStatement(checkID);
			ps.setString(1, staffno);
			ResultSet rs1 = ps.executeQuery();
			if(!rs1.next())
				{
					recordAdded = true;
				}
			if(recordAdded)
			{
				String personSqlStament = "INSERT INTO person (title, name, address, email, telephone, dateOfBirth, gender)" + "VALUES(?,?,?,?,?,?,?)";
				PreparedStatement personStmt = con.prepareStatement(personSqlStament);
				personStmt.setString(1, Title);
				personStmt.setString(2, name);
				personStmt.setString(3, address);
				personStmt.setString(4, email);
				personStmt.setInt(5, tel);
				personStmt.setDate(6, sDOB);
				personStmt.setString(7, gender+"");
				personStmt.executeUpdate();
				
				String pID = "SELECT max(personId) FROM person";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(pID);
				int mid =0;
				while(rs.next())
				{
					mid = rs.getInt(1);
				}
				
				String staffSqlStatement = "INSERT INTO staff (staffNo, username, password, dateJoined, role, personId)" + "VALUES(?,?,?,?,?,?)";
				PreparedStatement staffStmt = con.prepareStatement(staffSqlStatement);
				staffStmt.setString(1, staffno);
				staffStmt.setString(2, username);
				staffStmt.setString(3, password);
				staffStmt.setDate(4, sJoined);
				staffStmt.setString(5, role);
				staffStmt.setInt(6, mid);
				staffStmt.executeUpdate();
				
				String sqlStatementSecretary = "INSERT INTO secretary (staffNo, desknumber)" + "VALUES(?,?)";
				PreparedStatement stmtD = con.prepareStatement(sqlStatementSecretary);
				stmtD.setString(1, staffno);
				stmtD.setString(2, deskNo);
				stmtD.executeUpdate();
				JOptionPane.showMessageDialog(null, name + " has been successfully added to the system");
				
				con.close();
				
			}
			else
			{
				JOptionPane.showMessageDialog(null,"StaffNo already exists","Warning", JOptionPane.WARNING_MESSAGE);
			}
		}	
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void insertAdmin(Naartjie_userAdmin admin)
	{
		String Title = admin.getTitle();
		String name = admin.getName();
		String address = admin.getAddress();
		String email = admin.getEmail();
		int tel = admin.getTelephone();
		Date Joined = admin.getDateJoined();
		char gender = admin.getGender();
		String role = "Admin";
		String username = admin.getUsername();
		String password = admin.getPassword();
		String staffno = admin.getStaffNo();
		Date lastLogin = admin.getLastLogin();
		boolean recordAdded = false;
		
		 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = null;
		String doB = dateFormat.format(date);
		Date DoB = null;
		try {
			DoB = new SimpleDateFormat("yyyy-MM-dd").parse(doB);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.sql.Date sDOB = new java.sql.Date(DoB.getTime());

		java.sql.Date sJoined = new java.sql.Date(Joined.getTime());
		
		try
		{
			Connection con = Naartjie_dBConnection.createConnection();
			
			String checkID = "SELECT staffNo FROM staff WHERE staffNo = ?";
			PreparedStatement ps = con.prepareStatement(checkID);
			ps.setString(1, staffno);
			ResultSet rs1 = ps.executeQuery();
			if(!rs1.next())
				{
					recordAdded = true;
				}
			if(recordAdded)
			{
				String personSqlStament = "INSERT INTO person (title, name, address, email, telephone, dateOfBirth, gender)" + "VALUES(?,?,?,?,?,?,?)";
				PreparedStatement personStmt = con.prepareStatement(personSqlStament);
				personStmt.setString(1, Title);
				personStmt.setString(2, name);
				personStmt.setString(3, address);
				personStmt.setString(4, email);
				personStmt.setInt(5, tel);
				personStmt.setDate(6, sDOB);
				personStmt.setString(7, gender+"");
				personStmt.executeUpdate();
				
				String pID = "SELECT max(personId) FROM person";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(pID);
				int mid =0;
				while(rs.next())
				{
					mid = rs.getInt(1);
				}
				
				String staffSqlStatement = "INSERT INTO staff (staffNo, username, password, dateJoined, role, personId)" + "VALUES(?,?,?,?,?,?)";
				PreparedStatement staffStmt = con.prepareStatement(staffSqlStatement);
				staffStmt.setString(1, staffno);
				staffStmt.setString(2, username);
				staffStmt.setString(3, password);
				staffStmt.setDate(4, sJoined);
				staffStmt.setString(5, role);
				staffStmt.setInt(6, mid);
				staffStmt.executeUpdate();
				
				String sqlStatementSecretary = "INSERT INTO admin (staffNo, lastLogin)" + "VALUES(?,?)";
				PreparedStatement stmtD = con.prepareStatement(sqlStatementSecretary);
				stmtD.setString(1, staffno);
				stmtD.setDate(2, (java.sql.Date) lastLogin);
				stmtD.executeUpdate();
				JOptionPane.showMessageDialog(null, name + " has been successfully added to the system");
				
				con.close();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"StaffNo already exists","Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void editSearch(String ID, JTextField usernameField, JTextField passField, JTextField name, JTextField address , JTextField tel, JTextField special, JTextField pharm)
	{
		String u =null;
		String pass = null;
		String Name = null;
		String Address = null;
		int tele = 0;
		String Special = null;
		String Pharm = null;
		try
		{
			Connection con = Naartjie_dBConnection.createConnection();
			
			String checkID = "SELECT* FROM staff WHERE staffNo = ?";
			PreparedStatement ps = con.prepareStatement(checkID);
			ps.setString(1, ID);
			ResultSet rs1 = ps.executeQuery();
			
			while(rs1.next())
			{
				u = rs1.getString(2);
				pass = rs1.getString(3);
				usernameField.setText(u);
				passField.setText(pass);
			}
			
			String checkPersonID = "SELECT name, title, address, email, telephone FROM staff INNER JOIN person ON staff.personId = person.personId WHERE staffNo = ?";
			PreparedStatement ps2 = con.prepareStatement(checkPersonID);
			ps2.setString(1, ID);
			ResultSet rs2 = ps2.executeQuery();
			
			while(rs2.next())
			{
				Name = rs2.getString("name");
				Address = rs2.getString("address");
				tele = rs2.getInt("telephone");
			}
			name.setText(Name);
			address.setText(Address);
			tel.setText(tele+"");
			
			String checkNurseID = "SELECT pharmacy FROM nurse INNER JOIN staff ON staff.staffNo = nurse.staffNo WHERE staff.staffNo = ?";
			PreparedStatement nursest = con.prepareStatement(checkNurseID);
			nursest.setString(1, ID);
			ResultSet Nrs2 = nursest.executeQuery();
			
			while(Nrs2.next())
			{
				Pharm = Nrs2.getString(1);
			}
			pharm.setText(Pharm);
			
			String checkDoctorID = "SELECT speciality FROM doctor INNER JOIN staff ON staff.staffNo = doctor.staffNo WHERE staff.staffNo = ?";
			PreparedStatement doctorst = con.prepareStatement(checkDoctorID);
			doctorst.setString(1, ID);
			ResultSet Drs2 = doctorst.executeQuery();
			
			while(Drs2.next())
			{
				Special = Drs2.getString(1);
			}
			special.setText(Special);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//return u ;
	}
	
	public Date lastLogin()
	{
		Date date = Calendar.getInstance().getTime(); 
		 java.sql.Date sDate = new java.sql.Date(date.getTime());
		return sDate;
	}
	
	protected void updateDoctor(int search, String u, String pass, String Name, String Address, String tele, String Special, String Pharm)
	{
		String personID =null;
		boolean recordAdded = true;
		try
		{
			Connection con = Naartjie_dBConnection.createConnection();
		
			String checkID = "SELECT staffNo FROM staff WHERE staffNo = ?";
			PreparedStatement ps = con.prepareStatement(checkID);
			ps.setInt(1, search);
			ResultSet rs1 = ps.executeQuery();
			if(rs1.next())
				{
					recordAdded = false;
				}
			if(recordAdded == false)
			{
				try
				{
					int tel = Integer.parseInt(tele);
					String personId = "SELECT personId FROM staff WHERE staffNo = ?"+search;
					PreparedStatement ps2 = con.prepareStatement(personId);
					ps2.setInt(1, search);
					ResultSet rs2 = ps2.executeQuery();
					if(rs2.next())
						{
							personID = rs2.getString("personId");
						}
					
					String paymentSqlStament = "UPDATE person SET name = ?, address = ?, telephone = ? WHERE personId ="+personID;
					PreparedStatement paymentStmt = con.prepareStatement(paymentSqlStament);
					paymentStmt.setString(1, Name);
					paymentStmt.setString(2, Address);
					paymentStmt.setInt(3, tel);
					paymentStmt.executeUpdate();
					System.out.println("cheese");
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"StaffNo Number does not exist","Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
	}
	
	protected void deleteStaff(String ID)
	{
		Connection con = Naartjie_dBConnection.createConnection();
		try
		{
			String personID = null;
			String checkID = "SELECT person.personId FROM staff INNER JOIN person ON staff.personId = person.personId WHERE staff.staffNo = ?";
			PreparedStatement ps = con.prepareStatement(checkID);
			ps.setString(1, ID);
			ResultSet rs1 = ps.executeQuery();
			
			while(rs1.next())
			{
				personID = rs1.getString(1);
			}
			
			sqlStatement ="DELETE FROM staff WHERE staffNo ="+ID;
			stmt = con.prepareStatement(sqlStatement);
			stmt.executeUpdate(sqlStatement);
			
			sqlStatement ="DELETE FROM person WHERE person.personId ="+personID;
			stmt = con.prepareStatement(sqlStatement);
			stmt.executeUpdate(sqlStatement);
		}
		catch(SQLException x)
		{
			x.printStackTrace();
		}
	}
}

/*String searches = "SELECT Animals.animal_id, Animals.animal_name, Animals.description,"
        + "Animals.species_id FROM Animals INNER JOIN Species ON "
        + "Animals.species_id = Species.species_id "
        + "WHERE (Species.Species_name LIKE '%"+speciesName+"%')";
        
         PreparedStatement st = conn.prepareStatement(searches);
                  ResultSet rec = st.executeQuery();
*/
