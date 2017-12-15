package src.com.yz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.factory.ServicesFactory;

import src.com.yz.service.impl.ManageProductService;

/**
 * Servlet implementation class ManageProduct
 */
@WebServlet("*.manage")
public class ManageProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static ManageProductService managerProduct = ServicesFactory.getServicesInstance()
			.createServices(ManageProductService.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		switch (path) {
		case "/manageProduct.manage":
			managerProduct.findAllProduct(request, response);
			break;
		case "/product-add.manage":
			managerProduct.insertData(request, response);
			break;

		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
