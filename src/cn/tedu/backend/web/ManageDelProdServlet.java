package cn.tedu.backend.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BaseFactory;
import cn.tedu.service.ProdService;

public class ManageDelProdServlet extends HttpServlet {

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
		//接收参数
		String pidStr = req.getParameter("pid");
		int pid = Integer.parseInt(pidStr);
		//表单验证
		//调用service逻辑执行
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag = service.prodDel(pid);
		//根据返回的结果转发相应的视图
		String msg = null;
		if(flag){
			msg = "删除成功";
		}else{
			msg = "删除失败";
		}
		resp.getWriter().write(msg);
		resp.setHeader("refresh", "3;url="+req.getContextPath()+"/ManageProdListServlet");
		
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
