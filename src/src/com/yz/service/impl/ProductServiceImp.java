package src.com.yz.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;
import com.yz.bean.Product;
import com.yz.dao.impl.ProductDaoImpl;
import com.yz.factory.DaoFactory;
import com.yz.factory.ServicesFactory;
import com.yz.util.UploadUtils;

import src.com.yz.services.ProductService;

public class ProductServiceImp implements ProductService {

	private static ProductDaoImpl productDaoImpl = DaoFactory.getDaoInstance().CreateDao(ProductDaoImpl.class);
	private static ManageProductService manageProductService = ServicesFactory.getServicesInstance()
			.createServices(ManageProductService.class);

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

	// 用于父类的数据的查找未添加子类数据
	public boolean findAllParentDataAdd(HttpServletRequest request, HttpServletResponse response) {
		List<ProducBean> list = productDaoImpl.findAllParentData();

		// 仅用于转发
		if (list != null && list.size() > 0) {

			try {
				request.setAttribute("list", list);
				request.getRequestDispatcher("WEB-INF/manage/productClass-add.jsp").forward(request, response);
			} catch (ServletException | IOException e) {

				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean insertChildData(HttpServletRequest request, HttpServletResponse response) {
		String parentId = request.getParameter("parentId");
		String childName = request.getParameter("className");

		boolean p = productDaoImpl.insertChildData(Integer.parseInt(parentId), childName);
		if (p) {

			try {
				request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	public boolean mangerResult(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("findByPage.product?currentPageProduct=1").forward(request, response);

		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean InsertResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取来自页面的数据

		// 获取来自页面
		ServletFileUpload servletFileUpload = new ServletFileUpload();
		if (servletFileUpload.isMultipartContent(request)) {
			UploadUtils uploadUtils = new UploadUtils();
			Product doFileUpload = UploadUtils.doFileUpload(request);
			// 调用service的方法通过小类id 获取大类的id；
			Product product = productDaoImpl.findBigClassById(doFileUpload);
			// 调用dao层方法对数据库进行插入
			boolean isResult = productDaoImpl.insertProductData(product);
			if (isResult) {

				manageProductService.findAllProduct(request, response);

			} else {
				System.out.println("出现错误。。");
			}

		}
		return false;
	}

}
