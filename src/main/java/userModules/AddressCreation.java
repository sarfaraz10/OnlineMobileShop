package userModules;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

import databaseconnection.DataBaseConnection;

/**
 * Servlet implementation class AddressCreation
 */
@WebServlet("/AddressCreation")
public class AddressCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressCreation() {
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

		
		String receiverName = request.getParameter("name");
		String phoneNumber = request.getParameter("phonenumber");
		String addressLine1 = request.getParameter("address1");
		String addressLine2 = request.getParameter("address2");
		String landmark = request.getParameter("landmark");
		String pincode = request.getParameter("pincode");
		String city = request.getParameter("city");
		
		int errorSender=0;
		String userId = (String)request.getSession().getAttribute("userid");
		

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserAddAddress.jsp");
		
		
		if(receiverName!= null && phoneNumber!=null && addressLine1!=null && addressLine2!=null && landmark!=null && pincode!=null && city!=null){

			try{
				Connection connection = DataBaseConnection.createConnection();
				
				Statement statement=connection.createStatement();
		        int executeQuery=statement.executeUpdate("insert into address(userid,receivername,phonenumber,addressline1,addressline2,landmark,pincode,city)values('"+userId+"','"+receiverName+"','"+phoneNumber+"','"+addressLine1+"','"+addressLine2+"','"+landmark+"','"+pincode+"','"+city+"')");
				response.sendRedirect("UserProceedToBuy.jsp");
			}
			catch(Exception e){
				request.setAttribute("fullerror", "<br>"+e.toString());
				errorSender++;
			}
		}
		if(errorSender >0) {
			requestDispatcher.forward(request, response);
		}
		
	}

}
