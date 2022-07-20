package web.booking.dao;

import web.booking.vo.Doctor;

public interface DoctorDAO {
	//select*
	public Doctor selectOne(Doctor doctor);
	
	public int update(Doctor doctor);
	
	public int insert(Doctor doctor);
	
	//給醫師編號 拿醫師姓名
	public String selectDoctorNameById(int doctorId);	
	

}
