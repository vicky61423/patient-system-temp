package web.booking.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import web.booking.dao.DoctorDAOImpl;
import web.booking.dao.DoctorScheduleDAOImpl;
import web.booking.dao.PatientDAOImpl;
import web.booking.vo.DoctorSchedule;
import web.booking.vo.Patient;

public class BookingServiceImpl {
	//例外通常是放在service層 才能夠決定導向到哪裡去 不要在DAOimpl把exception處理掉
	//		
	public BookingServiceImpl() {
	}

	//組裝會員編號和要booking的時段，並回傳是否新增成功 把object資料拿出來
	public int setPatientBooking(String memId, Patient patient) throws NamingException {
		PatientDAOImpl patientDAOJDBC = new PatientDAOImpl();
		int result = patientDAOJDBC.insertBookingIntoPatient(memId, patient.getPatientIdcard(), patient.getBookingDate(), patient.getAmPm(), patient.getBookingNumber(), patient.getDoctorId());
		return result;
	}
	
	// 組裝日期 醫師有上班的時段和姓名
	public Map<String, Object> getDoctorScheduleAndDoctorName(Date date1, Date date2,Integer doctorId) throws NamingException {
		List<DoctorSchedule> listDr = new DoctorScheduleDAOImpl().selectDoctorSchedule(date1, date2, doctorId);
		String drName = new DoctorDAOImpl().selectDoctorNameById(doctorId);
		
		Map map = new HashMap<String, Object>();
		map.put("list", listDr);
		map.put("name", drName);
		return map;
	}
	
	// Overloading組裝日期 醫師有上班的時段和姓名
	public Map<String, Object> getDoctorScheduleAndDoctorName(String date1, String date2,String doctorId) throws NamingException {
//		Integer.valueOf(doctorId);
		List<DoctorSchedule> listDr = new DoctorScheduleDAOImpl().selectDoctorSchedule(java.sql.Date.valueOf(date1), java.sql.Date.valueOf(date2), Integer.valueOf(doctorId));
		String drName = new DoctorDAOImpl().selectDoctorNameById(Integer.valueOf(doctorId));
		
		Map map = new HashMap<String, Object>();
		map.put("list", listDr);
		map.put("name", drName);
		return map;
	}

}
