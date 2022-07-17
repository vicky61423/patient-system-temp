package web.booking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.mysql.cj.jdbc.exceptions.PacketTooBigException;

import web.booking.vo.Patient;

public class PatientDAOImpl implements PatientDAO {
	private SessionFactory sessionFactory;
	
//JNDI====
	DataSource dataSource;
	public PatientDAOImpl() throws NamingException {
		 DataSource dataSource = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/YOKULT");
		 this.dataSource = dataSource;
	}
//JNDI==end==
	
	public PatientDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}	

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	//新增一筆醫師看診日期=? 醫師看診時段=? 病患身份證字號為=? 醫師編號=? 的掛號資料在PATIENT
	@Override
	public int insertBookingIntoPatient(String memId, String patientIdcard, Date bookingDate, String amPm, Integer bookingNumber, Integer doctorId) {
		//先查PK
		String hql = "SELECT serialNumber FROM Patient WHERE patientIdcard = :id AND bookingDate = :bookingdate";
		Session session = this.getSession();
		Query<Integer> query = session.createQuery(hql, Integer.class);
		query.setParameter("id", patientIdcard);
		query.setParameter("bookingdate", bookingDate);
		Integer pk = query.uniqueResult();
		if(pk == null) {
			Patient vo = new Patient();
			vo.setMemId(memId);
			vo.setPatientIdcard(patientIdcard);
			vo.setBookingDate(bookingDate);
			vo.setAmPm(amPm);
			vo.setBookingNumber(bookingNumber);
			vo.setDoctorId(doctorId);
			return (int)session.save(vo);
		}
		return -1;
		
		
//		String sql = "INSERT INTO PATIENT(MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ID) VALUES (?, ?, ?, ?, ?, ?);";
//			try(Connection connection = dataSource.getConnection();) {
//				PreparedStatement ps = connection.prepareStatement(sql);
//				ps.setString(1, memId);
//				ps.setString(2, patientIdcard);
//				ps.setDate(3, bookingDate);
//				ps.setString(4, amPm);
//				ps.setInt(5, bookingNumber);
//				ps.setInt(6, doctorId);
//				return ps.executeUpdate();
//
//			} catch (SQLException e) {
//				System.out.println("insertBookingIntoPatient failure");
//				e.printStackTrace();
//				return -1;
//			}
	}


	@Override
	public List<Patient> selectPatientBypatientIdcard(String patientIdcard, Integer checkinCondition) {
//		if(patientIdcard != null && checkinCondition != null)
		
//=====JDBC start======
		String sql = "SELECT SERIAL_NUMBER, MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ALPHABET, DOCTOR_ID, CHECKIN_CONDITION ,CHART FROM PATIENT WHERE PATIENT_IDCARD = ? AND CHECKIN_CONDITION = ? ORDER BY BOOKING_DATE";
			try (Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					){
				ps.setString(1, patientIdcard);
				ps.setInt(2, checkinCondition);
				ResultSet rs = ps.executeQuery();
				List<Patient> list = new ArrayList<>(); 
				while(rs.next()) {
					Patient patient = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
					list.add(patient);
				}
				
				return list;
			} catch (SQLException e) {
				System.out.println("selectPatientBypatientIdcard failure");
				e.printStackTrace();
			}
			return null;
//=====JDBC end======
			
	}

	@Override
	//(刪除/修改)病患身份證字號為A123456788 看診預約日期為=?? 預約狀態為=?
	// 刪除病患身份證字號為A123456788 看診預約日期為??=? 預約狀態為? =1
	// 修改病患身份證字號為A123456788 看診預約日期為 =今天 預約狀態為=1
	public int updatePatientCheckinConditionByBookingDate(String patientIdcard, Date bookingDate,Integer checkinCondition) {
		String sql = "UPDATE PATIENT SET CHECKIN_CONDITION = ? WHERE PATIENT_IDCARD = ? AND BOOKING_DATE = ? ;";
			try (Connection connection = dataSource.getConnection()){
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, checkinCondition);
				ps.setString(2, patientIdcard);
				ps.setDate(3, bookingDate);
				return ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("selectPatientBypatientIdcard failure");
				e.printStackTrace();
			}
			return -1;
			
	}



}
