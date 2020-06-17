package naartjie;

public class Naartjie_patientMedicalAid extends Naartjie_patient {

		private String medicalAid;
		private int medicalNo;
		
		protected Naartjie_patientMedicalAid(String title, String name, String address, String email, int telephone,
				String dOB, char gender, String allergies, boolean hasMedical, int age, String lastScriptDate,
				String medicalHistory, String prescriptions, String medicalAid, int medicalNo) 
		{
			super(title, name, address, email, telephone, dOB, gender, allergies, hasMedical, age, lastScriptDate,
					medicalHistory, prescriptions);
			this.medicalAid = medicalAid;
			this.medicalNo = medicalNo;
		}

		protected String getMedicalAid() {
			return medicalAid;
		}

		protected void setMedicalAid(String medicalAid) {
			this.medicalAid = medicalAid;
		}

		protected int getMedicalNo() {
			return medicalNo;
		}

		protected void setMedicalNo(int medicalNo) {
			this.medicalNo = medicalNo;
		}
}
