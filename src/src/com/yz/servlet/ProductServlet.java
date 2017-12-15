package src.com.yz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;
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
			System.out.println("�����ˡ���");

			// ȫ�����߼���service��ʵ��
			Page<ProducBean> page = productDaoImpl.findByPage(request, response);
			if (page != null) {
				request.setAttribute("page", page);
				request.getRequestDispatcher("WEB-INF/manage/productClass.jsp").forward(request, response);

			} else {
				System.out.println("��ѯ�����޹�����");
			}

			break;
		case "/productClass-modify.product":
			// ��ȡ���������
			boolean b = productDaoImpl.findAllParentData(request, response);

			break;
		case "/produtClass-delete.product":

			boolean isDelete = productDaoImpl.deleteProduct(request, response);
			if (isDelete) {
				System.out.println("ɾ���ɹ�����");

			}

			break;
		case "/manage-result.product":
			boolean result = productDaoImpl.updataChildData(request, response);
			if (result) {

				request.getRequestDispatcher("WEB-INF/manage/productClass.jsp").forward(request, response);
				System.out.println("���³ɹ�����");

			}

			break;
		case "/productClass-add.product":
			// �����ݿ��������
			boolean data = productDaoImpl.findAllParentDataAdd(request, response);

			break;
		case "/product-add.product":
			// ��·�ύ����������
			request.getRequestDispatcher("WEB-INF/manage/productClass-add.jsp").forward(request, response);

			break;
		case "/manage-results.product":
			// ���÷������в���
			b = productDaoImpl.insertChildData(request, response);

			break;
		case "/managers-results.product":
			// ���÷���ת��
			b = productDaoImpl.mangerResult(request, response);

			break;
		case "/managers--results.product":
			// ���÷���ת��
			System.out.println("�����ˡ�����");
			try {
				b = productDaoImpl.InsertResult(request, response);
			} catch (Exception e) {

				e.printStackTrace();
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
