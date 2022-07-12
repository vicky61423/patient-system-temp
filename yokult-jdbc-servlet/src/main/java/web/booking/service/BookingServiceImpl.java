package web.booking.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import web.booking.dao.DoctorDAOJDBC;
import web.booking.dao.DoctorScheduleDAOJDBC;
import web.booking.vo.DoctorSchedule;

public class BookingServiceImpl {
	//例外通常是放在service層 才能夠決定導向到哪裡去 不要在DAOimpl把exception處理掉
	//		
	public BookingServiceImpl() {
	}

	public static void main(String[] args) {
		
	}

	// 回傳 日期區間為? 醫師編號為? 的 醫師姓名、醫師看診日期、醫師時段
	// 組裝日期 時段和姓名
	public Map<String, Object> getDoctorScheduleAndDoctorName(Date date1, Date date2,Integer doctorId) throws NamingException {
		List<DoctorSchedule> listDr = new DoctorScheduleDAOJDBC().selectDoctorSchedule(date1, date2, doctorId);
		String drName = new DoctorDAOJDBC().selectDoctorNameById(doctorId);
		
		Map map = new HashMap<String, Object>();
		map.put("list", listDr);
		map.put("name", drName);
		return map;
	}
	
	public Map<String, Object> getDoctorScheduleAndDoctorName(String date1, String date2,String doctorId) throws NamingException {
//		Integer.valueOf(doctorId);
		List<DoctorSchedule> listDr = new DoctorScheduleDAOJDBC().selectDoctorSchedule(java.sql.Date.valueOf(date1), java.sql.Date.valueOf(date2), Integer.valueOf(doctorId));
		String drName = new DoctorDAOJDBC().selectDoctorNameById(Integer.valueOf(doctorId));
		
		Map map = new HashMap<String, Object>();
		map.put("list", listDr);
		map.put("name", drName);
		return map;
	}

}
