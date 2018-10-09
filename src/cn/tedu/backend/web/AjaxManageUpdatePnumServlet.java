package cn.tedu.backend.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BaseFactory;
import cn.tedu.service.ProdService;

public class AjaxManageUpdatePnumServlet extends HttpServlet {

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
		//���ղ���
		String pidStr = req.getParameter("pid");
		String pnumStr = req.getParameter("pnum");
		int pid = Integer.parseInt(pidStr);
		int pnum = Integer.parseInt(pnumStr);
		//����֤
		//����service�߼�ִ��
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag = service.prodUpdatePnum(pid,pnum);
		//���ݷ��ؽ��ִ������߼�
		if(flag){
			resp.getWriter().write("��Ʒ��������³ɹ�");
		}else{
			resp.getWriter().write("��Ʒ���������ʧ��");
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
