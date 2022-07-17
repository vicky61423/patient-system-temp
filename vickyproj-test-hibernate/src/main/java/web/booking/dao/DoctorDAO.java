package web.booking.dao;

import web.booking.vo.Doctor;

public interface DoctorDAO {
	//給醫師編號 拿醫師姓名
	public String selectDoctorNameById(Integer doctorId);	
	
	//給醫師編號 select*
	public Doctor selectStarById(Integer doctorId);	
	

}
