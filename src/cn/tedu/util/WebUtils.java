package cn.tedu.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	/**
	 * 用来验证字符串是否为null或空串的方法
	 * @param str 被验证的字符串
	 * @return true- 字符串为null 或trim之后为空串
	 *         false- 字符串不为空或空串
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
}
