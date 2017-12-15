package com.yz.util;

import java.util.UUID;

public class WebUtil {

	public static String subFileName(String name) {
		// �ҵ����һ��\���ֵ�λ��
		int index = name.lastIndexOf("\\");
		if (index == -1) {
			return name;

		}

		return name.substring(index + 1);

	}

	// ��������uuid ���ļ���
	public static String generateRandonFileName(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return UUID.randomUUID().toString() + ext;

	}

	// ���hashcode ���ɶ���Ŀ¼
	public static String generateRandDir(String uuidFileName) {

		int hashCode = uuidFileName.hashCode();
		int d1 = hashCode & 0xf;
		int d2 = (hashCode >> 4) & 0xf;

		return "/" + d1 + "/" + d2;

	}
	//

}
