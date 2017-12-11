package com.yz.factory;

import java.util.ResourceBundle;

public class DaoFactory {

	/**
	 * 创建dao层的对象的方法
	 * 
	 */
	// 我们需要将构造方法私有化
	private DaoFactory() {

	}

	// 创造出来一个对象共外界调用
	private static DaoFactory daoFactory = new DaoFactory();

	// 创造出一个方法供外界使用
	public static DaoFactory getDaoInstance() {

		return daoFactory;

	}

	// 创造一个方法来实现
	public <T> T CreateDao(Class<T> t) {
		String simpleName = t.getSimpleName();
		String clazzName = ResourceBundle.getBundle("dao").getString(simpleName);

		try {
			// 类----通过调用newinstance来获得该对象
			return (T) Class.forName(clazzName).newInstance();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
}
