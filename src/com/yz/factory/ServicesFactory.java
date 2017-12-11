package com.yz.factory;

import java.util.ResourceBundle;

public class ServicesFactory {
	/**
	 * ���һ�������������н���ϣ�����������������
	 */
	// ˽�л����Ĺ��캯��
	private ServicesFactory() {

	}

	// ͨ����̬������ʵ��һ������
	private static ServicesFactory servicesFactory = new ServicesFactory();

	// �����һ�������ķ�����������
	public static ServicesFactory getServicesInstance() {

		return servicesFactory;

	}

	public <T> T createServices(Class<T> t) {
		// ��ȡ��ǰ������
		String simpleName = t.getSimpleName();
		// �������ļ���ͨ������������������·��
		String cazzNameString = ResourceBundle.getBundle("services").getString(simpleName);

		try {
			// ͨ����������ȡ����
			return (T) Class.forName(cazzNameString).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
