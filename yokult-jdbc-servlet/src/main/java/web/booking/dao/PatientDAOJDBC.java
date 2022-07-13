package web.booking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PatientDAOJDBC implements PatientDAO {
	DataSource dataSource;
	public PatientDAOJDBC() throws NamingException {
		 DataSource dataSource = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/YOKULT");
		 this.dataSource = dataSource;
	}

	@Override
	public int insertBookingIntoPatient(String memId, String patientIdcard, Date bookingDate, String amPm, Integer bookingNumber, Integer doctorId) {
		String sql = "INSERT INTO PATIENT(MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ID) VALUES (?, ?, ?, ?, ?, ?);";
			try(Connection connection = dataSource.getConnection();) {
				PreparedStatement ps = connection.prepareStatement(sql);
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
