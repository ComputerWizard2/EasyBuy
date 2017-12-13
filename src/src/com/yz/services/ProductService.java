package src.com.yz.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;

public interface ProductService {
	// 分页查询
	public Page<ProducBean> findByPage(HttpServletRequest request, HttpServletResponse response);

	// 查找所有的父类的数据
	public boolean findAllParentData(HttpServletRequest request, HttpServletResponse response);
}
