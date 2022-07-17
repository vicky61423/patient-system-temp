package web.booking.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import web.booking.vo.Patient;

public interface BookingService {

	//組裝會員編號和要booking的時段，並回傳是否新增成功 把object資料拿出來
	public int setPatientBooking(String memId, Patient patient) throws NamingException;

	// 組裝日期 醫師有上班的時段和姓名
	public Map<String, Object> getDoctorScheduleAndDoctorName(Date date1, Date date2, Integer doctorId) throws NamingException;

	// Overloading組裝日期 醫師有上班的時段和姓名
	public Map<String, Object> getDoctorScheduleAndDoctorName(String date1, String date2, String doctorId)
			throws NamingException;
	
	//patient checkin方法 成功回傳1(影響筆數) 失敗回傳-1
	public int patientCheckIn(Patient patient)throws NamingException;
	
	//回傳病人未報到的所有欄位 有的話回傳list 沒有回null
	public List<Patient> getPatientBooking(Patient patient)throws NamingException;
	
	//取消預約
	public int patientCancel(Patient patient) throws NamingException;
	

}