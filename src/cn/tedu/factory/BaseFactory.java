package cn.tedu.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 通用的工厂类
 * 用于实现层与层之间的解耦
 * 利用工厂+接口+配置文件的方式将耦合管理起来
 * @author tarena
 *
 */
public class BaseFactory {
	//单例
	// 1.私有的构造器
	private BaseFactory(){
		//获取配置文件的绝对路径
		String path = BaseFactory.class.getClassLoader().getResource("config.properties").getPath();
		//使用工具类加载配置文件内容
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
	
	//2.私有的静态的本类的唯一实例
	private static BaseFactory factory = new BaseFactory();
	
	//3.共有的静态的返回本类实例的方法
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
