package naartjie;

import java.util.Date;

public class Naartjie_userSecretary extends Naartjie_staff
{
	
	private String deskNumber;

	protected Naartjie_userSecretary(String title, String name, String address, String email, int telephone, String dOB,
			char gender, String staffNo, String username, String password, Date dateJoined, String role,
			String deskNumber) {
		super(title, name, address, email, telephone, dOB, gender, staffNo, username, password, dateJoined, role);
		this.deskNumber = deskNumber;
	}

	protected String getDeskNumber() {
		return deskNumber;
	}

	protected void setDeskNumber(String deskNumber) {
		this.deskNumber = deskNumber;
	}
	
}
