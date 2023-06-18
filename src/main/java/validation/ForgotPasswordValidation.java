package validation;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.*;

import databaseconnection.DataBaseConnection;

import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPasswordValidation")
public class ForgotPasswordValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String phoneNumber = request.getParameter("userphone");
		String userEmail = request.getParameter("email");
		

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserForgotPassword.jsp");
		//RequestDispatcher rd_reset = request.getRequestDispatcher("forgot_password.jsp");
		

		//request.setAttribute("emailerror", "<br>Email Correct da mapla");
		
		int validChecker=0,errorSender=0;
		
		if (phoneNumber != null && userEmail != null){
		
			int count = 0;
			
			if (phoneNumber.length() == 10) {
				if(Character.getNumericValue(phoneNumber.charAt(0))<6 || Character.getNumericValue(phoneNumber.charAt(0))>9){
					count++;
				}
				for (int i = 0; i < phoneNumber.length(); i++) {
					if (Character.isDigit(phoneNumber.charAt(i)) == false) {
						count++;
					}
				}
				if (count > 0) {
					request.setAttribute("phoneerror", "<br>Your phone number is not valid...<br>");
					errorSender++;
		
				} 
				else {
					validChecker++;
				}
			} 
			else {
				request.setAttribute("phoneerror", "<br>Enter Valid Phone number");
				errorSender++;
			}
		
			String emailid = "^[a-z0-9.]+@[a-z.]+$";
			Pattern patternmail = Pattern.compile(emailid);
		
			Matcher matchers = patternmail.matcher(userEmail);
			if (matchers.matches() == true) {
				validChecker++;
			} 
			else {
				request.setAttribute("emailerror", "<br>Email address format is incorrect <br>");
				errorSender++;
			}
			

			if(validChecker != 2){
				request.setAttribute("fullerror", "<br>Not inserted error");
				errorSender++;
			}
			else{
				try{

					Connection connection = DataBaseConnection.createConnection();
					
					String queryCheck = "SELECT count(*) AS COUNT from users WHERE useremail = ?";
					PreparedStatement ps = connection.prepareStatement(queryCheck);
					ps.setString(1, userEmail);
					ResultSet resultSet = ps.executeQuery();
					
					resultSet.next();
					int mailCount = resultSet.getInt("COUNT");
					
					if(mailCount == 0){
						request.setAttribute("emailerror", "<br>Email not exist");
						errorSender++;
					}

					else{
						String pwdQuery = "SELECT userphone AS uphone from users WHERE useremail = ?";
						PreparedStatement pcheck = connection.prepareStatement(pwdQuery);
						pcheck.setString(1, userEmail);
						ResultSet rs = pcheck.executeQuery();

						String finalPhone = "";
						if(rs.next())  {
						   	finalPhone = rs.getString("uphone"); 
						}
						
						if(phoneNumber.equals(finalPhone)){
							request.setAttribute("usermail", userEmail);
							request.getSession().setAttribute("usermail", userEmail);
							response.sendRedirect("UserResetPassword.jsp");
						}
						else{
							request.setAttribute("phoneerror", "<br>Phone Number is Incorrect...!");
							errorSender++;
						}
					}
				}
				catch(Exception e){
					request.setAttribute("fullerror", "<br>Database error");
					errorSender++;
				}
			}
			
		}
		
		if(errorSender > 1) {
			requestDispatcher.forward(request, response);
		}
		
	}

}
