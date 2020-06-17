package naartjie;

import java.util.Date;

public class Naartjie_userAdmin extends Naartjie_staff {
	
	private Date lastLogin;

	protected Naartjie_userAdmin(String title, String name, String address, String email, int telephone, String dOB,
			char gender, String staffNo, String username, String password, Date dateJoined, String role,
			Date lastLogin) {
		super(title, name, address, email, telephone, dOB, gender, staffNo, username, password, dateJoined, role);
		this.lastLogin = lastLogin;
	}

	protected Date getLastLogin() {
		return lastLogin;
	}

	protected void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	

}
