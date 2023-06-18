package adminModules;

import jakarta.servlet.http.HttpServlet;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import databaseconnection.DataBaseConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProductValidation")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductValidation() {
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
		

		String productName = request.getParameter("productname");
		String stock = request.getParameter("stock");
		String price = request.getParameter("price");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin_add_product.jsp");
		
		InputStream inputStream = null;
		int errorSender = 0;
        Part filePart = request.getPart("productimage");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }
        try{

			Connection connection = DataBaseConnection.createConnection();
			
			String insertProducts = "INSERT INTO products (productname, stock, price, productimage) values (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertProducts);
			preparedStatement.setString(1, productName);
			preparedStatement.setString(2, stock);
			preparedStatement.setString(3, price);
			if (inputStream != null) {
                preparedStatement.setBlob(4, inputStream);
            }
			int row = preparedStatement.executeUpdate();
			if(row>0) {
				response.sendRedirect("admin_product.jsp");			
			}

        }
        catch(Exception e) {
			request.setAttribute("fullerror", "<br>Database error");
			errorSender++;
        }

		if(errorSender > 0 ) {
			requestDispatcher.forward(request, response);
		}
	}

}
