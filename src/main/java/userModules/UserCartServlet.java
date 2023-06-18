package userModules;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * Servlet implementation class UserCartServlet
 */
@WebServlet("/UserCartServlet")
public class UserCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCartServlet() {
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
		
		HashSet<String> productList = (HashSet<String>)request.getSession().getAttribute("productList");
		
		HashMap<String,String> productMap = new HashMap<String,String>();
			for(String productId : productList){
				productMap.put(productId,String.valueOf(request.getParameter(productId)));
			}
			request.getSession().setAttribute("productMap", productMap);
			response.sendRedirect("UserProceedToBuy.jsp");
		
	}

}
