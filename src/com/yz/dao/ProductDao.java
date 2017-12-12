package com.yz.dao;

import com.yz.bean.Page;

public interface ProductDao {
	// 查找总数
	public int findCount();

	// 查找分页
	public Page<String> findByPage(Page<String> page);

}
