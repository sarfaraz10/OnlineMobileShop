package userDao;

import java.sql.Connection;
import java.sql.Statement;

import databaseconnection.DataBaseConnection;
import userBean.AddressUpdateBean;
import userBean.RegisterBean;

public class AddressUpdateDao {
	
	public String updateAddress(AddressUpdateBean addressUpdateBean) {
		
		String fullerror="";
		
		String addressId = addressUpdateBean.getAddressId();
		String receiverName = addressUpdateBean.getReceiverName();
		String phoneNumber = addressUpdateBean.getPhoneNumber();
		String addressLine1 = addressUpdateBean.getAddressLine1();
		String addressLine2 = addressUpdateBean.getAddressLine2();
		String pincode = addressUpdateBean.getPincode();
		String city = addressUpdateBean.getCity();
		
		try {
			Connection connection = DataBaseConnection.createConnection();
			
			Statement st=connection.createStatement();
	        int i=st.executeUpdate("update address set receivername = '"+receiverName+"',phonenumber = '"+phoneNumber+"',addressline1 = '"+addressLine1+"',addressline2 = '"+addressLine2+"',pincode = '"+pincode+"',city = '"+city+"' where addressid = '"+addressId+"'");
		}
		catch(Exception exception) {
			fullerror += "<br>"+exception.toString();
		}
		
		if(fullerror != "") {
			return fullerror;
		}
		else {
			return null;
		}
		
	}
	
}
