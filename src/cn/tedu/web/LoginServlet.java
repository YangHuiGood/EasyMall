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
		//��ȡweb.xml�����õ��ַ���
		String encode = this.getServletContext().getInitParameter("encode");
		//1.��������-�������롢Ӧ������
		   //post��������
		req.setCharacterEncoding(encode);
		//2.�����������
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remname = req.getParameter("remname");
		//3.����֤(��)
		//4.ִ���߼�
		 //��ס�û���
		 //�ж��û��Ƿ�ѡ�˼�ס�û�����ѡ��
		if(remname != null && "true".equals(remname)){
			//��ѡ�˼�ס�û���
			Cookie cookie = new Cookie("remname",URLEncoder.encode(username, encode));
			//������Чʱ��
			cookie.setMaxAge(60*60*24*7);//����7��
			//�ֶ�����һ��·��-webӦ�øɵĸ�·��
			//EasyMall�����ó�������������Ĭ��webӦ�õ���req.getContextPath()���� ""
			//setPath("")->��Ч������setPath(""+"/")
			cookie.setPath(req.getContextPath()+"/");
			//��cookie�콾��response��
			resp.addCookie(cookie);
		}else{
			//û�й�ѡ��ס�û���
			Cookie cookie = new Cookie("remname","");
			cookie.setPath(req.getContextPath()+"/");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
		//5.����ִ�н������Ӧ������
		//��¼
		//�ж��û����û����������Ƿ���ȷ
		String sql = "select * from user where username = ? and password = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				req.getSession().setAttribute("user", username);
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
				
			}else{
				req.setAttribute("errMsg", "�û������������");
				//����ת��
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("��¼�����쳣"+e.getMessage());
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		
		//�������ض�����ҳ
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
