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
		//1.������������
		  //��������
		req.setCharacterEncoding("utf-8");
		  //Ӧ������
		resp.setContentType("text/html;charset=utf-8");
		//2.���ձ�����
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String valistr = req.getParameter("valistr");
	    //����֤
		//��֤����֤
		if(WebUtils.isEmpty(valistr)){
			//��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "��֤�벻��Ϊ�գ�");
			//������ת����regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}else{
			//��ȡ������session�е���֤��
			HttpSession session = req.getSession(false);
			boolean flag = true;//Ĭ����֤��û����
			if(session == null || session.getAttribute("code") == null){
				//û��session���󣬻���session��û����ȷ����֤���ı�
				flag= false;
			}else{
				String text = (String) session.getAttribute("code");
				if(!text.equalsIgnoreCase(valistr)){
					//��֤�벻ƥ��
					flag = false;
				}
			}
			if(flag == false){
				req.setAttribute("errMsg", "��֤�����");
				//������ת����regist.jsp
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				return;
			}
		}
		
		 //1)�ǿ���֤-��Ϊnull,qie trim()��Ϊ�մ�
		if(WebUtils.isEmpty(username)){
			//��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "�û�������Ϊ�գ�");
			//������ת����regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		if(WebUtils.isEmpty(password)){
			//��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "���벻��Ϊ�գ�");
			//������ת����regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		if(WebUtils.isEmpty(password2)){
			//��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "ȷ�����벻��Ϊ�գ�");
			//������ת����regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		if(WebUtils.isEmpty(email)){
			//��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "����������Ϊ�գ�");
			//������ת����regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		
		//2)����һ����֤
		if(!password.equals(password2)){
			//��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "�����������һ�£�");
			//������ת����regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		//3)�����ʽ��֤
		String reg = "^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(reg)){
			//��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "�����ʽ����ȷ��");
			//������ת����regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		
		//4)�û����Ƿ������֤
		
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		boolean flag = service.hasUsername(username);
		if (flag) {
			// ��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "�û����Ѵ��ڣ�");
			// ������ת����regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		
		
		
		
		

		
		
		
	//4.�����ݴ������ݿ�
		User user = new User(-1,username,password,nickname,email);
		//����service����ʵ��ע���û�
		boolean flag2 = service.registUser(user);
		if(flag2){
			//5.����ɹ�-��ʾ�ɹ���Ϣ����ʱˢ�µ���ҳ
			resp.getWriter().write("<h1 style='text-align: center;color:red'>��ϲ����ע��ɹ���3�����ת����ҳ</h1>");
			resp.setHeader("refresh","3;url="+req.getContextPath()+"/index.jsp");
		}else{
			//��request����������Ӵ�����ʾ��Ϣ
			req.setAttribute("errMsg", "ע������쳣�����Ժ�����....");
			//������ת����regist.jsp
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
