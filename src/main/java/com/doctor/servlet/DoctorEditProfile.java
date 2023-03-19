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
@WebServlet("/updateEditDoctor")
public class DoctorEditProfile extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullName = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String spec = req.getParameter("spec");
			String email = req.getParameter("email");
			String contactNo = req.getParameter("contact");
			String password = req.getParameter("password");

			int id = Integer.parseInt(req.getParameter("id"));
			
			Doctor d = new Doctor(id,fullName, dob, qualification, spec, email, contactNo, password);
			DoctorDao dao = new DoctorDao(DBConnect.getConn());
			HttpSession session = req.getSession();
			
			if (dao.updateEditDoctor(d)) {
				Doctor updateDoctorDetails = dao.getDoctorById(id);
				session.setAttribute("succMsg", "Doctor Update Sucessfully...");
				session.setAttribute("docObj", updateDoctorDetails);
				resp.sendRedirect("doctor/edit_profile.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong on server");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
