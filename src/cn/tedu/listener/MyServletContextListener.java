package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 当web应用被加载完成，服务器会创建ServletContext对象
		// 该对象被创建完成后，会调用绑定的监听的contextInitialized
		// 之后，在任何jsp页面中，都可以直接通过${app}的方式，获取当前web映射的虚拟路径
		// 通过事件对象获取到ServletContext
		ServletContext sc = sce.getServletContext();
		// 向application作用域中添加一个键值对 键是 app,值是当前web应用映射的虚拟路径
		sc.setAttribute("app", sc.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
