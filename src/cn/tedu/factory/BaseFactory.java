package cn.tedu.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ͨ�õĹ�����
 * ����ʵ�ֲ����֮��Ľ���
 * ���ù���+�ӿ�+�����ļ��ķ�ʽ����Ϲ�������
 * @author tarena
 *
 */
public class BaseFactory {
	//����
	// 1.˽�еĹ�����
	private BaseFactory(){
		//��ȡ�����ļ��ľ���·��
		String path = BaseFactory.class.getClassLoader().getResource("config.properties").getPath();
		//ʹ�ù�������������ļ�����
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//2.˽�еľ�̬�ı����Ψһʵ��
	private static BaseFactory factory = new BaseFactory();
	
	//3.���еľ�̬�ķ��ر���ʵ���ķ���
	public static BaseFactory getFactory(){
		return factory;
	}
	private Properties prop = new Properties();
	
	public <T> T getInstance(Class<T> c){
		String value = prop.getProperty(c.getSimpleName());
		try {
			Class cz = Class.forName(value);
			return (T) cz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
