package userModules;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import userBean.ProceedBuyBean;
import userDao.ProceedBuyDao;

/**
 * Servlet implementation class ProceedBuy
 */

@WebServlet("/ProceedBuy")
public class ProceedBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProceedBuy() {
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
		
		String addressId = request.getParameter("address");
		String userId = (String)request.getSession().getAttribute("userid");
		String productId = (String)request.getSession().getAttribute("productid");
		String quantity = (String)request.getSession().getAttribute("quantity");
		String price = (String)request.getSession().getAttribute("price");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserProceedToBuy.jsp");

		System.out.println("Before Bean "+addressId+" "+userId+" "+price);
		if(productId != null && userId != null && addressId != null && price != null && quantity != null) {
			
			ProceedBuyBean proceedBuyBean = new ProceedBuyBean();
			proceedBuyBean.setAddressId(addressId);
			proceedBuyBean.setProductId(productId);
			proceedBuyBean.setUserId(userId);
			proceedBuyBean.setPrice(price);
			proceedBuyBean.setQuantity(quantity);
			
			System.out.println("After Bean "+addressId+" "+userId+" "+price);
			
			ProceedBuyDao proceedBuyDao = new ProceedBuyDao();
			String fullError = proceedBuyDao.addOrder(proceedBuyBean);

			if(fullError == null) {
				response.sendRedirect("ThankYouPage.jsp");
			}
			else {
				requestDispatcher.forward(request,response);
			}
		}
		
	}

}
