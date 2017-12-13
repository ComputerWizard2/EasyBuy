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

		// ��ȡ��ǰҳ
		String parameter = request.getParameter("currentPageProduct");

		int currentPage = Integer.parseInt(parameter);

		int pageSize = 10;

		Page<ProducBean> page = new Page<>();
		// ��ÿҳ�ж������ݷ���page��
		page.setPageSize(pageSize);
		// ��ȡ�ܵ�������
		int i = productDaoImpl.findCount();
		page.setDataNum(i);

		// ������ҳ��
		int countPage = i % pageSize == 0 ? i / pageSize : i / pageSize + 1;
		page.setPageCount(countPage);
		// �ж�һ�µ�ǰҳ��λ��
		if (currentPage <= 0) {
			currentPage = 1;

		}
		if (currentPage >= countPage) {
			currentPage = countPage;

		}
		// ����ǰҳ���뵽page ��
		page.setCurrentPage(currentPage);

		page.setCurrentPage(currentPage);
		// ����dao�������в�ѯ
		Page<ProducBean> findByPage = productDaoImpl.findByPage(page);
		// ������ݽ��з���
		return findByPage;
	}

	public boolean findAllParentData(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ���������
		List<ProducBean> list = productDaoImpl.findAllParentData();
		request.setAttribute("parentList", list);
		// ��ȡ��ǰ�����������
		String id = request.getParameter("id");

		// ����id�ҵ���ǰ�Ķ����
		ProducBean list2 = productDaoImpl.findProducterById(Integer.parseInt(id));

		request.setAttribute("child", list2);
		// ת��
		try {
			request.getRequestDispatcher("WEB-INF/manage/product-modify.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean updataChildData(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ��ǰ�����ݵ�id
		int child = Integer.parseInt(request.getParameter("childName"));
		int parent = Integer.parseInt(request.getParameter("parentId"));
		// ����dao��ķ������и�������
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
		// ��ȡ��ǰ��id
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
