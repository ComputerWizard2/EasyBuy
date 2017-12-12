package com.yz.dao;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;

public interface Product {
	// 查找总数
	public int findCount();

	// 查找分页
	public Page<ProducBean> findByPage(Page<ProducBean> page);

}
