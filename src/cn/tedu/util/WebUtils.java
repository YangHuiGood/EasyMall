package cn.tedu.util;

public class WebUtils {
	/**
	 * ������֤�ַ����Ƿ�Ϊnull��մ��ķ���
	 * @param str ����֤���ַ���
	 * @return true- �ַ���Ϊnull ��trim֮��Ϊ�մ�
	 *         false- �ַ�����Ϊ�ջ�մ�
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
}
