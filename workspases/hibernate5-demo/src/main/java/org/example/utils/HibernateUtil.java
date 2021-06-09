package org.example.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 抽取hibernate的工具类
 * @author
 *
 */
public class HibernateUtil {

	private static SessionFactory factory;
	
	//了解：hibernate把可以预见的异常都转成了运行时异常
	static{
		try {
			Configuration cfg = new Configuration();
			cfg.configure();
			factory = cfg.buildSessionFactory();
			//System.out.println(factory);
		} catch (ExceptionInInitializerError e) {
			throw new ExceptionInInitializerError("初始化SessionFactory失败，请检查配置文件");
		}
	}
	
	/**
	 * 获取一个新的Session对象
	 * @return
	 */
	public static Session openSession(){
		return factory.openSession();
	}
}
