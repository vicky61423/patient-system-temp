package web.booking.vo;

import java.io.Serializable;
import java.sql.Date;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;


//@Entity
//@Table(name = "PATIENT")
public class Patient implements Serializable {
	//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "SERIAL_NUMBER")
	private Integer serialNumber;
	
//	@Column(name = "MEMID")
	private String memId;
	
//	@Column(name = "PATIENT_IDCARD")
	private String patientIdcard;
	
//	@Column(name = "BOOKING_DATE")
	private java.sql.Date bookingDate;
	
//	@Column(name = "AMPM")
	private String amPm;
	
//	@Column(name = "BOOKING_NUMBER")
	private Integer bookingNumber;
	
//	@Column(name = "DOCTOR_ALPHABET")
	private String doctorAlphabet;
	
//	@Column(name = "DOCTOR_ID")
	private Integer doctorId;
	
//	@Column(name = "CHECKIN_CONDITION")
	private Integer checkinCondition;
	
//	@Column(name = "CHART")
	private String chart;
	
	public Patient() {
		super();
	}

	//has serialNumber
	public Patient(Integer serialNumber, String memId, String patientIdcard, Date bookingDate, String amPm,
			Integer bookingNumber, String doctorAlphabet, Integer doctorId, Integer checkinCondition, String chart) {
		super();
		this.serialNumber = serialNumber;
		this.memId = memId;
		this.patientIdcard = patientIdcard;
		this.bookingDate = bookingDate;
		this.amPm = amPm;
		this.bookingNumber = bookingNumber;
		this.doctorAlphabet = doctorAlphabet;
		this.doctorId = doctorId;
		this.checkinCondition = checkinCondition;
		this.chart = chart;
	}
	// no serialNumber
	public Patient(String memId, String patientIdcard, Date bookingDate, String amPm, Integer bookingNumber,
			String doctorAlphabet, Integer doctorId, Integer checkinCondition, String chart) {
		super();
		this.memId = memId;
		this.patientIdcard = patientIdcard;
		this.bookingDate = bookingDate;
		this.amPm = amPm;
		this.bookingNumber = bookingNumber;
		this.doctorAlphabet = doctorAlphabet;
		this.doctorId = doctorId;
		this.checkinCondition = checkinCondition;
		this.chart = chart;
	}

	@Override
	public String toString() {
		return "Patient [serialNumber=" + serialNumber + ", memId=" + memId + ", patientIdcard=" + patientIdcard
				+ ", bookingDate=" + bookingDate + ", amPm=" + amPm + ", bookingNumber=" + bookingNumber
				+ ", doctorAlphabet=" + doctorAlphabet + ", doctorId=" + doctorId + ", checkinCondition="
				+ checkinCondition + ", chart=" + chart + "]";
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPatientIdcard() {
		return patientIdcard;
	}

	public void setPatientIdcard(String patientIdcard) {
		this.patientIdcard = patientIdcard;
	}

	public java.sql.Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(java.sql.Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getAmPm() {
		return amPm;
	}

	public void setAmPm(String amPm) {
		this.amPm = amPm;
	}

	public Integer getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(Integer bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getDoctorAlphabet() {
		return doctorAlphabet;
	}

	public void setDoctorAlphabet(String doctorAlphabet) {
		this.doctorAlphabet = doctorAlphabet;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getCheckinCondition() {
		return checkinCondition;
	}

	public void setCheckinCondition(Integer checkinCondition) {
		this.checkinCondition = checkinCondition;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}

	
	

}
