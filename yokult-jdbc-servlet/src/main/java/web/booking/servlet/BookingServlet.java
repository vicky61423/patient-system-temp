package web.booking.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import web.booking.service.BookingServiceImpl;
import web.booking.vo.Patient;
import web.booking.vo.PatientBookingVO;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//開啟跨網域，html才能接收到servlet傳出的東西

		setHeaders(response);
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();
		System.out.println("get request");

//		流程重寫 在這邊用gson把前端包裹打開 判斷裡面是甚麼關鍵字???再寫if判斷式
		
		
		br.close();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//新增掛號資料
	private void receiveBookingRequest(Gson gson, Reader br, String memId) {
		BookingServiceImpl bookingService = new BookingServiceImpl();
//		bookingService.setPatientBooking(String memId, Patient patient);
	}
	
	//回傳醫師上班時間
	private void sendJsonOfDoctorScheduleAndDoctorName(Gson gson, Reader br, Writer out) {
		PatientBookingVO vo = gson.fromJson(br, PatientBookingVO.class);
		System.out.println(vo);
		System.out.println("fromJson to PatientBookingVO");
		//把東西轉成GSON丟出
		BookingServiceImpl bookingService = new BookingServiceImpl();
		try {
			Map<String, Object> map = bookingService.getDoctorScheduleAndDoctorName(vo.getDate1(), vo.getDate2(), vo.getDoctorId());
												//此方式把直接map物件轉JSONTREE 再轉JsonObject
			JsonObject drNameScheduleJsonObject = gson.toJsonTree(map).getAsJsonObject();
//			System.out.println(drNameScheduleJsonObject);
			//送到前端要把JsonObject轉json
			out.append(gson.toJson(drNameScheduleJsonObject));
			
		} catch (NamingException e) {
			System.out.println("NamingException at sendJsonOfDoctorScheduleAndDoctorName()");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException at sendJsonOfDoctorScheduleAndDoctorName()");
		}

		
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
