package com.yz.dao;

import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.UserBean;

public interface UserDao {
	// ����User ���ݿ��ѯ�жϵ�ǰ�û��Ƿ����
	public boolean isIllegleUser(String username, String pwd);

	// �����������ݶ���
	public List<UserBean> findAllUser();

	// ������������
	public int findAllUserCount();

	// ��ҳ��ѯ
	public Page findUserByPage(Page page);

}
