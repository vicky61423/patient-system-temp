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
//@Table(name = "DOCTOR_SCHEDULE")
public class DoctorSchedule implements Serializable {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="SERIAL_NUMBER")
	private Integer serialNumber;
	
//	@Column(name = "DOCTOR_ALPHABET")
	private String doctorAlphabet;
//	@Column(name = "DOCTOR_ID")
	private Integer doctorId;
//	@Column(name = "DOCTOR_SCHEDULE_DATE")
	private java.sql.Date doctorScheduleDate;
//	@Column(name = "DOCTOR_AMPM")
	private String doctorAmpm;
//	@Column(name = "DOCTOR_STATUS")
	private Integer doctorStatus;
	
	public DoctorSchedule() {
		super();
	}
	public DoctorSchedule(Integer serialNumber, String doctorAlphabet, Integer doctorId, Date doctorScheduleDate,
			String doctorAmpm, Integer doctorStatus) {
		super();
		this.serialNumber = serialNumber;
		this.doctorAlphabet = doctorAlphabet;
		this.doctorId = doctorId;
		this.doctorScheduleDate = doctorScheduleDate;
		this.doctorAmpm = doctorAmpm;
		this.doctorStatus = doctorStatus;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
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
	public java.sql.Date getDoctorScheduleDate() {
		return doctorScheduleDate;
	}
	public void setDoctorScheduleDate(java.sql.Date doctorScheduleDate) {
		this.doctorScheduleDate = doctorScheduleDate;
	}
	public String getDoctorAmpm() {
		return doctorAmpm;
	}
	public void setDoctorAmpm(String doctorAmpm) {
		this.doctorAmpm = doctorAmpm;
	}
	public Integer isDoctorStatus() {
		return doctorStatus;
	}
	public void setDoctorStatus(Integer doctorStatus) {
		this.doctorStatus = doctorStatus;
	}
	@Override
	public String toString() {
		return "DoctorSchedule [serialNumber=" + serialNumber + ", doctorAlphabet=" + doctorAlphabet + ", doctorId="
				+ doctorId + ", doctorScheduleDate=" + doctorScheduleDate + ", doctorAmpm=" + doctorAmpm
				+ ", doctorStatus=" + doctorStatus + "]";
	}
		
}
