package validation;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.regex.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import userBean.RegisterBean;
import userDao.RegisterDao;

/**
 * Servlet implementation class RegisterCheck
 */
@WebServlet("/UserRegisterValidation")
public class UserRegisterValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public UserRegisterValidation() {
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
		// TODO Auto-generated method stub
		
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhoneNumber");
		String userEmail = request.getParameter("userEmail");
		String password = request.getParameter("userPassword");
		String confirmPassword = request.getParameter("userConfirmPassword");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserRegister.jsp");
		

		int validChecker = 0,errorSender=0;
		
		if (userName != null && userPhone != null && userEmail != null && password != null && confirmPassword != null){
		
			String regex = "^[a-zA-Z\\s]+";
			Pattern pattern = Pattern.compile(regex);
		
			Matcher matcher = pattern.matcher(userName);
			if (matcher.matches() == true) {
				validChecker++;
			} 
			else {
				request.setAttribute("nameError", "<br>Name must contain only alphabet....<br>");
				errorSender++;
			}
		
			int count = 0;
			
			if (userPhone.length() == 10) {
				if(Character.getNumericValue(userPhone.charAt(0))<6 || Character.getNumericValue(userPhone.charAt(0))>9){
					count++;
				}
				for (int itertor = 0; itertor < userPhone.length(); itertor++) {
					if (Character.isDigit(userPhone.charAt(itertor)) == false) {
						count++;
					}
				}
				if (count > 0) {
					request.setAttribute("phoneError", "<br>Your phone number is not valid...<br>");
					errorSender++;
				} 
				else {
					validChecker++;
				}
			} 
			else {
				request.setAttribute("phoneError", "<br>Enter Valid Phone number");
				errorSender++;
			}
		
			String emailIdRegex = "^[a-z0-9.]+@[a-z.]+$";
			Pattern patternmail = Pattern.compile(emailIdRegex);
		
			Matcher emailIdMatcher = patternmail.matcher(userEmail);
			if (emailIdMatcher.matches() == true) {
				validChecker++;
			} 
			else {
				request.setAttribute("emailError", "<br>Email address format is incorrect <br>");
				errorSender++;
			}
			
			int upperCaseChecker=0,lowerCaseChecker=0,numberChecker=0,specialCharacterChecker=0;
			
			if (password.equals(confirmPassword)) {
				if(password.length() >= 8){
					for(int i=0;i<password.length();i++){
						if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
				            upperCaseChecker++;
				        else if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
				            lowerCaseChecker++;
				        else if (password.charAt(i)>= '0' && password.charAt(i)<= '9')
				            numberChecker++;
				        else
				            specialCharacterChecker++;
					}
					
					if (upperCaseChecker>0 && lowerCaseChecker>0 && numberChecker>0 && specialCharacterChecker>0) {
						validChecker++;
					} 
					else {
						String passwordError="";
						if(upperCaseChecker==0){
							passwordError += "<br>Add atleast one Uppercase letter in Passsword";
						}
						if(lowerCaseChecker==0){
							passwordError += "<br>Add atleast one Lowercase letter in Passsword";
						}
						if(numberChecker==0){
							passwordError += "<br>Add atleast one Number in Passsword";
						}
						if(specialCharacterChecker==0){
							passwordError += "<br>Add atleast one Special Character in Passsword";
						}
						request.setAttribute("passwordError", passwordError);
						errorSender++;
					}
				}
				else{
					request.setAttribute("passwordError", "<br>Password Length must be atleast 8");
					errorSender++;
				}
			}
			else {
				request.setAttribute("passwordError", "<br>Password mismatch");
				errorSender++;
			}
			
			
			if(validChecker != 4){
				request.setAttribute("fullError", "<br>Please enter valid details in all Fields...!!!");
				errorSender++;
			}
			else{
				
				RegisterBean registerBean = new RegisterBean();
				
				registerBean.setUserName(userName);
				registerBean.setUserEmail(userEmail);
				registerBean.setUserPhone(userPhone);
				registerBean.setUserPassword(password);
				
				RegisterDao registerDao = new RegisterDao();
				
				String fullError = registerDao.registerUser(registerBean);
			
		
				if(fullError != null) {
					request.setAttribute("fullError", fullError);
					errorSender++;
				}
				else {
					response.sendRedirect("UserLogin.jsp");
				}
			}
		}
		
		if(errorSender > 0 ) {
			requestDispatcher.forward(request, response);
		}
		
	
	}

}
