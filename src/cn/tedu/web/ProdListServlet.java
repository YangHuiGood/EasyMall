package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Prod;
import cn.tedu.factory.BaseFactory;
import cn.tedu.service.ProdService;

public class ProdListServlet extends HttpServlet {

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
		//获取请求参数
		//表单验证
		// 调用service执行逻辑
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list = service.listAllProd();
		//根据执行结果转发对应的视图
		//将数据存入request作用域
		req.setAttribute("list",list);
		// 将请求转发给prod_list.jsp
		req.getRequestDispatcher("/prod_list.jsp").forward(req, resp);

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
