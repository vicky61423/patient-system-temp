package web.booking.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.taglibs.standard.lang.jstl.test.ParserTest;

import web.booking.dao.DoctorDAOImpl;
import web.booking.dao.DoctorScheduleDAOImpl;
import web.booking.dao.PatientDAOImpl;
import web.booking.vo.DoctorSchedule;
import web.booking.vo.Patient;

public class BookingServiceImpl implements BookingService {
	//例外通常是放在service層 才能夠決定導向到哪裡去 不要在DAOimpl把exception處理掉
	//		
	public BookingServiceImpl() {
	}
	
	//patient checkin方法 成功回傳1 失敗回傳-1
	public int patientCheckIn(Patient patient) throws NamingException {
		PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
		patient.setCheckinCondition(1);
//		patient.setBookingDate();
		System.out.println("patientCheckIn at setCheckinCondition"+patient.getCheckinCondition());
		int result = patientDAOImpl.updatePatientCheckinConditionByBookingDate(patient);
		System.out.println("patientCheckIn int "+ result);
		
		return result;
	}
	//組裝會員編號和要booking的時段，並回傳是否新增成功 把object資料拿出來
	//在這邊計算掛幾號
	@Override
	public int setPatientBooking(String memId, Patient patient) throws NamingException {
		PatientDAOImpl patientDAO = new PatientDAOImpl();
		//先查詢是否有此筆掛號
		
		//要先拿到patient中 某醫師某時段總共幾人
	 	int patientCount = patientDAO.selectCountByDoctor(patient.getBookingDate(), patient.getDoctorId());
		// 得到當天有幾人掛號
	 	if(patientCount != -1) {
			patient.setBookingNumber(patientCount+1);
		}
	 	int rowcount = patientDAO.insertBookingIntoPatient(memId, patient);
		if (rowcount == -1) {
			return -1;
		} else {
			return patient.getBookingNumber();
		}
	}
	
	// 組裝日期 醫師有上班的時段和姓名
	@Override
	public Map<String, Object> getDoctorScheduleAndDoctorName(Date date1, Date date2,Integer doctorId) throws NamingException {
		List<DoctorSchedule> listDr = new DoctorScheduleDAOImpl().selectDoctorSchedule(date1, date2, doctorId);
		String drName = new DoctorDAOImpl().selectDoctorNameById(doctorId);
		
		Map map = new HashMap<String, Object>();
		map.put("list", listDr);
		map.put("name", drName);
		return map;
	}
	
	// Overloading組裝日期 醫師有上班的時段和姓名
	@Override
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
