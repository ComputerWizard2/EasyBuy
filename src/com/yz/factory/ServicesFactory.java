package com.yz.factory;

import java.util.ResourceBundle;

public class ServicesFactory {
	/**
	 * 设计一个工厂类来进行接耦合，工厂类做出单例的
	 */
	// 私有化他的构造函数
	private ServicesFactory() {

	}

	// 通过静态方法仅实现一个对象
	private static ServicesFactory servicesFactory = new ServicesFactory();

	// 创造出一个公共的方法共外界调用
	public static ServicesFactory getServicesInstance() {

		return servicesFactory;

	}

	public <T> T createServices(Class<T> t) {
		// 获取当前的名字
		String simpleName = t.getSimpleName();
		// 在配置文件中通过名称来查找这个类的路径
		String cazzNameString = ResourceBundle.getBundle("services").getString(simpleName);

		try {
			// 通过反射来获取对象
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
