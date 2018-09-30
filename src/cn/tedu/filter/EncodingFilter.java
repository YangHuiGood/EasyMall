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

	private String encode =null;//保存全局字符集
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 解决应答乱码
		response.setContentType("text/html;charset="+encode);
		MyRequest req = new MyRequest((HttpServletRequest)request);
//		//解决post请求乱码
//		request.setCharacterEncoding(encode);
//		//解决get请求乱码
//		
//		//用户第一次调用getParameterMap方法，返回一个保存了全部请求的map集合
//		Map<String, String[]> map = request.getParameterMap();
//		//对map集合中的所有value进行手动编解码
//		//1.遍历map集合
//		for(Entry<String,String[]> entry:map.entrySet()){
//			//获取一个请求参数的值，以数组形式保存
//			String[] values = entry.getValue();
//			if(values != null){//如果该请求参数的值不为null
//				//遍历这个值得数组，对其中的每一个值进行手动编解码
//				for(int i=0;i<values.length;i++){
//					String value = values[i];
//					//手动编解码
//					value = new String(value.getBytes("iso8859-1"),encode);
//					values[i] = value;
//				}
//				
//			}
//		}
			
			
			
			
			
		//放行请求
		chain.doFilter(req, response);
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//通过fileConfig对象获取ServletContext
		ServletContext sc = filterConfig.getServletContext();
		encode = sc.getInitParameter("encode");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	class MyRequest extends HttpServletRequestWrapper{

		//该变量表明paramMap是否被手动编解码，默认没有
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
			//获取被装饰者的map集合
			Map<String, String[]> paramMap = this.getRequest().getParameterMap();
			if(this.hasEncode == false){
				//遍历map集合，进行手动编解码
				for(Entry<String,String[]> entry:paramMap.entrySet()){
					//获取一个请求参数对应的值
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
