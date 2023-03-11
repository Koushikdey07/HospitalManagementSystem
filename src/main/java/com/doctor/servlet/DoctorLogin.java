package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;


@SuppressWarnings("serial")
@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		DoctorDao dao = new DoctorDao(DBConnect.getConn());
		Doctor doc = dao.login(email, password);
		
		if(doc != null ){
			session.setAttribute("docObj", doc);
			resp.sendRedirect("doctor/index.jsp");
		}else {
			session.setAttribute("errorMessage", "Invalid email or password");
			resp.sendRedirect("doctor_login.jsp");
		}
		
	}

}
