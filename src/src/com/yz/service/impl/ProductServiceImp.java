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

	// ���ڸ�������ݵĲ���δ�����������
	public boolean findAllParentDataAdd(HttpServletRequest request, HttpServletResponse response) {
		List<ProducBean> list = productDaoImpl.findAllParentData();

		// ������ת��
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
		// ��ȡ����ҳ�������

		// ��ȡ����ҳ��
		ServletFileUpload servletFileUpload = new ServletFileUpload();
		if (servletFileUpload.isMultipartContent(request)) {
			UploadUtils uploadUtils = new UploadUtils();
			Product doFileUpload = UploadUtils.doFileUpload(request);
			// ����service�ķ���ͨ��С��id ��ȡ�����id��
			Product product = productDaoImpl.findBigClassById(doFileUpload);
			// ����dao�㷽�������ݿ���в���
			boolean isResult = productDaoImpl.insertProductData(product);
			if (isResult) {

				manageProductService.findAllProduct(request, response);

			} else {
				System.out.println("���ִ��󡣡�");
			}

		}
		return false;
	}

}
