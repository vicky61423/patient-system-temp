package web.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.hibernate.HibernateUtil;
import web.booking.vo.Doctor;

public class DoctorDAOImpl implements DoctorDAO {
	private DataSource dataSource;
//Factory存在屬性中
	private SessionFactory sessionFactory;

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		try {
			DoctorDAOImpl dao = new DoctorDAOImpl(sessionFactory);
			session = dao.getSession();
			Transaction transaction = session.beginTransaction();
			System.out.println(dao.selectStarById(1));
			System.out.println(dao.selectDoctorNameById(1));
//Schema-validation: wrong column type encountered in column [DOCTOR_PHOTO] in table [DOCTOR]; 
			//found [longblob (Types#LONGVARBINARY)], but expecting [tinyblob (Types#VARBINARY)]
			
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		
		session.close();
		HibernateUtil.closeSessionFactory();
	}
	
	
//JNDI for JDBC
	public DoctorDAOImpl() throws NamingException {
		Context context = new InitialContext();
		DataSource dataSource =(DataSource) context.lookup("java:comp/env/jdbc/YOKULT");
		this.dataSource = dataSource;
	}
	
//要使用此DAO方法時 要傳入一個SessionFactory當參數 利用建構子獲得連線
	public DoctorDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
//到時候Spring課程會把session宣告成屬性
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
		
	@Override
	public String selectDoctorNameById(Integer doctorId) {
		Query<String> query = this.getSession().createQuery("SELECT doctorName FROM Doctor WHERE doctorId = :drid",String.class);
		query.setParameter("drid", doctorId);
		String drname =  query.uniqueResult();
		if(drname != null) {
			return drname;
		}
		return null;
		
		
//		String sql = "SELECT DOCTOR_NAME FROM DOCTOR WHERE DOCTOR_ID = ? ;";
//		try ( Connection connection =  dataSource.getConnection();){
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setInt(1, doctorId);
//			ResultSet rs = ps.executeQuery();
//			rs.next();
//			return rs.getString(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//				
//		return null;
	}

	@Override
	public Doctor selectStarById(Integer doctorId) {
//****Hibernate
		if(doctorId != null) {
			return this.getSession().get(Doctor.class, doctorId);
		}
		return null;
	}
}
