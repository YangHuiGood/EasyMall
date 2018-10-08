package cn.tedu.web;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProdImgServlet extends HttpServlet {

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
		//��ȡ�������
		String imgurl = req.getParameter("imgurl");
		//����֤���ԣ�
		//3. ִ���߼�-ͨ����������ȡͼƬ�����ݣ�ͨ�������д���û�
		FileInputStream fis = null;
		try {
			// ����ͼƬurl��ȡͼƬ����ʵ·��
			String realPath = this.getServletContext().getRealPath(imgurl);
			// ��Ҫ��·����ͼƬ�ڷ������ϵľ���·��
			fis = new FileInputStream(realPath);
			// ��ȡ��Ӧ��ʵ���������������ݵ������
			ServletOutputStream sos = resp.getOutputStream();
			// �ӷ��������ض�ȡͼƬ��һ��������
			byte[] array = new byte[1024];
			int len = fis.read(array);
			while(len != -1){
				//��Ӧ��ʵ�����������ͼƬ������
				sos.write(array,0,len);
				len = fis.read(array);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(fis != null){
				fis.close();
			}
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
