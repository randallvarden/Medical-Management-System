package naartjie;

import java.util.Date;

public class Naartjie_staff extends Naartjie_person
{
	private String staffNo;
	private String username;
	private String password;
	private Date dateJoined;
	private String role;
	protected Naartjie_staff(String title, String name, String address, String email, int telephone, String dOB,
			char gender, String staffNo, String username, String password, Date dateJoined, String role) {
		super(title, name, address, email, telephone, dOB, gender);
		this.staffNo = staffNo;
		this.username = username;
		this.password = password;
		this.dateJoined = dateJoined;
		this.role = role;
	}
	
	
	protected String getStaffNo() {
		return staffNo;
	}
	protected void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	protected String getUsername() {
		return username;
	}
	protected void setUsername(String username) {
		this.username = username;
	}
	protected String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	protected Date getDateJoined() {
		return dateJoined;
	}
	protected void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}
	protected String getRole() {
		return role;
	}
	protected void setRole(String role) {
		this.role = role;
	}
	
	
	
}
