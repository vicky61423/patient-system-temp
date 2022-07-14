package web.booking.dao;

import java.sql.Date;
import java.util.List;

import web.booking.vo.Patient;

public interface PatientDAO {
	//新增一筆醫師看診日期=? 醫師看診時段=? 病患身份證字號為=? 醫師編號=? 的掛號資料在PATIENT
	public int insertBookingIntoPatient(String memId, String patientIdcard, Date bookingDate, String amPm, Integer bookingNumber, Integer doctorId) ;
	
	//查詢列出病患身份證字號為?預約狀態為?()的病患table所有欄位
	//病歷也在這裡查
	public List<Patient> selectPatientBypatientIdcard(String patientIdcard, Integer checkinCondition);
	
	//(刪除/修改)病患身份證字號為A123456788 看診預約日期為=?? 預約狀態為=?
	// 刪除病患身份證字號為A123456788 看診預約日期為??=? 預約狀態為? =1
	// 修改病患身份證字號為A123456788 看診預約日期為 =今天 預約狀態為=1
	public int updatePatientCheckinConditionByBookingDate(String patientIdcard, Date bookingDate,Integer checkinCondition);
	
	//update病歷
//	public int updatePatientChartByBySerialNumber(String patientIdcard, Date bookingDate,Integer checkinCondition);
	
		
}
