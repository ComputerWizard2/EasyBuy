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

import com.yz.bean.Page;
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

				session.setAttribute("user", username);
				session.setAttribute("date", new Date());
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
		case "/findByPage.user":
			int currentPage = 1;
			// ҳ�洫�ݵ�ǰҳ
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// ���ҵ�ǰҳ
			Page page = userServiceImpl.findByPage(currentPage);
			// ���뵽���ݵ�ҳ��
			if (page != null) {

				request.setAttribute("userList", page.getList());
				request.setAttribute("page", page);

				// ת�����û�����
				request.getRequestDispatcher("WEB-INF/manage/user.jsp").forward(request, response);
			} else {
				System.out.println("������");

			}
		case "/updateUser.user":
			// �������ݵĸ���
			String eu_user_id = request.getParameter("userName");
			String eu_user_name = request.getParameter("name");
			String eu_password = request.getParameter("passWord");
			String eu_sex = request.getParameter("sex");
			String birthyear = request.getParameter("birthyear");
			String birthmonth = request.getParameter("birthmonth");
			String birthday = request.getParameter("birthday");
			Date date = new Date(Integer.parseInt(birthday), Integer.parseInt(birthmonth), Integer.parseInt(birthday));
			String eu_mobile = request.getParameter("mobile");
			String eu_address = request.getParameter("address");
			// ������һ��Userbean ��������������
			UserBean userBean = new UserBean();
			userBean.setEu_user_id(eu_user_id);
			userBean.setEu_user_name(eu_user_name);
			userBean.setEu_password(eu_password);
			userBean.setEu_sex(eu_sex);
			userBean.setEu_birthday(date);
			userBean.setEu_mobile(eu_mobile);
			userBean.setEu_address(eu_address);
			// ����service �ķ���
			boolean b = userServiceImpl.updateUser(userBean);
			if (b) {
				request.getRequestDispatcher("/findByPage.user?currentPage=1").forward(request, response);

			} else {
				System.out.println("����ʧ�ܡ���");
			}

			break;
		case "/findUserById.user":
			// ����Id�����û�����
			String id = request.getParameter("id");

			userBean = userServiceImpl.findUserById(id);
			System.out.println(userBean);
			if (userBean != null) {
				request.setAttribute("userbean", userBean);
				request.getRequestDispatcher("WEB-INF/manage/user-modify.jsp").forward(request, response);

			} else {

			}

			break;
		case "/deleteUserById.user":
			// ����Id�����û�����

			// ��ȡ�û���Id
			id = request.getParameter("id");
			// ����service ����

			// ����service�ķ���ɾ�����ݿ��
			b = userServiceImpl.deleteUser(id);
			if (b) {

				request.getRequestDispatcher("/findByPage.user?currentPage=1").forward(request, response);

			} else {
				System.out.println("ɾ��ʧ�ܡ���");
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
