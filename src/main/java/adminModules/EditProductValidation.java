package adminModules;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import adminBean.EditProductBean;
import adminDao.EditProductDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class EditProductValidation
 */
@WebServlet("/EditProductValidation")
public class EditProductValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductValidation() {
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
		
		String productId = (String)request.getSession().getAttribute("productid");
		String productName = request.getParameter("productname");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");
		System.out.println(productId);
		RequestDispatcher rd = request.getRequestDispatcher("admin_edit_product.jsp");
		
		InputStream inputStream = null;
		int errorSender = 0;
        Part filePart = request.getPart("productimage");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }

        if(productId != null && productName != null && price != null && stock != null) {
        	EditProductBean editProductBean = new EditProductBean();
        	editProductBean.setProductId(productId);
        	editProductBean.setProductName(productName);
        	editProductBean.setPrice(price);
        	editProductBean.setStock(stock);
    		System.out.println("all set "+productId+" "+productName+" "+price+" "+stock+" "+inputStream.toString());
        	if(inputStream != null) {
        		System.out.println("image created");
        		editProductBean.setProductimage((Blob) inputStream);
        	}
        	
        	EditProductDao editProductDao = new EditProductDao();
        	String fullError = editProductDao.updateAddress(editProductBean);

			if(fullError != null) {
				request.setAttribute("fullerror", fullError);
				errorSender++;
			}
			else {
				response.sendRedirect("admin_product_edit_list.jsp");
			}

			if(errorSender > 0 ) {
				rd.forward(request, response);
			}
        	
        }
		
	}

}
