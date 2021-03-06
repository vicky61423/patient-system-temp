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

import web.booking.vo.Patient;

public class PatientDAOImpl implements PatientDAO {
	DataSource dataSource;
	public PatientDAOImpl() throws NamingException {
		 DataSource dataSource = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/Yokult");
		 this.dataSource = dataSource;
	}

	@Override
	public int insertBookingIntoPatient(String memId, Patient patient) {
		String sql = "INSERT INTO PATIENT(MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ID) VALUES (?, ?, ?, ?, ?, ?);";
			try(Connection connection = dataSource.getConnection();) {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, memId);
				ps.setString(2, patient.getPatientIdcard());
				ps.setDate(3, patient.getBookingDate());
				ps.setString(4, patient.getAmPm());
				ps.setInt(5, patient.getBookingNumber());
				ps.setInt(6, patient.getDoctorId());
				return ps.executeUpdate();

			} catch (SQLException e) {
				System.out.println("insertBookingIntoPatient failure");
				e.printStackTrace();
				return -1;
			}
	}

	@Override
	public List<Patient> selectPatientBypatientIdcard(String patientIdcard, Integer checkinCondition) {
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
			
	}

	@Override
	//(??????/??????)????????????????????????A123456788 ?????????????????????=?? ???????????????=?
	// ??????????????????????????????A123456788 ???????????????????????=? ???????????????? =1
	// ??????????????????????????????A123456788 ????????????????????? =?????? ???????????????=1
	public int updatePatientCheckinConditionByBookingDate(Patient patient) {
		String sql = "UPDATE PATIENT SET CHECKIN_CONDITION = ? WHERE PATIENT_IDCARD = ? AND BOOKING_DATE = ? ;";
			try (Connection connection = dataSource.getConnection()){
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, patient.getCheckinCondition());
				ps.setString(2, patient.getPatientIdcard());
				ps.setDate(3, patient.getBookingDate());
				System.out.println("in updatePatientCheckinConditionByBookingDate : "+ patient);
				return ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("selectPatientBypatientIdcard failure");
				e.printStackTrace();
			}
			return -1;
			
	}

	@Override
	public int selectCountByDoctor(Date date, int doctorId) {
		//SELECT COUNT(1) FROM PATIENT WHERE DOCTOR_ID = ? AND BOOKING_DATE = ? ;
		String sql = "SELECT COUNT(1) FROM PATIENT WHERE DOCTOR_ID = ? AND BOOKING_DATE = ? ;";
		try( Connection connection = dataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, doctorId);
			ps.setDate(2, date);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}



}
