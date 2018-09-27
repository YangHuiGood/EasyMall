package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BaseFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.UserServiceImpl;
import cn.tedu.util.JDBCUtils;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取web.xml中配置的字符集
		String encode = this.getServletContext().getInitParameter("encode");
		//1.处理乱码-请求乱码、应答乱码
		   //post请求乱码
		req.setCharacterEncoding(encode);
		//2.接收请求参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remname = req.getParameter("remname");
		//3.表单验证(略)
		//4.执行逻辑
		 //记住用户名
		 //判断用户是否勾选了记住用户名的选项
		if(remname != null && "true".equals(remname)){
			//勾选了记住用户名
			Cookie cookie = new Cookie("remname",URLEncoder.encode(username, encode));
			//设置有效时间
			cookie.setMaxAge(60*60*24*7);//保存7天
			//手动设置一个路径-web应用干的根路径
			//EasyMall被配置成了虚拟主机的默认web应用导致req.getContextPath()返回 ""
			//setPath("")->无效，所以setPath(""+"/")
			cookie.setPath(req.getContextPath()+"/");
			//将cookie天骄到response中
			resp.addCookie(cookie);
		}else{
			//没有勾选记住用户名
			Cookie cookie = new Cookie("remname","");
			cookie.setPath(req.getContextPath()+"/");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
		//5.根据执行结果返回应答内容
		//登录
		//判断用户的用户名和密码是否正确
		
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		User user = null;
		try {
			user = service.login(username,password);
		} catch (MsgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("errMsg", e.getMessage());
			//请求转发
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		if(user != null){
			//直接添加用户对象在session中，供后续使用
			req.getSession().setAttribute("user", user);
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			
		}else{
			req.setAttribute("errMsg", "用户名或密码错误");
			//请求转发
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
		//将请求重定向到首页
//		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req, resp);
	}

}
