package web.booking.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import web.booking.service.BookingService;
import web.booking.service.BookingServiceImpl;
import web.booking.vo.Patient;
import web.booking.vo.PatientBookingVO;

@WebServlet("/api/0.01/booking/*")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookingServlet() {
        super();
    }
    
    //最後再來分類doXXX
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//開啟跨網域，html才能接收到servlet傳出的東西
		setHeaders(response);
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();
//		BookingServlet servlet = new BookingServlet();???
		
		out.append(gson.toJson(sendJsonOfDoctorScheduleAndDoctorName(gson, br)));
//		out.append(gson.toJson(receiveBookingRequest(gson, br, "TGA001")));
//		out.append(gson.toJson(servlet.patientCheckin(gson, br)));
//		out.append(gson.toJson(bookingQuery(gson, "TGA001")));
//		out.append(gson.toJson(cancelBooking(gson, br)));
		
		
		
		
		br.close();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//新增checkin方法
	private JsonObject patientCheckin(Gson gson, Reader br) {
		//讀進資料
		Patient patient = gson.fromJson(br, Patient.class);
		//patient傳入處理checkin方法
		BookingServiceImpl bookingService = new BookingServiceImpl();
		int result = 0;;
		try {
			result = bookingService.patientCheckIn(patient);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		JsonObject jsonObject = new JsonObject();
		if (result == 1) {
			jsonObject.addProperty("msg", "checkin success");
		} else {
			jsonObject.addProperty("msg", "checkin failure");
		}
		return jsonObject;
	}
	
	//新增掛號資料方法 
	private JsonObject receiveBookingRequest(Gson gson, Reader br, String memId) {
		BookingService bookingService = new BookingServiceImpl();
		Patient patient = gson.fromJson(br, Patient.class);
		int bookingNumber = 0;
		JsonObject jsonObject = new JsonObject();
		try {
			bookingNumber = bookingService.setPatientBooking(memId, patient);
		} catch (NamingException e) {
			System.out.println("receiveBookingRequest failure");
			e.printStackTrace();
		}
		if(bookingNumber != -1) {
			jsonObject.addProperty("msg", "receiveBookingRequest success");
			jsonObject.addProperty("bookingNumber", bookingNumber);
		} else {
			jsonObject.addProperty("msg", "receiveBookingRequest failure");
		}
		System.out.println("receiveBookingRequest finish");
		return jsonObject;
	}
	
	//回傳醫師上班時間
	private JsonObject sendJsonOfDoctorScheduleAndDoctorName(Gson gson, Reader br) {
		PatientBookingVO vo = gson.fromJson(br, PatientBookingVO.class);
		JsonObject jsonObject = new JsonObject();
		//把東西轉成GSON丟出
		BookingService bookingService = new BookingServiceImpl();
		try {
			Map<String, Object> map = bookingService.getDoctorScheduleAndDoctorName(vo.getDate1(), vo.getDate2(), vo.getDoctorId());
												//toJsonTree方式把直接map物件轉JSONTREE 再轉JsonObject
			JsonObject drNameScheduleJsonObject = gson.toJsonTree(map).getAsJsonObject();
//			System.out.println(drNameScheduleJsonObject);
			//送到前端要把JsonObject轉json
			jsonObject.addProperty("msg", "sendSchedule success");
			jsonObject.add("schedule", drNameScheduleJsonObject);
			System.out.println("sendSchedule success");
			return jsonObject;
		} catch (NamingException e) {
			System.out.println("NamingException at sendJsonOfDoctorScheduleAndDoctorName()");
			jsonObject.addProperty("msg", "sendSchedule failure");
			System.out.println("sendSchedule failure");
			e.printStackTrace();
			return jsonObject;
		}
	}
	
	//查詢某會員預約資訊
	private JsonObject bookingQuery(Gson gson, String memId) {
		Patient patient = new Patient();
		patient.setMemId(memId);
		//patient傳給service service再查出預約日期
		BookingServiceImpl bookingServiceImpl = new BookingServiceImpl();
		JsonObject jsonObject = new JsonObject();
		try {
			List<Patient> list = bookingServiceImpl.getPatientBooking(patient);
			if(list != null) {
				jsonObject.addProperty("msg", "bookingQuery sucess");
				jsonObject.add("list", gson.toJsonTree(list, new TypeToken<List<Patient>>() {}.getType()).getAsJsonArray());
				return jsonObject;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		jsonObject.addProperty("msg", "you have no unchecked booking data");
		return jsonObject;
	}
	//取消預約
	
	private JsonObject cancelBooking(Gson gson, Reader br) {
		Patient patient = gson.fromJson(br, Patient.class);
		BookingServiceImpl bookingServiceImpl = new BookingServiceImpl();
		JsonObject jsonObject = new JsonObject();
		//傳入一筆要改變預約的patient
		try {
			int result = bookingServiceImpl.patientCancel(patient);
			if(result == 1) {
				jsonObject.addProperty("msg", "cancel success");
				return jsonObject;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		jsonObject.addProperty("msg", "cancel failure");
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
	
//	@Override
//	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		setHeaders(resp);
//	}
}
