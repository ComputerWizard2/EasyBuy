package src.com.yz.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;

public interface ProductService {
	// ��ҳ��ѯ
	public Page<ProducBean> findByPage(HttpServletRequest request, HttpServletResponse response);

	// �������еĸ��������
	public boolean findAllParentData(HttpServletRequest request, HttpServletResponse response);
}
