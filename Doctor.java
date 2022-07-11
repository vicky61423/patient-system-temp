package web.yokult.vo;

public class Doctor {
	private String doctor_alphabet;
	private int doctor_id;
	private String doctor_name;
	private byte[] doctor_photo;
	private String doctor_certificate;
	private String doctor_email;
	private String doctor_password;
		
	public Doctor() {
	}

	public Doctor(String doctor_alphabet, int doctor_id, String doctor_name, byte[] doctor_photo,
			String doctor_certificate, String doctor_email, String doctor_password) {
		super();
		this.doctor_alphabet = doctor_alphabet;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.doctor_photo = doctor_photo;
		this.doctor_certificate = doctor_certificate;
		this.doctor_email = doctor_email;
		this.doctor_password = doctor_password;
	}

	public String getDoctor_alphabet() {
		return doctor_alphabet;
	}

	public void setDoctor_alphabet(String doctor_alphabet) {
		this.doctor_alphabet = doctor_alphabet;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public byte[] getDoctor_photo() {
		return doctor_photo;
	}

	public void setDoctor_photo(byte[] doctor_photo) {
		this.doctor_photo = doctor_photo;
	}

	public String getDoctor_certificate() {
		return doctor_certificate;
	}

	public void setDoctor_certificate(String doctor_certificate) {
		this.doctor_certificate = doctor_certificate;
	}

	public String getDoctor_email() {
		return doctor_email;
	}

	public void setDoctor_email(String doctor_email) {
		this.doctor_email = doctor_email;
	}

	public String getDoctor_password() {
		return doctor_password;
	}

	public void setDoctor_password(String doctor_password) {
		this.doctor_password = doctor_password;
	}

	
	

}
