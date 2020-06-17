package naartjie;

import java.util.Date;

public class Naartjie_userDoctor extends Naartjie_staff
{
	private String speciality;
	private String officeNumber;
	protected Naartjie_userDoctor(String title, String name, String address, String email, int telephone, String dOB,
			char gender, String staffNo, String username, String password, Date dateJoined, String role,
			String speciality, String officeNumber) {
		super(title, name, address, email, telephone, dOB, gender, staffNo, username, password, dateJoined, role);
		this.speciality = speciality;
		this.officeNumber = officeNumber;
	}
	
	
	protected String getSpeciality() {
		return speciality;
	}
	protected void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	protected String getOfficeNumber() {
		return officeNumber;
	}
	protected void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	
	
}
