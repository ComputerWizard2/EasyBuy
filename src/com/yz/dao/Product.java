package com.yz.dao;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;

public interface Product {
	// ��������
	public int findCount();

	// ���ҷ�ҳ
	public Page<ProducBean> findByPage(Page<ProducBean> page);

}
