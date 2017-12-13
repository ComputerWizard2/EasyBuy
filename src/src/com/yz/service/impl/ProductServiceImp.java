package src.com.yz.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;
import com.yz.dao.impl.ProductDaoImpl;
import com.yz.factory.DaoFactory;

import src.com.yz.services.ProductService;

public class ProductServiceImp implements ProductService {

	private static ProductDaoImpl productDaoImpl = DaoFactory.getDaoInstance().CreateDao(ProductDaoImpl.class);

	public Page<ProducBean> findByPage(HttpServletRequest request, HttpServletResponse response) {

		// 获取当前页
		String parameter = request.getParameter("currentPageProduct");

		int currentPage = Integer.parseInt(parameter);

		int pageSize = 10;

		Page<ProducBean> page = new Page<>();
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
		Page<ProducBean> findByPage = productDaoImpl.findByPage(page);
		// 获得数据进行返回
		return findByPage;
	}

	public boolean findAllParentData(HttpServletRequest request, HttpServletResponse response) {
		// 获取父类的数据
		List<ProducBean> list = productDaoImpl.findAllParentData();
		request.setAttribute("parentList", list);
		// 获取当前的子类的名字
		String id = request.getParameter("id");

		// 根据id找到当前的对象的
		ProducBean list2 = productDaoImpl.findProducterById(Integer.parseInt(id));

		request.setAttribute("child", list2);
		// 转向
		try {
			request.getRequestDispatcher("WEB-INF/manage/product-modify.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean updataChildData(HttpServletRequest request, HttpServletResponse response) {
		// 获取当前的数据的id
		int child = Integer.parseInt(request.getParameter("childName"));
		int parent = Integer.parseInt(request.getParameter("parentId"));
		// 调用dao层的方法进行更新数据
		boolean isSuccess = productDaoImpl.updataCurrentData(child, parent);
		if (isSuccess) {

			try {
				request.getRequestDispatcher("findByPage.product?currentPageProduct=1").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;

		}

		return false;
	}

	public boolean deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		// 获取当前的id
		String id = request.getParameter("id");
		boolean isDelete = productDaoImpl.deleteProductData(Integer.parseInt(id));
		if (isDelete) {

			try {
				request.getRequestDispatcher("findByPage.product?currentPageProduct=1").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}
