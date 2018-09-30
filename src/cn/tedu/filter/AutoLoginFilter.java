package cn.tedu.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BaseFactory;
import cn.tedu.service.UserService;

public class AutoLoginFilter implements Filter{

	private String encode = null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encode = filterConfig.getServletContext().getInitParameter("encode");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		//1.�û�û�е�¼
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("user") == null){
			Cookie[] cs = req.getCookies();
			Cookie findC = null;
			if(cs != null){
				for(Cookie c:cs){
					if("autologin".equals(c.getName())){
						findC =  c;
						break;
					}
				}
			}
			//2.�û�Я�����Զ���¼��Cookie
			if(findC != null){
				//3.�Զ���¼cookie�б�����û�������������ȷ��
				String value = findC.getValue();//username#password
				String username = URLDecoder.decode(value.split("#")[0],encode);
				String password = value.split("#")[1];
				UserService service = BaseFactory.getFactory().getInstance(UserService.class);
				try {
					User user = service.login(username, password);
					if(user != null){
						//��¼�ɹ�
					   //4.����3�㶼���ϣ��ڽ����Զ���¼->���û���Ϣ����session
						//session.setAttribute("user", user); //��ָ���쳣
						req.getSession().setAttribute("user", user);
					}
				} catch (MsgException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		//��������
		chain.doFilter(request, response);
		
      		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
