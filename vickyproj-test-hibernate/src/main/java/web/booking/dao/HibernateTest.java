package web.booking.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.hibernate.HibernateUtil;
import web.booking.vo.DoctorSchedule;

public class HibernateTest {
	private SessionFactory sessionFactory;

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		try {

//			System.out.println(dao.selectStarById(1));
//			System.out.println(dao.selectDoctorNameById(1));
			HibernateTest dao = new HibernateTest(sessionFactory);
			session = dao.getSession();
			Transaction transaction = session.beginTransaction();
			System.out.println(dao.selectDoctorSchedule(Date.valueOf("2022-07-12"), Date.valueOf("2022-07-20"),1));
			
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		
		session.close();
		HibernateUtil.closeSessionFactory();
	}
	
	public HibernateTest(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

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
	}
}
