package cn.tedu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.domain.User;
import cn.tedu.factory.BaseFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.UserServiceImpl;
import cn.tedu.util.JDBCUtils;
import cn.tedu.util.WebUtils;

public class RegistServlet extends HttpServlet {

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
		//1.处理乱码问题
		  //请求乱码
		req.setCharacterEncoding("utf-8");
		  //应答乱码
		resp.setContentType("text/html;charset=utf-8");
		//2.接收表单数据
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String valistr = req.getParameter("valistr");
	    //表单验证
		//验证码验证
		if(WebUtils.isEmpty(valistr)){
			//向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "验证码不能为空！");
			//将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}else{
			//获取保存在session中的验证码
			HttpSession session = req.getSession(false);
			boolean flag = true;//默认验证码没问题
			if(session == null || session.getAttribute("code") == null){
				//没有session对象，或者session中没有正确的验证码文本
				flag= false;
			}else{
				String text = (String) session.getAttribute("code");
				if(!text.equalsIgnoreCase(valistr)){
					//验证码不匹配
					flag = false;
				}
			}
			if(flag == false){
				req.setAttribute("errMsg", "验证码错误");
				//将请求转发给regist.jsp
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				return;
			}
		}
		
		 //1)非空验证-不为null,qie trim()不为空串
		if(WebUtils.isEmpty(username)){
			//向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "用户名不能为空！");
			//将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		if(WebUtils.isEmpty(password)){
			//向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "密码不能为空！");
			//将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		if(WebUtils.isEmpty(password2)){
			//向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "确认密码不能为空！");
			//将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		if(WebUtils.isEmpty(email)){
			//向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "邮箱名不能为空！");
			//将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		
		//2)密码一致验证
		if(!password.equals(password2)){
			//向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "两次密码必须一致！");
			//将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		//3)邮箱格式验证
		String reg = "^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(reg)){
			//向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "邮箱格式不正确！");
			//将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		
		//4)用户名是否存在验证
		
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		boolean flag = service.hasUsername(username);
		if (flag) {
			// 向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "用户名已存在！");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		
		
		
		
		

		
		
		
	//4.将数据存入数据库
		User user = new User(-1,username,password,nickname,email);
		//调用service方法实现注册用户
		boolean flag2 = service.registUser(user);
		if(flag2){
			//5.保存成功-提示成功信息，定时刷新到首页
			resp.getWriter().write("<h1 style='text-align: center;color:red'>恭喜您，注册成功！3秒后跳转至首页</h1>");
			resp.setHeader("refresh","3;url="+req.getContextPath()+"/index.jsp");
		}else{
			//向request作用域中添加错误提示信息
			req.setAttribute("errMsg", "注册出现异常，请稍后重试....");
			//将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}

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
