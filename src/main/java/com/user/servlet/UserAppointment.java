package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AppointmentDao;
import com.db.DBConnect;
import com.entity.Appointment;


@SuppressWarnings("serial")
@WebServlet("/userAppointment")
public class UserAppointment extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userid"));
	    String fullName = req.getParameter("fullname");
		String gender = req.getParameter("gender");
		String age = req.getParameter("age");
		String appointDate = req.getParameter("appoint_date");
		String email = req.getParameter("email");
		String contact = req.getParameter("contact");
		String diseases = req.getParameter("diseases");
		int doctorId = Integer.parseInt(req.getParameter("doctorid"));
		String address = req.getParameter("address");
		
		Appointment ap = new Appointment(userId, fullName, gender, age, appointDate, email, contact, diseases, doctorId, address, "Pending");
		AppointmentDao dao =  new AppointmentDao(DBConnect.getConn());
		
		HttpSession session = req.getSession();
		
		if(dao.addAppointment(ap)) {
			session.setAttribute("succMsg", "Appointment Successfully");
			resp.sendRedirect("user_appointment.jsp");
		}else {
			session.setAttribute("errorMsg", "Something went wrong");
			resp.sendRedirect("user_appointment.jsp");
		}
	}
	
}
