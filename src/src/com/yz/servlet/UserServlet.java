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

		// 获取service对象
		userServiceImpl = ServicesFactory.getServicesInstance().createServices(UserServiceImpl.class);

		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先获取路径
		String path = request.getServletPath();
		switch (path) {
		// 用于用户登录的校验
		case "/login.user":
			// 获取来自页面的参数
			String username = request.getParameter("userId");
			String password = request.getParameter("password");
			String code = request.getParameter("code");

			// 获取session的对象里的验证码
			HttpSession session = request.getSession();
			Object scode = session.getAttribute("code");

			// 调用service方法进行校验
			boolean illegleUser = userServiceImpl.isIllegleUser(username, password);
			// 进行校验

			System.out.println(scode);
			/*
			 * if (illegleUser && scode.equals(code)) {
			 */
			if (true) {
				// 跳转到管理员界面
				request.setAttribute("user", username);
				request.setAttribute("date", new Date().toString());
				request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);
			} else {
				request.setAttribute("mess", "密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}
			break;
		case "/userManag.user":
			// 获取数据在用户页面显示
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
