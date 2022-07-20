package web.booking.vo;

import java.io.Serializable;

public class DoctorChartVO implements Serializable {
	private Integer doctorId;
	private String memId;
	private String patientIdcard;
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
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
	public DoctorChartVO(Integer doctorId, String memId, String patientIdcard) {
		super();
		this.doctorId = doctorId;
		this.memId = memId;
		this.patientIdcard = patientIdcard;
	}
	public DoctorChartVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
