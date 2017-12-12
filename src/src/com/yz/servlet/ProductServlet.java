package src.com.yz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.bean.Page;
import com.yz.factory.ServicesFactory;

import src.com.yz.service.impl.ProductServiceImp;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("*.product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ��ȡservie ����ͨ��������
	ProductServiceImp productDaoImpl = ServicesFactory.getServicesInstance().createServices(ProductServiceImp.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ·��
		String servletPath = request.getServletPath();
		switch (servletPath) {
		case "/findByPage.product":

			// ȫ�����߼���service��ʵ��
			Page<String> page = productDaoImpl.findByPage(request, response);
			if (page != null) {
				request.setAttribute("page", page);
				request.getRequestDispatcher("WEB-INFO/manage/productClass.jsp").forward(request, response);

			} else {
				System.out.println("��ѯ�����޹�����");
			}

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
