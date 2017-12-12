package src.com.yz.services;

import java.util.List;

import com.yz.bean.Page;
import com.yz.bean.UserBean;

public interface UserServices {
	// 通过password和username 来判断用户是否合法
	public boolean isIllegleUser(String username, String pwd);

	// 获取用户的数据显示在列表中
	public List<UserBean> findAllUser();

	// 在这里分页的逻辑处理
	public Page findByPage(int currentPage);

	// 用于数据的更新
	public boolean updateUser(UserBean userBean);

	// 根据用户id获得用户对象
	public UserBean findUserById(String id);

	// 更具用户的ID s删除该用户
	public boolean deleteUser(String id);

}
