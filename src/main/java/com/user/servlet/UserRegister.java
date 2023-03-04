package com.user.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.User;

@WebServlet("/user_register")
public class UserRegister extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullName = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			// Nilanjana Saha  04-03-2023
			password = doHashing(password);
			
			User u = new User(fullName,email,password);
			
			UserDao dao = new UserDao(DBConnect.getConn());
			
			HttpSession session= req.getSession();
			
			
			boolean f=dao.register(u);
			
			if(f) {
				session.setAttribute("successMessage", "Registered successfully");
				resp.sendRedirect("signup.jsp");
			}else {
				session.setAttribute("errorMessage", "Registration failed");
				resp.sendRedirect("signup.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Nilanjana Saha  04-03-2023
	public static String doHashing (String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA");    // create a object of MessageDigest, which follows the MD5 hashing algorithm
			
			messageDigest.update(password.getBytes());  			 // update the object with byte values of password
			
			byte[] resultByteArray = messageDigest.digest();   //the value is converted to hashvalue and stored in form of byte array
			
			StringBuilder sb = new StringBuilder();
			for(byte b : resultByteArray) {                //convert bytes to String
				sb.append(String.format("%02x", b));
			}
			password = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}
	//--------------------------------------------------------------------------

}

