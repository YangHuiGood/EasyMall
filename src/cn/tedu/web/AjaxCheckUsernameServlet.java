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
		//��ȡweb.xml�����õ��ַ���
		ServletContext sc = this.getServletContext();
		String encode = sc.getInitParameter("encode");
		//1.������������ ��������  Ӧ������
		resp.setContentType("text/html;charset="+encode);
		//��ȡ�������
		String username = req.getParameter("username");
		//����get��������
		byte[] array = username.getBytes("iso8859-1");
		username = new String(array,encode);
		
		//��ѯ���ݿ⣬���û����Ƿ����
		
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		boolean flag = service.hasUsername(username);
		if(flag){
			resp.getWriter().write("�Բ��𣡸��û����Ѵ��ڣ�");
		}else{
			resp.getWriter().write("��ϲ�������û�����ʹ�ã�");
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
