package web.booking.vo;

import java.io.Serializable;
import java.util.Arrays;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

//@Entity
//@Table(name="DOCTOR")
public class Doctor implements Serializable {
//	@Column(name="DOCTOR_ALPHABET")
	private String doctorAlphabet;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "DOCTOR_ID")
	private Integer doctorId;
//	@Column(name = "DOCTOR_NAME")
	private String doctorName;
//	@Column(name = "DOCTOR_PHOTO")
	private byte[] doctorPhoto;
//	@Column(name = "DOCTOR_CERTIFICATE")
	private String doctorCertificate;
//	@Column(name = "DOCTOR_EMAIL")
	private String doctorEmail;
//	@Column(name = "DOCTOR_PASSWARD")
	private String doctorPassword;

	public Doctor() {
		super();
	}

	public Doctor(String doctorAlphabet, int doctorId, String doctorName, byte[] doctorPhoto, String doctorCertificate,
			String doctorEmail, String doctorPassword) {
		super();
		this.doctorAlphabet = doctorAlphabet;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorPhoto = doctorPhoto;
		this.doctorCertificate = doctorCertificate;
		this.doctorEmail = doctorEmail;
		this.doctorPassword = doctorPassword;
	}

	public String getDoctorAlphabet() {
		return doctorAlphabet;
	}

	public void setDoctorAlphabet(String doctorAlphabet) {
		this.doctorAlphabet = doctorAlphabet;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public byte[] getDoctorPhoto() {
		return doctorPhoto;
	}

	public void setDoctorPhoto(byte[] doctorPhoto) {
		this.doctorPhoto = doctorPhoto;
	}

	public String getDoctorCertificate() {
		return doctorCertificate;
	}

	public void setDoctorCertificate(String doctorCertificate) {
		this.doctorCertificate = doctorCertificate;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public String getDoctorPassword() {
		return doctorPassword;
	}

	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}

	@Override
	public String toString() {
		return "Doctor [doctorAlphabet=" + doctorAlphabet + ", doctorId=" + doctorId + ", doctorName=" + doctorName
				+ ", doctorPhoto=" + Arrays.toString(doctorPhoto) + ", doctorCertificate=" + doctorCertificate
				+ ", doctorEmail=" + doctorEmail + ", doctorPassword=" + doctorPassword + "]";
	}
	
	
	

		
		


}
