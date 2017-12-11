package src.com.yz.service.impl;

import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.UserBean;
import com.yz.dao.impl.UserDaoImpl;
import com.yz.factory.DaoFactory;

import src.com.yz.services.UserServices;

public class UserServiceImpl implements UserServices {

	private static UserDaoImpl createDao = DaoFactory.getDaoInstance().CreateDao(UserDaoImpl.class);

	/**
	 * 用来实现用户的校验,这里调用dao的方法
	 */
	public UserServiceImpl() {
		// 在构造函数里直接获取到Userdao的对象

	}

	@Override
	public boolean isIllegleUser(String username, String pwd) {

		// 调用dao的方法
		boolean b = createDao.isIllegleUser(username, pwd);

		if (b) {
			return true;

		}

		return false;
	}

	/**
	 * 调用dao层的方法遍历User表的所有的数据
	 */

	@Override
	public List<UserBean> findAllUser() {
		List<UserBean> users = createDao.findAllUser();

		return users;
	}

	@Override
	public Page findByPage(int currentPage) {
		int pageSize = 1;
		// 创建page的对象
		Page page = new Page();
		page.setPageSize(pageSize);

		// 调用dao曾德方法获取数据的总数
		int i = createDao.findAllUserCount();
		if (i > 0) {
			page.setDataNum(i);
			// 获取总页数
			int pageCount = page.getPageCount();

			// 对当前页进行判断
			if (currentPage <= 0) {
				currentPage = 1;

			}
			if (currentPage >= pageCount) {
				currentPage = pageCount;

			}
			// 将当前页放入到page

			page.setCurrentPage(currentPage);
			// 调用dao层的命令进行查询当前页的数据

		} else {
			System.out.println("没有数据数据出错。。");
		}
		return null;
	}

}
