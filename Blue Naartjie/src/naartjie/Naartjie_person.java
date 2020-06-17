package naartjie;

public class Naartjie_person {
	
	private String title;
	private String name;
	private String address;
	private String email;
	private int telephone;
	private String dOB;
	private char gender;
	
	protected Naartjie_person(String title, String name, String address, String email, int telephone, String dOB,
			char gender) {
		super();
		this.title = title;
		this.name = name;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		this.dOB = dOB;
		this.gender = gender;
	}
	
	protected Naartjie_person(String title, String name) {
		super();
		this.name = name;
		this.title = title;
	}
	protected String getTitle() {
		return title;
	}
	protected void setTitle(String title) {
		this.title = title;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected String getAddress() {
		return address;
	}
	protected void setAddress(String address) {
		this.address = address;
	}
	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	protected int getTelephone() {
		return telephone;
	}
	protected void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	protected String getdOB() {
		return dOB;
	}
	protected void setdOB(String dOB) {
		this.dOB = dOB;
	}
	protected char getGender() {
		return gender;
	}
	protected void setGender(char gender) {
		this.gender = gender;
	}
	
	
}
