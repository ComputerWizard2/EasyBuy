package src.com.yz.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yz.bean.UserBean;
import com.yz.factory.ServicesFactory;

import src.com.yz.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("*.user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserServiceImpl userServiceImpl = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {

		// ��ȡservice����
		userServiceImpl = ServicesFactory.getServicesInstance().createServices(UserServiceImpl.class);

		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �Ȼ�ȡ·��
		String path = request.getServletPath();
		switch (path) {
		// �����û���¼��У��
		case "/login.user":
			// ��ȡ����ҳ��Ĳ���
			String username = request.getParameter("userId");
			String password = request.getParameter("password");
			String code = request.getParameter("code");

			// ��ȡsession�Ķ��������֤��
			HttpSession session = request.getSession();
			Object scode = session.getAttribute("code");

			// ����service��������У��
			boolean illegleUser = userServiceImpl.isIllegleUser(username, password);
			// ����У��

			System.out.println(scode);
			/*
			 * if (illegleUser && scode.equals(code)) {
			 */
			if (true) {
				// ��ת������Ա����
				request.setAttribute("user", username);
				request.setAttribute("date", new Date().toString());
				request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);
			} else {
				request.setAttribute("mess", "�������");
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}
			break;
		case "/userManag.user":
			// ��ȡ�������û�ҳ����ʾ
			List<UserBean> findAllUser = userServiceImpl.findAllUser();
			if (findAllUser != null) {
				request.setAttribute("userList", findAllUser);
				request.getRequestDispatcher("WEB-INF/manage/user.jsp").forward(request, response);

			} else {
				request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);

			}

			break;
		case "/findByPage":

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
