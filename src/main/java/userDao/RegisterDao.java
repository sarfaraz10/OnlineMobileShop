package userDao;

import java.sql.*;

import databaseconnection.DataBaseConnection;
import userBean.RegisterBean;

public class RegisterDao {
	public String registerUser(RegisterBean registerUser) {
		String userName = registerUser.getUserName();
		String userEmail = registerUser.getUserEmail();
		String userPhone = registerUser.getUserPhone();
		String userPassword = registerUser.getUserPassword();
		
		String fullError = "";
		
		try{
			
			Connection connection = DataBaseConnection.createConnection();
			
			String emailCheckerQuery = "SELECT count(*) AS COUNT from users WHERE useremail = ?";
			PreparedStatement emailStatement = connection.prepareStatement(emailCheckerQuery);
			emailStatement.setString(1, userEmail);
			ResultSet emailResultSet = emailStatement.executeQuery();
			
			emailResultSet.next();
			int emailCount = emailResultSet.getInt("COUNT");
			
			String phoneCheckerQuery = "SELECT count(*) AS COUNT from users WHERE userphone = ?";
			PreparedStatement phoneStatement = connection.prepareStatement(phoneCheckerQuery);
			phoneStatement.setString(1, userPhone);
			ResultSet phoneResultSet = phoneStatement.executeQuery();
			
			phoneResultSet.next();
			int phoneCount = phoneResultSet.getInt("COUNT");
			
			if(phoneCount > 0 || emailCount > 0){
				if(phoneCount > 0){
					fullError += "<br>Phone number already exists";
				}
				if(emailCount > 0){
					fullError += "<br>Email address already exists";
				}
			}
			else{
				Statement statement = connection.createStatement();
				String insertQuery = "insert into users(username,useremail,userphone,userpassword)values('"+userName+"','"+userEmail+"','"+userPhone+"','"+userPassword+"')";
		        int executeQuery = statement.executeUpdate(insertQuery);
			}
		}
		catch(Exception exception){
			fullError +=  "<br>"+exception.toString();
		}
		
		if(fullError != "") {
			return fullError;
		}
		else {
			return null;
		}
		
	}

}
