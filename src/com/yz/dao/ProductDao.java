package com.yz.dao;

import com.yz.bean.Page;

public interface ProductDao {
	// ��������
	public int findCount();

	// ���ҷ�ҳ
	public Page<String> findByPage(Page<String> page);

}
