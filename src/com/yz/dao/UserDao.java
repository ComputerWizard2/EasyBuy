package com.yz.dao;

import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.UserBean;

public interface UserDao {
	// 访问User 数据库查询判断当前用户是否存在
	public boolean isIllegleUser(String username, String pwd);

	// 查找所有数据对象
	public List<UserBean> findAllUser();

	// 查找数据总数
	public int findAllUserCount();

	// 分页查询
	public Page findUserByPage(Page page);

	// 更新用户数据
	public boolean updataUser(UserBean userBean);

	// 根据用户id获得用户信息
	public UserBean findUserById(String id);

	// 删除用户的信息根据id
	public boolean deleteUser(String id);

}
