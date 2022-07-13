package web.booking.dao;

import java.sql.Date;

public interface PatientDAO {
	//新增一筆醫師看診日期=? 醫師看診時段=? 病患身份證字號為=? 醫師編號=? 的掛號資料在PATIENT
	public int insertBookingIntoPatient(String memId, String patientIdcard, Date bookingDate, String amPm, Integer bookingNumber, Integer doctorId) ;
}
