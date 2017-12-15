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
	// a文件上传的功能
	public static Product doFileUpload(HttpServletRequest request) throws Exception {
		// 那工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置临时文件的缓冲区
		factory.setSizeThreshold(1024 * 1024);
		// 设置临时文件的位置
		factory.setRepository(new File(request.getSession().getServletContext().getRealPath("/temp")));
		// 获取最终的工具类
		ServletFileUpload uploader = new ServletFileUpload(factory);

		// 解决中文乱码的问题
		uploader.setHeaderEncoding("utf-8");
		// 设置单个文件的大小
		uploader.setFileSizeMax(1024 * 1024 * 5);
		// 设置容器
		Product product = new Product();
		List<FileItem> list = uploader.parseRequest(request);
		Map<String, Product> map = new HashMap<>();
		for (FileItem fileItem : list) {
			if (fileItem.isFormField()) {
				// 获取的表中的文件

				String fileName = fileItem.getFieldName();
				// 获取当前文件的
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
				// 获得文件的名称
				String name = fileItem.getName();

				int index = name.lastIndexOf("\\");

				if (index != -1) {
					name = name.substring(index + 1);
				}

				// 走到这, name 就是 俺们想要的了

				// 生成 随机的 文件名 --- 避免同名的文件被覆盖
				String uuidname = WebUtil.generateRandonFileName(name);

				// 生成随机的文件夹---- 避免文件夹的文件过多,导致 访问 过慢

				// /1/5
				String randomDir = WebUtil.generateRandDir(uuidname);

				// 注意: 这里 不能将上传的图片放到 WEB-INF目录下了 .

				// /images/1/5
				String imageurl = "/images" + randomDir;

				String realPath = request.getSession().getServletContext().getRealPath("/images");

				// d:/a/b/c/day15/images/1/5
				String newPath = realPath + randomDir;

				File file1 = new File(newPath);

				if (!file1.exists()) {

					file1.mkdirs();
				}
				// 创建这个文件
				File file2 = new File(newPath, uuidname);
				InputStream inputStream = fileItem.getInputStream();
				OutputStream outputStream = new FileOutputStream(file2);
				int len = 0;
				byte[] buf = new byte[1024];
				while ((len = inputStream.read(buf)) > 0) {
					// 创建输出流文件
					outputStream.write(buf, 0, len);
					// 创建一个文件对象进行写入操作

					outputStream.flush();

				}
				// 关闭流
				inputStream.close();
				outputStream.close();
				// 删除临时文件
				fileItem.delete();

				product.setEp_file_name(imageurl + "/" + uuidname);
				System.out.println(randomDir);
				System.out.println(imageurl);

			}

		}

		return product;

	}
}