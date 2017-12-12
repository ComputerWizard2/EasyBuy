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
	 * ����ʵ���û���У��,�������dao�ķ���
	 */
	public UserServiceImpl() {
		// �ڹ��캯����ֱ�ӻ�ȡ��Userdao�Ķ���

	}

	@Override
	public boolean isIllegleUser(String username, String pwd) {

		// ����dao�ķ���
		boolean b = createDao.isIllegleUser(username, pwd);

		if (b) {
			return true;

		}

		return false;
	}

	/**
	 * ����dao��ķ�������User������е�����
	 */

	@Override
	public List<UserBean> findAllUser() {
		List<UserBean> users = createDao.findAllUser();

		return users;
	}

	@Override
	public Page findByPage(int currentPage) {
		int pageSize = 1;
		// ����page�Ķ���
		Page page = new Page();
		page.setPageSize(pageSize);

		// ����dao���·�����ȡ���ݵ�����
		int i = createDao.findAllUserCount();

		Page byPage = null;
		if (i > 0) {
			page.setDataNum(i);
			// ��ȡ��ҳ��

			int countpage = i % pageSize == 0 ? i / pageSize : i / pageSize + 1;
			page.setPageCount(countpage);
			// �Ե�ǰҳ�����ж�
			if (currentPage <= 0) {
				currentPage = 1;

			}
			if (currentPage >= countpage) {
				currentPage = countpage;

			}
			// ����ǰҳ���뵽page

			page.setCurrentPage(currentPage);
			// ����dao���������в�ѯ��ǰҳ������

			byPage = createDao.findUserByPage(page);

		} else {
			System.out.println("û���������ݳ�����");
		}

		return byPage;
	}

	@Override
	public boolean updateUser(UserBean userBean) {

		boolean b = createDao.updataUser(userBean);

		return b;

	}

	public UserBean findUserById(String id) {
		UserBean userBean = createDao.findUserById(id);
		return userBean;
	}

	public boolean deleteUser(String id) {
		// ����dao��ķ���
		boolean b = createDao.deleteUser(id);
		if (b) {
			return true;

		}
		return false;
	}

}
