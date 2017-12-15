package com.yz.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yz.bean.Product;

public class UploadUtils {
	// a�ļ��ϴ��Ĺ���
	public static Product doFileUpload(HttpServletRequest request) throws Exception {
		// �ǹ���
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ������ʱ�ļ��Ļ�����
		factory.setSizeThreshold(1024 * 1024);
		// ������ʱ�ļ���λ��
		factory.setRepository(new File(request.getSession().getServletContext().getRealPath("/temp")));
		// ��ȡ���յĹ�����
		ServletFileUpload uploader = new ServletFileUpload(factory);

		// ����������������
		uploader.setHeaderEncoding("utf-8");
		// ���õ����ļ��Ĵ�С
		uploader.setFileSizeMax(1024 * 1024 * 5);
		// ��������
		Product product = new Product();
		List<FileItem> list = uploader.parseRequest(request);
		Map<String, Product> map = new HashMap<>();
		for (FileItem fileItem : list) {
			if (fileItem.isFormField()) {
				// ��ȡ�ı��е��ļ�

				String fileName = fileItem.getFieldName();
				// ��ȡ��ǰ�ļ���
				switch (fileName) {
				case "productName":
					product.setEp_name(fileItem.getString("utf-8"));
					break;
				case "productDetail":
					product.setEp_description(fileItem.getString("utf-8"));
					break;
				case "parentId":
					product.setEpc_child_id(Integer.parseInt(fileItem.getString("utf-8")));
					break;
				case "productPrice":
					product.setEp_price(Double.parseDouble(fileItem.getString("utf-8")));
					;
					break;
				case "productNumber":
					product.setEp_stock(Integer.parseInt(fileItem.getString("utf-8")));
					break;

				}

			} else {
				// ����ļ�������
				String name = fileItem.getName();

				int index = name.lastIndexOf("\\");

				if (index != -1) {
					name = name.substring(index + 1);
				}

				// �ߵ���, name ���� ������Ҫ����

				// ���� ����� �ļ��� --- ����ͬ�����ļ�������
				String uuidname = WebUtil.generateRandonFileName(name);

				// ����������ļ���---- �����ļ��е��ļ�����,���� ���� ����

				// /1/5
				String randomDir = WebUtil.generateRandDir(uuidname);

				// ע��: ���� ���ܽ��ϴ���ͼƬ�ŵ� WEB-INFĿ¼���� .

				// /images/1/5
				String imageurl = "/images" + randomDir;

				String realPath = request.getSession().getServletContext().getRealPath("/images");

				// d:/a/b/c/day15/images/1/5
				String newPath = realPath + randomDir;

				File file1 = new File(newPath);

				if (!file1.exists()) {

					file1.mkdirs();
				}
				// ��������ļ�
				File file2 = new File(newPath, uuidname);
				InputStream inputStream = fileItem.getInputStream();
				OutputStream outputStream = new FileOutputStream(file2);
				int len = 0;
				byte[] buf = new byte[1024];
				while ((len = inputStream.read(buf)) > 0) {
					// ����������ļ�
					outputStream.write(buf, 0, len);
					// ����һ���ļ��������д�����

					outputStream.flush();

				}
				// �ر���
				inputStream.close();
				outputStream.close();
				// ɾ����ʱ�ļ�
				fileItem.delete();

				product.setEp_file_name(imageurl + "/" + uuidname);
				System.out.println(randomDir);
				System.out.println(imageurl);

			}

		}

		return product;

	}
}