package org.example;

import org.example.demo1.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateDemo1 {
     /** 步骤分析
	 * 	1、解析主配置文件
	 * 	2、根据配置文件创建SessionFactory
	 * 	3、根据SessionFactory创建Session
	 * 	4、开启事务
	 *  5、执行操作（保存）
            *  6、提交事务
	 *  7、释放资源
	 */
    @Test
    public void test1(){
        Customer c = new Customer();
        c.setCustName("黑马287期");
        // 1、解析主配置文件
        Configuration cfg = new Configuration();
        cfg.configure();
        // 2、根据配置文件创建SessionFactory
        SessionFactory factory = cfg.buildSessionFactory();
        // 3、根据SessionFactory创建Session
        Session session = factory.openSession();
        // 4、开启事务
        Transaction tx = session.beginTransaction();
        // 5、执行操作（保存）
        session.save(c);
        // 6、提交事务
        tx.commit();
        // 7、释放资源
        session.close();
        factory.close();
    }
}
