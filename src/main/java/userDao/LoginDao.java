package userDao;

import java.sql.*;

import databaseconnection.DataBaseConnection;
import userBean.RegisterBean;

public class LoginDao {
	
	public String loginUser(RegisterBean loginBean) {
		String fullError = "";
		
		int adminChecker = 0;
		
		String userEmail = loginBean.getUserEmail();
		String userPassword = loginBean.getUserPassword();
		
		try{
			
			Connection connection = DataBaseConnection.createConnection();
			
			String queryCheck = "SELECT count(*) AS COUNT from users WHERE useremail = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(queryCheck);
			preparedStatement.setString(1, userEmail);
			ResultSet emailResultSet = preparedStatement.executeQuery();
			
			emailResultSet.next();
			int mailCount = emailResultSet.getInt("COUNT");
			
			if(mailCount == 0){
				fullError += "<br>Email not exist";
			}

			else{
				String pwdQuery = "SELECT userpassword AS pwd from users WHERE useremail = ?";
				PreparedStatement pcheck = connection.prepareStatement(pwdQuery);
				pcheck.setString(1, userEmail);
				ResultSet passwordResultSet = pcheck.executeQuery();

				String finalPwd = "";
				if(passwordResultSet.next())  {
				   	finalPwd = passwordResultSet.getString("pwd"); 
				}
				
				if(userPassword.equals(finalPwd)){
					String idQuery = "SELECT userid AS uid from users WHERE useremail = ?";
					PreparedStatement idCheck = connection.prepareStatement(idQuery);
					idCheck.setString(1, userEmail);
					ResultSet userIdResultSet = idCheck.executeQuery();

					String finalID = "";
					if(userIdResultSet.next())  {
					   	finalID = String.valueOf(userIdResultSet.getInt("uid")); 
					}
					
					fullError = finalID;
					adminChecker++;
					//request.getSession().setAttribute("usermail", userEmail);
					//request.getSession().setAttribute("userid", finalID);
					//response.sendRedirect("index.jsp");
				}
				else{
					fullError += "<br>Password is Incorrect";
				}
			}
			
		}
		catch(Exception e){
			fullError += "<br>"+e.toString();
		}
		
		if(adminChecker == 0) {
			System.out.println("inside admin");
			try {
			Connection connection = DataBaseConnection.createConnection();
			
			String queryCheck = "SELECT count(*) AS COUNT from admins WHERE adminemail = ?";
			PreparedStatement problmStatement = connection.prepareStatement(queryCheck);
			problmStatement.setString(1, userEmail);
			ResultSet adminEmailResultSet = problmStatement.executeQuery();
			
			adminEmailResultSet.next();
			
			int mailCount = adminEmailResultSet.getInt("COUNT");
			System.out.println(mailCount);		
			if(mailCount != 0){
				String pwdQuery = "SELECT adminpassword AS pwd from admins WHERE adminemail = ?";
				PreparedStatement pcheck = connection.prepareStatement(pwdQuery);
				pcheck.setString(1, userEmail);
				ResultSet adminResultSet = pcheck.executeQuery();

				String finalPwd = "";
				if(adminResultSet.next())  {
				   	finalPwd = adminResultSet.getString("pwd"); 
				}
				
				if(userPassword.equals(finalPwd)){
					fullError = "admin";
				}
			}
		}
		catch(Exception exception){
			fullError += "<br>ADMIN "+exception.toString();
		}
		}

		return fullError;
	}
	
}
