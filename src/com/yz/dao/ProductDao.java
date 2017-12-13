package com.yz.dao;

import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;

public interface ProductDao {
	// ��������
	public int findCount();

	// ���ҷ�ҳ
	public Page<ProducBean> findByPage(Page<ProducBean> page);

	// ���Ҹ��������
	public List<ProducBean> findAllParentData();

}
