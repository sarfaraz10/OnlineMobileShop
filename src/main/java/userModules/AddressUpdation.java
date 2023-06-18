package userModules;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import userBean.AddressUpdateBean;
import userDao.AddressUpdateDao;
import userDao.RegisterDao;

/**
 * Servlet implementation class AddressUpdaion
 */
@WebServlet("/AddressUpdation")
public class AddressUpdation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressUpdation() {
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
		
		String addressId = (String)request.getSession().getAttribute("addressid");
		String receiverName = request.getParameter("receivername");
		String phoneNumber = request.getParameter("phonenumber");
		String addressLine1 = request.getParameter("address1");
		String addressLine2 = request.getParameter("address2");
		String pincode = request.getParameter("pincode");
		String city = request.getParameter("city");
		
		
		int errorSender=0;
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("user_edit_address.jsp");
		
		AddressUpdateBean addressUpdateBean = new AddressUpdateBean();
		
		if(addressId != null && receiverName != null && phoneNumber != null && addressLine1 != null && addressLine2 != null && pincode != null && city != null) {
			
			addressUpdateBean.setAddressId(addressId);
			addressUpdateBean.setReceiverName(receiverName);
			addressUpdateBean.setPhoneNumber(phoneNumber);
			addressUpdateBean.setAddressLine1(addressLine1);
			addressUpdateBean.setAddressLine2(addressLine2);
			addressUpdateBean.setPincode(pincode);
			addressUpdateBean.setCity(city);

			AddressUpdateDao addressUpdateDao = new AddressUpdateDao();
			

			String fullError = addressUpdateDao.updateAddress(addressUpdateBean);


			if(fullError != null) {
				request.setAttribute("fullerror", fullError);
				errorSender++;
			}
			else {
				response.sendRedirect("UserAddressDetails.jsp");
			}

			if(errorSender > 0 ) {
				requestDispatcher.forward(request, response);
			}
		}
		
	}

}
