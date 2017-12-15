package com.yz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yz.bean.Page;
import com.yz.bean.ProducBean;
import com.yz.bean.Product;
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

	// 用于用户的跟新
	public boolean insertChildData(int parseInt, String parseInt2) {
		try {
			connection = Jdbc.getConnection();
			prepareStament = connection
					.prepareStatement("insert into easybuy_product_category(epc_name,epc_parent_id)  values(?,?)");
			prepareStament.setInt(2, parseInt);
			prepareStament.setString(1, parseInt2);

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

	public Map<ProducBean, List<ProducBean>> getBigDataAndSmallData() {
		Map<ProducBean, List<ProducBean>> map = new HashMap<>();

		ResultSet resultSet2 = null;
		try {
			connection = Jdbc.getConnection();
			// 先查出对应得id
			prepareStament = connection
					.prepareStatement("select * from easybuy_product_category where epc_parent_id =0");

			resultSet = prepareStament.executeQuery();
			// 获取父类 的id二次查子类的信息
			while (resultSet.next()) {
				List<ProducBean> list = new ArrayList<>();
				ProducBean producBean = new ProducBean();
				int i = resultSet.getInt("epc_id");
				producBean.setEpc_parent_id(i);
				producBean.setEpc_parentName(resultSet.getString("epc_name"));
				prepareStament = connection
						.prepareStatement("select * from easybuy_product_category where epc_parent_id=?");

				prepareStament.setInt(1, i);
				resultSet2 = prepareStament.executeQuery();
				while (resultSet2.next()) {
					ProducBean producBean2 = new ProducBean();
					producBean2.setEpc_child_id(resultSet2.getInt("epc_id"));
					producBean2.setEpc_childName(resultSet2.getString("epc_name"));
					list.add(producBean2);
				}

				map.put(producBean, list);

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

		return map;
	}

	// 对产品数据插入数据
	public boolean insertProductData(Product product) {
		try {
			connection = Jdbc.getConnection();
			prepareStament = connection.prepareStatement(
					"insert into easybuy_product (ep_name,ep_description,ep_price,ep_stock,epc_id,epc_child_id,ep_file_name) values(?,?,?,?,?,?,?)");

			prepareStament.setString(1, product.getEp_name());
			prepareStament.setString(2, product.getEp_description());
			prepareStament.setDouble(3, product.getEp_price());
			prepareStament.setInt(4, product.getEp_stock());
			prepareStament.setInt(5, product.getEpc_id());
			prepareStament.setInt(6, product.getEpc_child_id());
			prepareStament.setString(7, product.getEp_file_name());
			int i = prepareStament.executeUpdate();
			if (i > 0) {
				return true;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, prepareStament, resultSet);
		}

		return false;
	}
	// 根据子类的id查找父类的数据

	public Product findBigClassById(Product doFileUpload) {
		try {
			connection = Jdbc.getConnection();
			prepareStament = connection
					.prepareStatement("select epc_parent_id from easybuy_product_category where  epc_id =? ");
			prepareStament.setInt(1, doFileUpload.getEpc_child_id());
			resultSet = prepareStament.executeQuery();
			if (resultSet.next()) {

				doFileUpload.setEpc_id(resultSet.getInt(1));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, prepareStament, resultSet);
		}

		return doFileUpload;
	}

}
