package controller;

import java.io.IOException;

import model.Users;
import model.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/VerifyOTPServlet")
public class VerifyOTPServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("otp") == null) {

            response.sendRedirect("index.jsp");
            return;
        }

        String enteredOTP = request.getParameter("otp");

        String actualOTP = (String) session.getAttribute("otp");

        long expiry = (long) session.getAttribute("expiry");

        int attempts = (int) session.getAttribute("attempts");

        // Maximum 5 attempts
        if (attempts >= 5) {

            session.invalidate();

            response.sendRedirect("index.jsp?error=TooManyAttempts");
            return;
        }
        

        // Check OTP expiry
        if (System.currentTimeMillis() > expiry) {

            session.invalidate();

            response.sendRedirect("index.jsp?error=Expired");
            return;
        }

        // Check OTP
        if (enteredOTP != null && enteredOTP.equals(actualOTP)) {

            try {

                // Create POJO
                Users user = new Users();

                // Get session values and set into POJO
                user.setName((String) session.getAttribute("name"));
                user.setEmail((String) session.getAttribute("email"));
                user.setPhone((String) session.getAttribute("phone"));
                user.setPassword((String) session.getAttribute("password"));

                // Call DAO
                UsersDAO dao = new UsersDAO();

                boolean result = dao.register(user);

                if (result) {

                    session.invalidate();

                    response.sendRedirect("success.jsp");

                } else {

                    response.sendRedirect("index.jsp?error=failed");
                }

            } catch (Exception e) {

                e.printStackTrace();

                response.sendRedirect("index.jsp?error=failed");
            }

        } else {

            session.setAttribute("attempts", attempts + 1);

            response.sendRedirect("verify-otp.jsp?error=InvalidOTP");
        }
    }
}