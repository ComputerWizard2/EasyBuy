package com.yz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.UserBean;
import com.yz.dao.UserDao;
import com.yz.util.Jdbc;

public class UserDaoImpl implements UserDao {
	private static Connection connection = null;
	private static PreparedStatement preparedStament = null;
	private static ResultSet resultSet = null;

	// 在构造函数中实现获取连接
	public UserDaoImpl() {
	}

	@Override
	public boolean isIllegleUser(String username, String pwd) {
		try {
			connection = Jdbc.getConnection();
			preparedStament = connection
					.prepareStatement("select * from easybuy_user where eu_user_id=? and eu_password=?");
			preparedStament.setString(1, username);
			System.out.println(username);
			System.out.println(pwd);
			preparedStament.setString(2, pwd);
			resultSet = preparedStament.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, preparedStament, resultSet);
		}
		return false;

	}

	/**
	 * 查询用户表的所有的数据
	 */

	public List<UserBean> findAllUser() {
		List<UserBean> list = null;
		try {
			connection = Jdbc.getConnection();
			preparedStament = connection.prepareStatement("select * from easybuy_user");
			resultSet = preparedStament.executeQuery();
			list = new ArrayList<>();
			/**
			 * 用户名 真实姓名 性别 Email 手机 操作
			 */
			while (resultSet.next()) {
				UserBean userBean = new UserBean();
				userBean.setEu_user_id(resultSet.getString(1));
				userBean.setEu_user_name(resultSet.getString(2));
				userBean.setEu_sex(resultSet.getString(4));
				userBean.setEu_email(resultSet.getString(7));
				userBean.setEu_mobile(resultSet.getString(8));
				userBean.setEu_address(resultSet.getString(9));
				list.add(userBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, preparedStament, resultSet);
		}
		return list;
	}

	@Override
	public int findAllUserCount() {
		int i = 0;
		try {
			connection = Jdbc.getConnection();
			preparedStament = connection.prepareStatement("select count(*) from easybuy_user");
			resultSet = preparedStament.executeQuery();
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
	public Page findUserByPage(Page page) {
		try {
			connection = Jdbc.getConnection();
			preparedStament = connection.prepareStatement("select * from easybuy_user limit ? , ? ");
			preparedStament.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			preparedStament.setInt(2, page.getPageSize());
			resultSet = preparedStament.executeQuery();
			List<UserBean> list = new ArrayList<>();
			while (resultSet.next())

			{
				UserBean userBean = new UserBean();
				userBean.setEu_user_id(resultSet.getString(1));
				userBean.setEu_user_name(resultSet.getString(2));
				userBean.setEu_sex(resultSet.getString(4));
				userBean.setEu_email(resultSet.getString(7));
				userBean.setEu_mobile(resultSet.getString(8));
				userBean.setEu_address(resultSet.getString(9));
				list.add(userBean);

			}
			page.setList(list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return page;
	}

}
