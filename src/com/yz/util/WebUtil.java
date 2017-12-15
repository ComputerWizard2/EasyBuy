package com.yz.util;

import java.util.UUID;

public class WebUtil {

	public static String subFileName(String name) {
		// 找到最后一个\出现的位置
		int index = name.lastIndexOf("\\");
		if (index == -1) {
			return name;

		}

		return name.substring(index + 1);

	}

	// 获得随机的uuid 的文件名
	public static String generateRandonFileName(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return UUID.randomUUID().toString() + ext;

	}

	// 获得hashcode 生成二级目录
	public static String generateRandDir(String uuidFileName) {

		int hashCode = uuidFileName.hashCode();
		int d1 = hashCode & 0xf;
		int d2 = (hashCode >> 4) & 0xf;

		return "/" + d1 + "/" + d2;

	}
	//

}
