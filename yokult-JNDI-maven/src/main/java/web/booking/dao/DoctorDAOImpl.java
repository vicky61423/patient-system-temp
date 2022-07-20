package web.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.booking.vo.Doctor;

public class DoctorDAOImpl implements DoctorDAO {
	DataSource dataSource;
	
	public DoctorDAOImpl() throws NamingException {
		Context context = new InitialContext();
		DataSource dataSource =(DataSource) context.lookup("java:comp/env/jdbc/Yokult");
		this.dataSource = dataSource;
	}

	public String selectDoctorNameById(int doctorId) {
		String sql = "SELECT DOCTOR_NAME FROM DOCTOR WHERE DOCTOR_ID = ? ;";
		try ( Connection connection =  dataSource.getConnection();){
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, doctorId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return null;
	}

	//select *
	@Override
	public Doctor selectOne(Doctor doctor) {
		String sql = "SELECT DOCTOR_ALPHABET, DOCTOR_ID,  DOCTOR_NAME, DOCTOR_PHOTO, DOCTOR_CERTIFICATE, DOCTOR_EMAIL ,DOCTOR_PASSWARD FROM DOCTOR WHERE DOCTOR_ID = ? ;";
		try ( Connection connection =  dataSource.getConnection();){
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, doctor.getDoctorAlphabet());
			ResultSet rs = ps.executeQuery();
						
			Doctor vo = new Doctor();	
			while(rs.next()) {
				vo.setDoctorAlphabet(rs.getString(1));
				vo.setDoctorId(rs.getInt(2));
				vo.setDoctorName(rs.getString(3));
				vo.setDoctorPhoto(rs.getBytes(4));
				vo.setDoctorCertificate(rs.getString(5));
				vo.setDoctorEmail(rs.getString(6));
				vo.setDoctorPassword(rs.getString(7));
			}
			return vo;
		} catch (SQLException e) {
			System.out.println("get Doctor by id failure");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int update(Doctor doctor) {
		//DOCTOR_ALPHABET = ?, DOCTOR_ID = ?, 沒有 因為自動填入
		String sql = "UPDATE DOCTOR SET   DOCTOR_NAME = ?, DOCTOR_PHOTO = ?, DOCTOR_CERTIFICATE = ?, DOCTOR_EMAIL = ? ,DOCTOR_PASSWARD = ? WHERE DOCTOR_ID = ? ; ";
		try ( Connection connection =  dataSource.getConnection();){
		PreparedStatement ps = connection.prepareStatement(sql);
//		ps.setString(1, doctor.getDoctorAlphabet());
//		ps.setInt(2, doctor.getDoctorId());
		ps.setString(1, doctor.getDoctorName());
		ps.setBytes(2, doctor.getDoctorPhoto());
		ps.setString(3, doctor.getDoctorCertificate());
		ps.setString(4, doctor.getDoctorEmail());
		ps.setString(5, doctor.getDoctorPassword());
		
		int rowcount = ps.executeUpdate();
		
		return rowcount;
		} catch (SQLException e) {
			System.out.println("update Doctor failure in dao");
			e.printStackTrace();
		}
		
		
		return -1;
	}

	@Override
	public int insert(Doctor doctor) {
		//DOCTOR_ALPHABET, DOCTOR_ID,這兩個沒有 會自動填入
		String sql = "INSERT INTO DOCTOR(  DOCTOR_NAME, DOCTOR_PHOTO, DOCTOR_CERTIFICATE, DOCTOR_EMAIL ,DOCTOR_PASSWARD) VALUES ( ?, ?, ?, ?,?) ";
		try ( Connection connection =  dataSource.getConnection();){
		PreparedStatement ps = connection.prepareStatement(sql);
//		ps.setString(1, doctor.getDoctorAlphabet());
//		ps.setInt(2, doctor.getDoctorId());
		ps.setString(1, doctor.getDoctorName());
		ps.setBytes(2, doctor.getDoctorPhoto());
		ps.setString(3, doctor.getDoctorCertificate());
		ps.setString(4, doctor.getDoctorEmail());
		ps.setString(5, doctor.getDoctorPassword());
		
		int rowcount = ps.executeUpdate();
		return rowcount;
		} catch (SQLException e) {
			System.out.println("insert Doctor failure in dao");
			e.printStackTrace();
		}
		return -1;
	}


}
