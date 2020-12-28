package job;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-18 22:11
 **/
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 定时任务
 */
public class TaskListener implements ServletContextListener {

    private Timer timer = null;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        System.out.println("定时器启动");

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 20); // 控制时，24小时制
            calendar.set(Calendar.MINUTE, 59);  // 控制分
            calendar.set(Calendar.SECOND, 0);  // 控制秒

            Date time = calendar.getTime(); // 执行任务的时间，可自行修改，此时为12:00:00

            timer = new Timer(true);

            //参数1：需要执行的类，参数2：执行时间，参数3：执行周期(参数可自行修改)
            //默认执行MyTask类下的run()方法；
            //timer.scheduleAtFixedRate(new MyTask(), time, 1000*5);  //隔一天(周期性)扫描执行方法，

            timer.schedule(new MyTask(), 2000 , 10000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
