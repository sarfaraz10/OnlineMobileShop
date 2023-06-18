package validation;


import jakarta.servlet.http.HttpServlet;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import databaseconnection.DataBaseConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import userBean.RegisterBean;
import userDao.LoginDao;


@WebServlet("/UserLoginValidation")
public class UserLoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginValidation() {
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
		
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		String productId = request.getParameter("productId");
		String buynow = request.getParameter("buynow");
		
		System.out.println("This is product ID "+productId+" "+buynow);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserLogin.jsp");

		
		int validChecker=0,errorSender=0;
		
		if (userEmail != null && userPassword != null){

			String emailRegex = "^[a-z0-9.]+@[a-z.]+$";
			Pattern patternmail = Pattern.compile(emailRegex);
		
			Matcher matchers = patternmail.matcher(userEmail);
			if (matchers.matches() == true) {
				validChecker++;
			} 
			else {
				request.setAttribute("emailError", "<br>Email address format is incorrect <br>");
				errorSender++;
			}
			
			if(validChecker != 0){
				
				RegisterBean registerBean = new RegisterBean();

				registerBean.setUserEmail(userEmail);
				registerBean.setUserPassword(userPassword);
				
				LoginDao loginDao = new LoginDao();
				
				String loginValidator = loginDao.loginUser(registerBean);
				
				if(loginValidator == "admin") {
					response.sendRedirect("admin_home.jsp");
				}
				else if(Character.isDigit(loginValidator.charAt(0))) {
					request.getSession().setAttribute("userid", loginValidator);
					if(productId != null) {
						if(buynow.equals("null") && productId != null) {
							response.sendRedirect("UserAddToCart.jsp?productId="+productId);
						}
						else {
							System.out.println("Inside Login "+productId+buynow);
							response.sendRedirect("UserAddToCart.jsp?productId="+productId+"&buynow=yes");
						}
					}
					else {
						response.sendRedirect("index.jsp");
					}
				}
				else {
					request.setAttribute("fullError", loginValidator);
					errorSender++;
				}
			}
			
		}
		
		if(errorSender > 0 ) {
			requestDispatcher.forward(request, response);
		}
		
	}

}
