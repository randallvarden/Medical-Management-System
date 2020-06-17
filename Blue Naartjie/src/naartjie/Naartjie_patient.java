package naartjie;

public class Naartjie_patient extends Naartjie_person{
	private int patientNo;
	private String allergies = "None";
	private boolean hasMedical = false;
	private int age;
	private String lastScriptDate;
	private String medicalHistory;
	private String prescriptions;

	protected Naartjie_patient(String title, String name, String address, String email, int telephone, String dOB,
			char gender, String allergies, boolean hasMedical, int age, String lastScriptDate,
			String medicalHistory, String prescriptions) 
	{
		super(title, name, address, email, telephone, dOB, gender);
		this.allergies = allergies;
		this.hasMedical = hasMedical;
		this.age = age;
		this.lastScriptDate = lastScriptDate;
		this.medicalHistory = medicalHistory;
		this.prescriptions = prescriptions;
	}
	
	

	protected void setPatientNo(int patientNo) {
		this.patientNo = patientNo;
	}
	
	protected int getPatientNo() {
		return patientNo;
	}
	protected String getAllergies() {
		return allergies;
	}
	protected void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	protected boolean isHasMedical() {
		return hasMedical;
	}
	protected void setHasMedical(boolean hasMedical) {
		this.hasMedical = hasMedical;
	}
	protected int getAge() {
		return age;
	}
	protected void setAge(int age) {
		this.age = age;
	}
	protected String getLastScriptDate() {
		return lastScriptDate;
	}
	protected void setLastScriptDate(String lastScriptDate) {
		this.lastScriptDate = lastScriptDate;
	}
	protected String getMedicalHistory() {
		return medicalHistory;
	}
	protected void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	protected String getPrescriptions() {
		return prescriptions;
	}
	protected void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
	}
	
}
