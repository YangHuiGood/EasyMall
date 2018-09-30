package cn.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter{

	private String encode =null;//����ȫ���ַ���
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// ���Ӧ������
		response.setContentType("text/html;charset="+encode);
		MyRequest req = new MyRequest((HttpServletRequest)request);
//		//���post��������
//		request.setCharacterEncoding(encode);
//		//���get��������
//		
//		//�û���һ�ε���getParameterMap����������һ��������ȫ�������map����
//		Map<String, String[]> map = request.getParameterMap();
//		//��map�����е�����value�����ֶ������
//		//1.����map����
//		for(Entry<String,String[]> entry:map.entrySet()){
//			//��ȡһ�����������ֵ����������ʽ����
//			String[] values = entry.getValue();
//			if(values != null){//��������������ֵ��Ϊnull
//				//�������ֵ�����飬�����е�ÿһ��ֵ�����ֶ������
//				for(int i=0;i<values.length;i++){
//					String value = values[i];
//					//�ֶ������
//					value = new String(value.getBytes("iso8859-1"),encode);
//					values[i] = value;
//				}
//				
//			}
//		}
			
			
			
			
			
		//��������
		chain.doFilter(req, response);
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//ͨ��fileConfig�����ȡServletContext
		ServletContext sc = filterConfig.getServletContext();
		encode = sc.getInitParameter("encode");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	class MyRequest extends HttpServletRequestWrapper{

		//�ñ�������paramMap�Ƿ��ֶ�����룬Ĭ��û��
		private boolean hasEncode = false;
		
		
		public MyRequest(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getParameter(String name) {
			Map<String,String[]> paramMap = this.getParameterMap();
			String[] values = paramMap.get(name);
			if(values == null){
				return null;
			}
			return values[0];
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			//��ȡ��װ���ߵ�map����
			Map<String, String[]> paramMap = this.getRequest().getParameterMap();
			if(this.hasEncode == false){
				//����map���ϣ������ֶ������
				for(Entry<String,String[]> entry:paramMap.entrySet()){
					//��ȡһ�����������Ӧ��ֵ
					String[] values = entry.getValue();
					if(values != null){
						for(int i=0;i<values.length;i++){
							try {
								values[i] = new String(values[i].getBytes("iso8859-1"),encode);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				this.hasEncode = true;
			}
			
			return paramMap;
		}

		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return this.getParameterMap().get(name);
		}
		
		
		
		
	}

}
