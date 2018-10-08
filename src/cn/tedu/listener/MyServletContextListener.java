package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// ��webӦ�ñ�������ɣ��������ᴴ��ServletContext����
		// �ö��󱻴�����ɺ󣬻���ð󶨵ļ�����contextInitialized
		// ֮�����κ�jspҳ���У�������ֱ��ͨ��${app}�ķ�ʽ����ȡ��ǰwebӳ�������·��
		// ͨ���¼������ȡ��ServletContext
		ServletContext sc = sce.getServletContext();
		// ��application�����������һ����ֵ�� ���� app,ֵ�ǵ�ǰwebӦ��ӳ�������·��
		sc.setAttribute("app", sc.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
