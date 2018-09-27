package cn.tedu.service;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserService {
	//1.需要一个方法一调用就会返回永辉的用户名存在
	/**
	 * 用来判断用户名是否存在的方法
	 * @param username 判断的用户名
	 * @return true- 用户名存在  false- 用户名不存在
	 */
	boolean hasUsername(String username);
	
	/**
	 * 注册用户的方法
	 * @param user 保存了用户信息的javaBean
	 * @return true-注册成功 false- 注册失败
	 */
	boolean registUser(User user);
	
	/**
	 * 用户登录方法
	 * @param username 用户名
	 * @param password 用户密码
	 * @return  user对象或null
	 */
	User login(String username, String password) throws MsgException;
}
