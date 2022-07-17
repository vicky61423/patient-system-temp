package web.booking.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import web.booking.vo.Doctor;
import web.booking.vo.DoctorSchedule;

public interface DoctorScheduleDAO {

	//查詢列出日期區間為7/13~7/17的醫師編號為1、醫生狀態為1
	//的 醫師姓名、醫師看診日期、醫師時段	
//	public List<Entry<Doctor, DoctorSchedule>> selectDoctorSchedule(Date date1, Date date2, Integer doctorId);
	
	//查詢列出日期區間為7/13~7/17的醫師編號為1、醫生狀態為1
		//的醫師看診日期、醫師時段	
	public List<DoctorSchedule> selectDoctorSchedule(Date date1, Date date2, Integer doctorId);
}
