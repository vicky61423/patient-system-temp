package web.booking.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import web.booking.dao.PatientDAOImpl;
import web.booking.vo.Doctor;
import web.booking.vo.Patient;

public class DoctorServiceIImpl {
	//index_doctor_chart  填寫病歷 
	//回傳某醫師所有病人MEMID
	public Set<String> returnDrPatientNames(Doctor doctor) throws NamingException {
		PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
		
		List<Patient> list = filterPatientNames(doctor);
		if(list.size() != 0) {
			Set<String> set = new HashSet<String>();
			for(Patient patient : list) {
				set.add(patient.getMemId());
			}
		return set;
		}
		return null;
	}
	
	//回傳某醫師某病人的   已報到日期
	public List<Date> returnDrPatientDates(Doctor doctor, Patient patient) throws NamingException {
		PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
		List<Patient> list = filterPatientNames(doctor);
		if(list.size() != 0) {
			List<Date> listofDate = new ArrayList<Date>();
			for(Patient vo : list) {
				if(vo.getMemId().equals(patient.getMemId())) {
					listofDate.add(vo.getBookingDate());
				}
			}
		return listofDate;
		}
		return null;
	}
	
	//回傳某醫師某病人已報到日期 的病歷資料
//	public List<Date> returnDrPatientChart(Doctor doctor, Patient patient) throws NamingException {
//		PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
//		
//		List<Patient> list = filterPatientNames(doctor);
//		if(list.size() != 0) {
//			List<Date> listofDate = new ArrayList<Date>();
//			for(Patient vo : list) {
//				if(vo.getMemId().equals(patient.getMemId())) {
//					listofDate.add(vo.getBookingDate());
//				}
//			}
//		return listofDate;
//		}
//		return null;
//	}
//	
	
	
	//回傳某醫師某病人某報到日期所有欄位
	
	
	
	//過濾同樣dr 有報到的病人們 但名字重複
	public List<Patient> filterPatientNames(Doctor doctor) throws NamingException{
		PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
		int drId = doctor.getDoctorId();
		List<Patient> list = patientDAOImpl.selectAll();
		if(list.size()!=0) {
			for (int i = list.size()-1; i >= 0; i--) {
				if((list.get(i).getDoctorId()) != drId || (list.get(i).getCheckinCondition()) != 1) {
					list.remove(i);
				};
			}
			return list;
		}
		return null;
	}
	
//	//過濾 某醫生的已經報到病人們
//	public List<Patient> filterPatientDate(Doctor doctor) throws NamingException{
//		PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
//		List<Patient> list = this.filterPatientNames(doctor);
//		if(list.size()!=0) {
//			for (int i = list.size()-1; i >= 0; i--) {
//				if((list.get(i).getCheckinCondition()) != 1) {
//					list.remove(i);
//				};
//			}
//			return list;
//		}
//		return null;
//	}
	
}
