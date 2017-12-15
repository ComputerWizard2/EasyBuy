package com.yz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.Product;
import com.yz.util.Jdbc;

public class ManageProductDaoImpl {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public int findAllData() {
		try {
			connection = Jdbc.getConnection();
			preparedStatement = connection.prepareStatement("select count(*) from easybuy_product ");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int i = resultSet.getInt(1);
				return i;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public Page<Product> findAllDataByPage(Page<Product> page) {
		// 计算起始页
		int start = (page.getCurrentPage() - 1) * page.getPageSize();
		List<Product> list = new ArrayList<>();
		try {
			connection = Jdbc.getConnection();
			preparedStatement = connection.prepareStatement("select *  from easybuy_product limit ?,?");
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, page.getPageSize());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setEp_description(resultSet.getString("ep_description"));
				product.setEp_file_name(resultSet.getString("ep_file_name"));
				product.setEp_id(resultSet.getInt("ep_id"));
				product.setEp_name(resultSet.getString("ep_name"));
				product.setEp_price(resultSet.getDouble("ep_price"));
				product.setEp_stock(resultSet.getInt("ep_stock"));
				product.setEpc_child_id(resultSet.getInt("epc_child_id"));
				product.setEpc_id(resultSet.getInt("epc_id"));
				list.add(product);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 将list放入到page 中
		page.setList(list);
		return page;
	}

}
