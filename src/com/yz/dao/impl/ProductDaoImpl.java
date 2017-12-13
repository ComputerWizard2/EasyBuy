package com.yz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;
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
		} finally {
			Jdbc.closeResourse(connection, prepareStament, resultSet);
		}

		return i;
	}

	@Override
	public Page<ProducBean> findByPage(Page<ProducBean> page) {

		int start = (page.getCurrentPage() - 1) * page.getPageSize();
		List<ProducBean> list = new ArrayList<>();

		try {
			/*
			 * connection = Jdbc.getConnection(); prepareStament = connection
			 * .prepareStatement(
			 * "select * from easybuy_product_category where epc_parent_id=0 ");
			 * resultSet = prepareStament.executeQuery(); while
			 * (resultSet.next()) { int int1 = resultSet.getInt(1);
			 * 
			 * prepareStament = connection.prepareStatement(
			 * "select * where epc_parent_id =? "); prepareStament.setInt(1,
			 * int1); resultSet1 = prepareStament.executeQuery();
			 * list2.add("0"); list2.add(resultSet.getString(2)); while
			 * (resultSet1.next()) { list2.add(resultSet1.getString(2));
			 * 
			 * }
			 * 
			 * } list = list2.subList(start, start + page.getPageSize());
			 */
			connection = Jdbc.getConnection();
			prepareStament = connection.prepareStatement(
					"select t1.epc_id parentid,t1.epc_name parentname,t2.epc_id childid,t2.epc_name childname from easybuy_product_category t1,easybuy_product_category t2 where t1.epc_id=t2.epc_parent_id limit ?,?");
			prepareStament.setInt(1, start);
			prepareStament.setInt(2, page.getPageSize());
			resultSet = prepareStament.executeQuery();
			while (resultSet.next()) {
				// 创建product 对象
				ProducBean producBean = new ProducBean();
				producBean.setEpc_parent_id(resultSet.getInt("parentid"));
				producBean.setEpc_parentName(resultSet.getString("parentname"));
				producBean.setEpc_child_id(resultSet.getInt("childid"));
				producBean.setEpc_childName(resultSet.getString("childname"));
				list.add(producBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, prepareStament, resultSet);
		}
		page.setList(list);

		return page;
	}

	public List<ProducBean> findAllParentData() {

		List<ProducBean> list = null;
		try {
			connection = Jdbc.getConnection();
			prepareStament = connection
					.prepareStatement("select epc_name,epc_id from  easybuy_product_category where epc_parent_id = 0;");
			resultSet = prepareStament.executeQuery();
			list = new ArrayList<>();

			while (resultSet.next()) {
				ProducBean poBean = new ProducBean();
				poBean.setEpc_child_id(resultSet.getInt(2));
				poBean.setEpc_parentName(resultSet.getString(1));
				list.add(poBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, prepareStament, resultSet);
		}

		return list;
	}

	public ProducBean findProducterById(int id) {
		ProducBean producBean = new ProducBean();
		try {
			connection = Jdbc.getConnection();
			prepareStament = connection.prepareStatement("select * from easybuy_product_category where epc_id =?");
			prepareStament.setInt(1, id);
			resultSet = prepareStament.executeQuery();

			if (resultSet.next()) {

				producBean.setEpc_child_id(resultSet.getInt("epc_id"));

				producBean.setEpc_parent_id(resultSet.getInt("epc_parent_id"));
				producBean.setEpc_parentName(resultSet.getString("epc_name"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, prepareStament, resultSet);
		}

		return producBean;
	}

	public boolean updataCurrentData(int child, int parent) {

		try {
			connection = Jdbc.getConnection();
			prepareStament = connection
					.prepareStatement("update  easybuy_product_category set epc_parent_id =? where epc_id=?");
			prepareStament.setInt(1, parent);
			prepareStament.setInt(2, child);
			int i = prepareStament.executeUpdate();
			if (i > 0) {
				return true;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, prepareStament, null);
		}

		return false;
	}

	// 删除一切哈哈
	public boolean deleteProductData(int parseInt) {
		try {
			connection = Jdbc.getConnection();
			prepareStament = connection.prepareStatement("delete from   easybuy_product_category  where epc_id=?");
			prepareStament.setInt(1, parseInt);

			int i = prepareStament.executeUpdate();
			if (i > 0) {
				return true;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, prepareStament, null);
		}

		return false;

	}

}
