package com.yz.factory;

import java.util.ResourceBundle;

public class DaoFactory {

	/**
	 * ����dao��Ķ���ķ���
	 * 
	 */
	// ������Ҫ�����췽��˽�л�
	private DaoFactory() {

	}

	// �������һ������������
	private static DaoFactory daoFactory = new DaoFactory();

	// �����һ�����������ʹ��
	public static DaoFactory getDaoInstance() {

		return daoFactory;

	}

	// ����һ��������ʵ��
	public <T> T CreateDao(Class<T> t) {
		String simpleName = t.getSimpleName();
		String clazzName = ResourceBundle.getBundle("dao").getString(simpleName);

		try {
			// ��----ͨ������newinstance����øö���
			return (T) Class.forName(clazzName).newInstance();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
}
