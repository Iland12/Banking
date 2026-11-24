package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;
import model.UsersDAO;
import util.EmailUtil;

import java.io.IOException;
import java.security.SecureRandom;


@WebServlet("/SignupServlet")
public class Registration extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		Users user = new Users();
		user.setName(request.getParameter("name")); //Farid
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setPassword(request.getParameter("password"));
		
		
		try {
			
			UsersDAO dao = new UsersDAO();
			
			if(dao.checkEmail(user.getEmail())) {  // true/false
				
				response.sendRedirect("index.jsp?error=EmailAlreadyExists");
				return;
				
			}
			
			
			
			// Generate OTP
			
			String otp = String.format("%06d" ,
					new 
					SecureRandom().nextInt(1000000)
					
					);
			
					// Create Session 
			
			HttpSession session = request.getSession();
			
			session.setAttribute("name", user.getName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("phone", user.getPhone());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("otp", otp);
			
			// otp expiry
			
			session.setAttribute("expiry", 
					System.currentTimeMillis()+300000L
					
					);
			
			session.setAttribute("attempts", 0);
			
			//send OTP
			
			EmailUtil.sendOTP(user.getEmail(), otp);
			
			response.sendRedirect("veriy-otp.jsp");
			
			return;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("index.jsp?error=failed");
		
	}

}