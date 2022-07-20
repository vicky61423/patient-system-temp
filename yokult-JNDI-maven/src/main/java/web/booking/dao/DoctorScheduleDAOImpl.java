package web.booking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import web.booking.vo.Doctor;
import web.booking.vo.DoctorSchedule;

public class DoctorScheduleDAOImpl implements DoctorScheduleDAO {

	private DataSource dataSource;
	
//	 private final String SELECT_DOCTOR_SCHEDULE  = "SELECT D.DOCTOR_NAME, DS.DOCTOR_SCHEDULE_DATE, DS.DOCTOR_AMPM FROM  DOCTOR_SCHEDULE AS DS "
//			+ "JOIN DOCTOR AS D ON D.DOCTOR_ID = DS.DOCTOR_ID "
//			+ "WHERE DS.DOCTOR_STATUS = 1 AND DS.DOCTOR_ID = ? "
//			+ "AND DS.DOCTOR_SCHEDULE_DATE BETWEEN ? AND ? ";
	
	
	
	
	//在建構子裡面拿到連線 JNDI
	public DoctorScheduleDAOImpl() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/Yokult");
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}


	@Override
	public List<DoctorSchedule> selectDoctorSchedule(Date date1, Date date2, Integer doctorId) {
		String sql = "SELECT DOCTOR_SCHEDULE_DATE, DOCTOR_AMPM FROM  DOCTOR_SCHEDULE WHERE DOCTOR_ID = ? "
				+ "AND DOCTOR_STATUS = 1 AND DOCTOR_SCHEDULE_DATE BETWEEN ? AND ? ORDER BY DOCTOR_SCHEDULE_DATE;";
		try (Connection connection = dataSource.getConnection()){
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


	@Override
	public int insert(DoctorSchedule doctorSchedule) {
		String sql = "INSERT INTO DOCTOR_SCHEDULE(DOCTOR_ID, DOCTOR_SCHEDULE_DATE, DOCTOR_AMPM ,DOCTOR_STATUS) VALUES(?, ?, ?, ?);";
		try ( Connection connection =  dataSource.getConnection();){
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, doctorSchedule.getDoctorId());
		ps.setDate(2, doctorSchedule.getDoctorScheduleDate());
		ps.setString(3, doctorSchedule.getDoctorAmpm());
		ps.setInt(4, doctorSchedule.getDoctorStatus());
		
		int rowcount = ps.executeUpdate();
		return rowcount;
		} catch (SQLException e) {
			System.out.println("insert DoctorSchedule failure in dao");
			e.printStackTrace();
		}
		return -1;
	}


	@Override
	public int update(DoctorSchedule doctorSchedule) {
		String sql ="UPDATE DOCTOR_SCHEDULE SET DOCTOR_SCHEDULE_DATE = ?, DOCTOR_AMPM = ? , DOCTOR_STATUS = ? WHERE DOCTOR_ID= ?;";
		try ( Connection connection =  dataSource.getConnection();){
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setDate(1, doctorSchedule.getDoctorScheduleDate());
		ps.setString(2, doctorSchedule.getDoctorAmpm());
		ps.setInt(3, doctorSchedule.getDoctorStatus());
		ps.setInt(4, doctorSchedule.getDoctorId());
		
		int rowcount = ps.executeUpdate();
		return rowcount;
		} catch (SQLException e) {
			System.out.println("update DoctorSchedule failure in dao");
			e.printStackTrace();
		}
		return -1;
	}

	
	

//	@Override
//	public List<Entry<Doctor, DoctorSchedule>> selectDoctorSchedule(Date date1, Date date2, Integer doctorId) {
//		try (Connection connection = dataSource.getConnection()){
//			PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_SCHEDULE);
//			ps.setInt(1, doctorId);
//			ps.setDate(2, date1);
//			ps.setDate(3, date2);
//			ResultSet rs = ps.executeQuery();
//			List<Entry<Doctor, DoctorSchedule>> list = new ArrayList<>();
//			while(rs.next()) {
//				DoctorSchedule dsbean = new DoctorSchedule();
//				Doctor drbean = new Doctor();
//				drbean.setDoctorName(rs.getString("DOCTOR_NAME"));
//				dsbean.setDoctorScheduleDate(rs.getDate("DOCTOR_SCHEDULE_DATE"));
//				dsbean.setDoctorAmpm(rs.getString("DOCTOR_AMPM"));
//				Entry<Doctor, DoctorSchedule> entry = Map.entry(drbean, dsbean);
//				list.add(entry);
//			}
//			System.out.println("selectDoctorSchedule success");
//			System.out.println(list);
//			return list;
//		} catch (SQLException e) {
//			System.out.println("selectDoctorSchedule mistake");
//			e.printStackTrace();
//		}
//		return null;
//	}


}
