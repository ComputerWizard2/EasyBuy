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
		if (i > 0) {
			page.setDataNum(i);
			// ��ȡ��ҳ��
			int pageCount = page.getPageCount();

			// �Ե�ǰҳ�����ж�
			if (currentPage <= 0) {
				currentPage = 1;

			}
			if (currentPage >= pageCount) {
				currentPage = pageCount;

			}
			// ����ǰҳ���뵽page

			page.setCurrentPage(currentPage);
			// ����dao���������в�ѯ��ǰҳ������

		} else {
			System.out.println("û���������ݳ�����");
		}
		return null;
	}

}
