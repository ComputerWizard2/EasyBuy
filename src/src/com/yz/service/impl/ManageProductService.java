package src.com.yz.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;
import com.yz.bean.Product;
import com.yz.dao.impl.ManageProductDaoImpl;
import com.yz.dao.impl.ProductDaoImpl;
import com.yz.factory.DaoFactory;

public class ManageProductService {

	// ����dao��Ķ���
	public static ManageProductDaoImpl manageProduct = DaoFactory.getDaoInstance()
			.CreateDao(ManageProductDaoImpl.class);
	public static ProductDaoImpl productDaoImpl = DaoFactory.getDaoInstance().CreateDao(ProductDaoImpl.class);

	public void findAllProduct(HttpServletRequest request, HttpServletResponse response) {
		int currentPage = 1;
		String currentPages = request.getParameter("currentPage");
		if (!(currentPages == null)) {

			currentPage = Integer.parseInt(currentPages);
		}

		int pageSize = 1;
		Page<Product> page = new Page<>();
		page.setPageSize(pageSize);// ���뵱ǰҳ�Ĵ�С

		// ��ȡ�ܵ�������
		int i = manageProduct.findAllData();
		// ������ҳ��
		int totalPage = i % pageSize == 0 ? i / pageSize : i / pageSize + 1;
		page.setPageCount(totalPage);// ������ҳ��

		// Լ����ǰҳ
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (currentPage > totalPage) {
			currentPage = totalPage;

		}
		page.setCurrentPage(currentPage);// ���뵱ǰҳ
		page.setDataNum(i);// ������������
		// ����dao���ķ�������
		Page<Product> page2 = manageProduct.findAllDataByPage(page);
		// ���뵽ҳ��
		request.setAttribute("page", page2);
		try {
			request.getRequestDispatcher("WEB-INF/manage/product.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertData(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ�����С����Ϣ
		Map<ProducBean, List<ProducBean>> map = productDaoImpl.getBigDataAndSmallData();
		// ��ҳ�洫����Ϣ

		request.setAttribute("map", map);

		try {
			request.getRequestDispatcher("WEB-INF/manage/product-add.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

}
