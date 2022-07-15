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
import web.booking.vo.Patient;

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
//			System.out.println(dao.selectDoctorSchedule(Date.valueOf("2022-07-12"), Date.valueOf("2022-07-20"),1));
//			System.out.println(dao.insertBookingIntoPatient("TGA001", "F123555666", Date.valueOf("2022-07-15"), "早", 2, 1));
//			System.out.println(dao.selectPatientBypatientIdcard("A123456788", 0));
			System.out.println(dao.updatePatientByBookingDateAndCheckinCondition("F123555666", Date.valueOf("2022-07-15"),2));
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//(刪除/修改)病患身份證字號為A123456788 看診預約日期為=?? 預約狀態為=?
	// 刪除病患身份證字號為A123456788 看診預約日期為??=? 預約狀態為? =1
	// 修改病患身份證字號為A123456788 看診預約日期為 =今天 預約狀態為=1
		public int updatePatientByBookingDateAndCheckinCondition(String patientIdcard, Date bookingDate,Integer checkinCondition ) {
		String sql = "UPDATE PATIENT SET BOOKING_NUMBER = ? WHERE PATIENT_IDCARD = ? AND BOOKING_DATE = ? ;";
			try {
				PreparedStatement ps = this.connection.prepareStatement(sql);
				ps.setInt(1, checkinCondition);
				ps.setString(2, patientIdcard);
				ps.setDate(3, bookingDate);
				return ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("selectPatientBypatientIdcard failure");
				e.printStackTrace();
			}
			return -1;
			
	};

}
