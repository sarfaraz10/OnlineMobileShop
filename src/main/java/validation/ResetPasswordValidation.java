package validation;

import java.sql.*;

import databaseconnection.DataBaseConnection;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPasswordValidation")
public class ResetPasswordValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordValidation() {
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


		ServletContext servletcontext = getServletContext();
	    String userEmail = (String)request.getSession().getAttribute("usermail");
	    
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("cpassword");
		int validChecker = 0,errorSender=0;

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserResetPassword.jsp");
		
		int upperCaseChecker=0,lowerCaseChecker=0,numberChecker=0,specialCharacterChecker=0;
		if(password != null && confirmPassword != null){
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
						String pwdError = "";
						if(upperCaseChecker==0){
							pwdError += "<br>Add atleast one Uppercase letter in Passsword";
						}
						if(lowerCaseChecker==0){
							pwdError += "<br>Add atleast one Lowercase letter in Passsword";
						}
						if(numberChecker==0){
							pwdError += "<br>Add atleast one Number in Passsword";
						}
						if(specialCharacterChecker==0){
							pwdError += "<br>Add atleast one Special Character in Passsword";
						}
						request.setAttribute("passworderror", pwdError);
						errorSender++;
					}
				}
				else{
					request.setAttribute("passworderror", "<br>Password Length must be atleast 8 "+userEmail);
					request.setAttribute("usermail", userEmail);
					errorSender++;
				}
			}
			else {
				request.setAttribute("passworderror", "<br>Password mismatch");
				errorSender++;
			}
			
			
			if(validChecker != 1){
				request.setAttribute("fullerror", "<br>Not inserted error");
				errorSender++;
			}
			else{
		
				try{
					Connection connection = DataBaseConnection.createConnection();
					Statement statement = connection.createStatement();
			        int executeQuery = statement.executeUpdate("update users set userpassword='"+password+"' where useremail = '"+userEmail+"'");
					response.sendRedirect("UserLogin.jsp");
				}
				catch(Exception e){
					request.setAttribute("fullerror", "Database error");
					errorSender++;
				}
			}
		}
		

		if(errorSender > 0 ) {
			requestDispatcher.forward(request, response);
		}
	}

}
