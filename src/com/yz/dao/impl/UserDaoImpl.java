package com.yz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
		} finally {
			Jdbc.closeResourse(connection, preparedStament, resultSet);
		}
		return i;
	}

	@Override
	public Page findUserByPage(Page page) {
		try {
			connection = Jdbc.getConnection();
			int startPage = (page.getCurrentPage() - 1) * page.getPageSize();

			preparedStament = connection.prepareStatement("select * from easybuy_user limit ? , ? ");
			preparedStament.setInt(1, startPage);
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
		} finally {
			Jdbc.closeResourse(connection, preparedStament, resultSet);

		}

		return page;
	}

	@Override
	public boolean updataUser(UserBean userBean) {
		try {
			connection = Jdbc.getConnection();
			preparedStament = connection.prepareStatement(
					"update easybuy_user set eu_password=?,eu_sex=?,eu_email=?,eu_mobile = ?,eu_address=? where eu_user_id=?");
			preparedStament.setString(6, userBean.getEu_user_id());
			preparedStament.setString(1, userBean.getEu_password());
			preparedStament.setString(2, userBean.getEu_sex());
			preparedStament.setString(3, userBean.getEu_email());
			preparedStament.setString(4, userBean.getEu_mobile());
			preparedStament.setString(5, userBean.getEu_address());
			int i = preparedStament.executeUpdate();
			if (i > 0) {
				return true;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, preparedStament, resultSet);
		}

		return false;
	}

	@Override
	public UserBean findUserById(String id) {
		// 根据用户的id 获取用户对象
		UserBean userBean = new UserBean();
		try {
			connection = Jdbc.getConnection();
			preparedStament = connection.prepareStatement("select * from easybuy_user where eu_user_id=?");
			preparedStament.setString(1, id);
			resultSet = preparedStament.executeQuery();

			/**
			 * 用户名 真实姓名 性别 Email 手机 操作
			 */
			while (resultSet.next()) {
				userBean.setEu_user_id(resultSet.getString(1));
				userBean.setEu_user_name(resultSet.getString(2));
				userBean.setEu_password(resultSet.getString(3));
				userBean.setEu_sex(resultSet.getString(4));
				userBean.setEu_email(resultSet.getString(7));
				userBean.setEu_mobile(resultSet.getString(8));
				userBean.setEu_address(resultSet.getString(9));
				userBean.setEu_birthday(new Date(resultSet.getDate(5).getTime()));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, preparedStament, resultSet);
		}
		return userBean;
	}

	public boolean deleteUser(String id) {
		// 删除一个用户的信息
		try {
			connection = Jdbc.getConnection();
			preparedStament = connection.prepareStatement("delete from easybuy_user where eu_user_id=?");
			preparedStament.setString(1, id);
			int i = preparedStament.executeUpdate();
			if (i > 0) {

				return true;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.closeResourse(connection, preparedStament, null);
		}

		return false;
	}

}
