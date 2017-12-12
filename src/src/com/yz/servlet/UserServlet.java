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

				session.setAttribute("user", username);
				session.setAttribute("date", new Date());
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
		case "/findByPage.user":
			int currentPage = 1;
			// 页面传递当前页
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// 查找当前页
			Page page = userServiceImpl.findByPage(currentPage);
			// 放入到传递到页面
			if (page != null) {

				request.setAttribute("userList", page.getList());
				request.setAttribute("page", page);

				// 转发到用户界面
				request.getRequestDispatcher("WEB-INF/manage/user.jsp").forward(request, response);
			} else {
				System.out.println("无数据");

			}
		case "/updateUser.user":
			// 用于数据的更新
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
			// 创建的一个Userbean 对象来接收数据
			UserBean userBean = new UserBean();
			userBean.setEu_user_id(eu_user_id);
			userBean.setEu_user_name(eu_user_name);
			userBean.setEu_password(eu_password);
			userBean.setEu_sex(eu_sex);
			userBean.setEu_birthday(date);
			userBean.setEu_mobile(eu_mobile);
			userBean.setEu_address(eu_address);
			// 调用service 的方法
			boolean b = userServiceImpl.updateUser(userBean);
			if (b) {
				request.getRequestDispatcher("/findByPage.user?currentPage=1").forward(request, response);

			} else {
				System.out.println("更新失败。。");
			}

			break;
		case "/findUserById.user":
			// 根据Id查找用户对象
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
			// 根据Id查找用户对象

			// 获取用户的Id
			id = request.getParameter("id");
			// 调用service 方法

			// 调用service的方法删除数据库的
			b = userServiceImpl.deleteUser(id);
			if (b) {

				request.getRequestDispatcher("/findByPage.user?currentPage=1").forward(request, response);

			} else {
				System.out.println("删除失败。。");
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
