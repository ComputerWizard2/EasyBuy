package src.com.yz.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.bean.Page;

public interface ProductService {
	// ��ҳ��ѯ
	public Page<String> findByPage(HttpServletRequest request, HttpServletResponse response);

}
