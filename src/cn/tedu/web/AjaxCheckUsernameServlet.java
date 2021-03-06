package cn.tedu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BaseFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.UserServiceImpl;
import cn.tedu.util.JDBCUtils;

public class AjaxCheckUsernameServlet extends HttpServlet {

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
//		//获取web.xml中配置的字符集
//		ServletContext sc = this.getServletContext();
//		String encode = sc.getInitParameter("encode");
//		//1.处理乱码问题 请求乱码  应答乱码
//		resp.setContentType("text/html;charset="+encode);
		//获取请求参数
		String username = req.getParameter("username");
//		//处理get请求乱码
//		byte[] array = username.getBytes("iso8859-1");
//		username = new String(array,encode);
		
		//查询数据库，看用户名是否存在
		
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		boolean flag = service.hasUsername(username);
		if(flag){
			resp.getWriter().write("对不起！该用户名已存在！");
		}else{
			resp.getWriter().write("恭喜您！该用户名可使用！");
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
