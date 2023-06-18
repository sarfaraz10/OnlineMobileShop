package userDao;

import userBean.ProceedBuyBean;
import userBean.RegisterBean;
import java.sql.*;

import databaseconnection.DataBaseConnection;

public class ProceedBuyDao {
	public String addOrder(ProceedBuyBean proceedBuyBean) {
		
		String fullError="";
		
		String productId = proceedBuyBean.getProductId();
		String addressId = proceedBuyBean.getAddressId();
		String userId = proceedBuyBean.getUserId();
		String quantity = proceedBuyBean.getQuantity();
		String price = proceedBuyBean.getPrice();
		
		int total = Integer.parseInt(price)*Integer.parseInt(quantity);
		System.out.println(total);
		
		try {
			Connection connection = DataBaseConnection.createConnection();
			Statement statement = connection.createStatement();
			String addressQuery = "select * from address where addressid = "+addressId;
			ResultSet addressResult = statement.executeQuery(addressQuery);
			String addressLine1="",addressLine2="",landmark="",pincode="",phoneNumber="",city="",receiverName="";
	        if(addressResult.next() == true) {
	             addressLine1 = addressResult.getString("addressline1");
	             addressLine2 = addressResult.getString("addressline2");
	        	 landmark = addressResult.getString("landmark");
	        	 pincode = addressResult.getString("pincode");
	        	 phoneNumber = addressResult.getString("phonenumber");
	        	 city = addressResult.getString("city");
	        	 receiverName = addressResult.getString("receivername");
	        	 System.out.println(addressLine1+" "+addressLine2+" "+landmark+" "+pincode+" "+phoneNumber+" "+city+" "+receiverName);
	        }
	        else {
	        	System.out.println("address not inserted");	        
	        }
	        
	        String productQuery = "select * from products where productid = "+productId;
			ResultSet productResult = statement.executeQuery(productQuery);
			Blob productImage = null;
			String productName = "";
	        if(productResult.next() == true) {
	             productImage = productResult.getBlob("productimage");
	             productName = productResult.getString("productname");
	        }
	        else {
	        	System.out.println("product not inserted");	        
	        }
	        
			String addOrderQuery = "insert into orders(userid,productid,quantity,price,total,addressline1,addressline2,landmark,pincode,phonenumber,city,receivername,productname,productimage,payment,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(addOrderQuery);
			preparedStatement.setInt(1, Integer.parseInt(userId));
			preparedStatement.setInt(2, Integer.parseInt(productId));
			preparedStatement.setString(3, quantity);
			preparedStatement.setString(4, price);
			preparedStatement.setString(5, String.valueOf(total));
			preparedStatement.setString(6, addressLine1);
			preparedStatement.setString(7, addressLine2);
			preparedStatement.setString(8, landmark);
			preparedStatement.setString(9, pincode);
			preparedStatement.setString(10, phoneNumber);
			preparedStatement.setString(11, city);
			preparedStatement.setString(12, receiverName);
			preparedStatement.setString(13, productName);
			preparedStatement.setBlob(14, productImage);
			preparedStatement.setString(15, "cod");
			preparedStatement.setString(16, "waiting");
			
			int executeQuery = preparedStatement.executeUpdate();
			if(executeQuery != 0) {
				fullError = null;
			}
		}
		catch(Exception exception) {
			fullError += "<br>"+exception.toString();
			System.out.println(exception.toString());
		}
		
		return fullError;
	}
}
