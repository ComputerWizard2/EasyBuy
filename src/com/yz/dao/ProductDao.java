package com.yz.dao;

import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;

public interface ProductDao {
	// 查找总数
	public int findCount();

	// 查找分页
	public Page<ProducBean> findByPage(Page<ProducBean> page);

	// 查找父类的数据
	public List<ProducBean> findAllParentData();

}
