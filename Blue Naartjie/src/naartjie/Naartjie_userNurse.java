package naartjie;

import java.util.Date;

public class Naartjie_userNurse extends Naartjie_staff
{
	private String pharmacy;

	protected Naartjie_userNurse(String title, String name, String address, String email, int telephone, String dOB,
			char gender, String staffNo, String username, String password, Date dateJoined, String role,
			String pharmacy) {
		super(title, name, address, email, telephone, dOB, gender, staffNo, username, password, dateJoined, role);
		this.pharmacy = pharmacy;
	}

	protected String getPharmacy() {
		return pharmacy;
	}

	protected void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}

}
