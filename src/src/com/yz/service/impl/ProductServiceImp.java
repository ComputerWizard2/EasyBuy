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

		// ��ȡ��ǰҳ
		String parameter = request.getParameter("currentPage");
		int currentPage = Integer.parseInt(parameter);
		int pageSize = 1;

		Page<String> page = new Page<>();
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
		Page<String> findByPage = productDaoImpl.findByPage(page);
		// ������ݽ��з���
		return findByPage;
	}

}
