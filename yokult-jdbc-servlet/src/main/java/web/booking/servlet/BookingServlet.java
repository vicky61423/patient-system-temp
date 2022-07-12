package web.booking.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import web.booking.vo.PatientBookingVO;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		response.setContentType();
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();
		System.out.println("get request");

		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	
	
}
