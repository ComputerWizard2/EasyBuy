package src.com.yz.services;

import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.UserBean;

public interface UserServices {
	// ͨ��password��username ���ж��û��Ƿ�Ϸ�
	public boolean isIllegleUser(String username, String pwd);

	// ��ȡ�û���������ʾ���б���
	public List<UserBean> findAllUser();

	// �������ҳ���߼�����
	public Page findByPage(int currentPage);

}