package adminDao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

import adminBean.EditProductBean;
import databaseconnection.DataBaseConnection;


public class EditProductDao {
	

	public String updateAddress(EditProductBean editProductBean) {
		
		String fullError="";
		
		String productId = editProductBean.getProductId();
		String productName = editProductBean.getProductName();
		String stock = editProductBean.getStock();
		String price = editProductBean.getPrice();
		Blob productImage = editProductBean.getProductimage();
		
		String updateQuery = "";
		System.out.println("inside dao");
		if(productImage != null) {
			updateQuery = "update products set productname = ?,stock = ?,price = ?,productimage = ? where productid = ?";

			System.out.println("1st query dao");
		}
		else {
			updateQuery = "update products set productname = ?,stock = ?,price = ? where productid = ?";
			System.out.println("2nd query dao");
		}
		
		try {
			Connection connection = DataBaseConnection.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, productName);
			preparedStatement.setString(2, stock);
			preparedStatement.setString(3, price);
			System.out.println("prodcts set");
			if(productImage != null) {
				preparedStatement.setBlob(4, productImage);
				preparedStatement.setString(5, productId);
			}
			else {
				preparedStatement.setString(4, productId);
			}
			
			int row = preparedStatement.executeUpdate();
			if(row>0) {
				fullError += "Database Error<br>";
			}
			
		}
		catch(Exception exception){
			fullError += exception.toString();
		}
		
		if(fullError == "") {
			fullError = null;
		}
		
		return fullError;
	}
}
