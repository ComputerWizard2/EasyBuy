package com.yz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yz.bean.Page;
import com.yz.dao.ProductDao;
import com.yz.util.Jdbc;

public class ProductDaoImpl implements ProductDao {
	private static Connection connection = null;
	private static PreparedStatement prepareStament = null;
	private static ResultSet resultSet = null;

	@Override
	public int findCount() {
		// 查找所有的数据
		int i = 0;
		// 连接数据库
		try {
			connection = Jdbc.getConnection();
			prepareStament = connection.prepareStatement("select count(*) from easybuy_product_category");
			resultSet = prepareStament.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public Page<String> findByPage(Page<String> page) {
		List<String> list2 = new ArrayList<>();
		ResultSet resultSet1 = null;

		int start = (page.getCurrentPage() - 1) * page.getPageSize();
		List<String> list = null;

		try {
			connection = Jdbc.getConnection();
			prepareStament = connection
					.prepareStatement("select * from easybuy_product_category where epc_parent_id=0 ");
			resultSet = prepareStament.executeQuery();
			while (resultSet.next()) {
				int int1 = resultSet.getInt(1);

				prepareStament = connection.prepareStatement("select * where epc_parent_id =? ");
				prepareStament.setInt(1, int1);
				resultSet1 = prepareStament.executeQuery();
				list2.add("0");
				list2.add(resultSet.getString(2));
				while (resultSet1.next()) {
					list2.add(resultSet1.getString(2));
					list = list2.subList(start, page.getPageSize());

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setList(list);

		return page;
	}

}
