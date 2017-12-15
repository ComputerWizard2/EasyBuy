package com.yz.test;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		File file = new File("C:\\apache-tomcat-8.0.47\\wtpwebapps\\EasyBuy\\images/2/13");
		boolean mkdirs = file.mkdirs();
		if (mkdirs) {
			System.out.println("创建成功。。");

		}
	}

}
