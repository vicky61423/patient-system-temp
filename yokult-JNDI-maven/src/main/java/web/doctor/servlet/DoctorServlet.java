package web.doctor.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import web.booking.service.DoctorServiceIImpl;
import web.booking.vo.Doctor;
import web.booking.vo.DoctorChartVO;
import web.booking.vo.Patient;

@WebServlet("/api/0.01/doctor/*")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DoctorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//開啟跨網域，html才能接收到servlet傳出的東西
		setHeaders(response);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();
		
//		out.append(gson.toJson(getDrPatientNames(gson, br)));
//		out.append(gson.toJson(getDrPatientDates(gson, br)));
		
		
		
		
		
		br.close();
		out.close();
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//回傳index_doctor_chart.html 的 病患姓名textbox
	private JsonObject getDrPatientNames(Gson gson, Reader br) {
		DoctorServiceIImpl doctorServiceIImpl = new DoctorServiceIImpl();
		Doctor doctor = gson.fromJson(br, Doctor.class);
		JsonObject jsonObject = new JsonObject();
		try {
			Set<String> set = doctorServiceIImpl.returnDrPatientNames(doctor);
			if (set.size()> 0) {
				jsonObject.addProperty("msg", "get patient names success");
				jsonObject.add("NameSet", gson.toJsonTree(set, new TypeToken<Set<String>>() {}.getType()).getAsJsonArray());
			}
			
		} catch (NamingException e) {
			System.out.println("getDrPatientNames failure");
			jsonObject.addProperty("msg", "getDrPatientNames failure");
			e.printStackTrace();
		}
		
		return jsonObject;
	}

	//回傳index_doctor_chart.html 的 看診日期選單
	private JsonObject getDrPatientDates(Gson gson, Reader br) {
		DoctorServiceIImpl doctorServiceIImpl = new DoctorServiceIImpl();
		DoctorChartVO doctorChartVO = gson.fromJson(br, DoctorChartVO.class);
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		doctor.setDoctorId(doctorChartVO.getDoctorId());
		patient.setMemId(doctorChartVO.getMemId());
		System.out.println(patient);		
		JsonObject jsonObject = new JsonObject();
		try {
			List<Date> list = doctorServiceIImpl.returnDrPatientDates(doctor, patient);
			if (list.size()> 0) {
				jsonObject.addProperty("msg", "get patient dates success");
				jsonObject.add("list", gson.toJsonTree(list, new TypeToken<List<Date>>() {}.getType()).getAsJsonArray());
			}
			
		} catch (NamingException e) {
			System.out.println("getDrPatientDates failure");
			jsonObject.addProperty("msg", "getDrPatientDates failure");
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	private void setHeaders(HttpServletResponse response) {
		// 重要
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		// 重要
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");
	}

}
