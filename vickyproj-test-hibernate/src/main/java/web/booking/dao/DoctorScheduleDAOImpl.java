package web.booking.dao;

import java.sql.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import web.booking.vo.DoctorSchedule;

public class DoctorScheduleDAOImpl implements DoctorScheduleDAO {
	//Factory存在屬性中
	private SessionFactory sessionFactory;

	private DataSource dataSource;

	//在建構子裡面拿到連線 JNDI
	public DoctorScheduleDAOImpl() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/YOKULT");
	}
	//要使用此DAO方法時 要傳入一個SessionFactory當參數 利用建構子獲得連線
	public DoctorScheduleDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	//到時候Spring課程會把session宣告成屬性
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}	

	@Override //hibernate中變成select * from doctorSchedule where 兩個日期和指定醫師
	public List<DoctorSchedule> selectDoctorSchedule(Date date1, Date date2, Integer doctorId) {
//		String hqlBK = "SELECT doctorScheduleDate, doctorAmpm FROM DoctorSchedule WHERE doctorId = :id AND doctorStatus = 1 AND doctorScheduleDate between :date1 AND :date2 ";
		String hql = "FROM DoctorSchedule WHERE doctorId = :id AND doctorStatus = 1 AND doctorScheduleDate between :date1 AND :date2 ";
		Query<DoctorSchedule> query = this.getSession().createQuery(hql, DoctorSchedule.class);
		query.setParameter("id", doctorId);
		query.setParameter("date1", date1);
		query.setParameter("date2", date2);
		List<DoctorSchedule> list = query.list();
		if(list != null) {
			return list;
		}
		return null;
//		String sql = "SELECT DOCTOR_SCHEDULE_DATE, DOCTOR_AMPM FROM  DOCTOR_SCHEDULE WHERE DOCTOR_ID = ? "
//				+ "AND DOCTOR_STATUS = 1 AND DOCTOR_SCHEDULE_DATE BETWEEN ? AND ? ORDER BY DOCTOR_SCHEDULE_DATE;";
//		try (Connection connection = dataSource.getConnection()){
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setInt(1, doctorId);
//			ps.setDate(2, date1);
//			ps.setDate(3, date2);
//			ResultSet rs = ps.executeQuery();
//			List<DoctorSchedule> list = new ArrayList<DoctorSchedule>();
//			while(rs.next()) {
//				DoctorSchedule ds = new DoctorSchedule();
//				ds.setDoctorScheduleDate(rs.getDate(1));
//				ds.setDoctorAmpm(rs.getString(2));
//				list.add(ds);
//			}
//			return list;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
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
