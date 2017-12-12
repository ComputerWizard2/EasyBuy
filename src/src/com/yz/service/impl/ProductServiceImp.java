package src.com.yz.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.bean.Page;
import com.yz.dao.impl.ProductDaoImpl;
import com.yz.factory.DaoFactory;

import src.com.yz.services.ProductService;

public class ProductServiceImp implements ProductService {

	private static ProductDaoImpl productDaoImpl = DaoFactory.getDaoInstance().CreateDao(ProductDaoImpl.class);

	public Page<String> findByPage(HttpServletRequest request, HttpServletResponse response) {

		// 获取当前页
		String parameter = request.getParameter("currentPage");
		int currentPage = Integer.parseInt(parameter);
		int pageSize = 1;

		Page<String> page = new Page<>();
		// 将每页有多少数据放入page的
		page.setPageSize(pageSize);
		// 获取总的数据数
		int i = productDaoImpl.findCount();
		page.setDataNum(i);

		// 求其总页数
		int countPage = i % pageSize == 0 ? i / pageSize : i / pageSize + 1;
		page.setPageCount(countPage);
		// 判断一下当前页的位置
		if (currentPage <= 0) {
			currentPage = 1;

		}
		if (currentPage >= countPage) {
			currentPage = countPage;

		}
		// 将当前页放入到page 中
		page.setCurrentPage(currentPage);

		page.setCurrentPage(currentPage);
		// 调用dao方法进行查询
		Page<String> findByPage = productDaoImpl.findByPage(page);
		// 获得数据进行返回
		return findByPage;
	}

}
