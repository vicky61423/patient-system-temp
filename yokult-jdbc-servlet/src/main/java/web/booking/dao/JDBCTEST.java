package web.booking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.booking.vo.DoctorSchedule;

public class JDBCTEST {
	private Connection connection;
	
	public JDBCTEST()  {
	}

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("driver fail");
		}
		
		try(Connection connection1 = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/YOKULT?serverTimezone=Asia/Taipei", "root", "password");
				){
			JDBCTEST dao = new JDBCTEST();
			dao.connection = connection1;
			System.out.println(dao.selectDoctorSchedule(Date.valueOf("2022-07-12"), Date.valueOf("2022-07-20"),1));
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<DoctorSchedule> selectDoctorSchedule(Date date1, Date date2, Integer doctorId) {
		String sql = "SELECT DOCTOR_SCHEDULE_DATE, DOCTOR_AMPM FROM  DOCTOR_SCHEDULE WHERE DOCTOR_ID = ? "
				+ "AND DOCTOR_STATUS = 1 AND DOCTOR_SCHEDULE_DATE BETWEEN ? AND ? ORDER BY DOCTOR_SCHEDULE_DATE;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, doctorId);
			ps.setDate(2, date1);
			ps.setDate(3, date2);
			ResultSet rs = ps.executeQuery();
			List<DoctorSchedule> list = new ArrayList<DoctorSchedule>();
			while(rs.next()) {
				DoctorSchedule ds = new DoctorSchedule();
				ds.setDoctorScheduleDate(rs.getDate(1));
				ds.setDoctorAmpm(rs.getString(2));
				list.add(ds);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
