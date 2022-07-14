package web.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DoctorDAOImpl {
	DataSource dataSource;
	
	public DoctorDAOImpl() throws NamingException {
		Context context = new InitialContext();
		DataSource dataSource =(DataSource) context.lookup("java:comp/env/jdbc/YOKULT");
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


}
