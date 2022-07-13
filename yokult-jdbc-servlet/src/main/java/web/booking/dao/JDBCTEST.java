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
//			System.out.println(dao.selectDoctorSchedule(Date.valueOf("2022-07-12"), Date.valueOf("2022-07-20"),1));
			System.out.println(dao.insertBookingIntoPatient("TGA001", "F123555666", Date.valueOf("2022-07-15"), "早", 2, 1));
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insertBookingIntoPatient(String memId, String patientIdcard, Date bookingDate, String amPm, Integer bookingNumber, Integer doctorId) {
		String sql = "INSERT INTO PATIENT(MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ID) VALUES (?, ?, ?, ?, ?, ?);";
			try {
				PreparedStatement ps = this.connection.prepareStatement(sql);
				ps.setString(1, memId);
				ps.setString(2, patientIdcard);
				ps.setDate(3, bookingDate);
				ps.setString(4, amPm);
				ps.setInt(5, bookingNumber);
				ps.setInt(6, doctorId);
				return ps.executeUpdate();

			} catch (SQLException e) {
				System.out.println("insertBookingIntoPatient failure");
				e.printStackTrace();
				return -1;
			}
	};

}
