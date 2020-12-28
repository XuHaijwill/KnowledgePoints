package thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author xhj
 * @Description 获取线程信息
 * @Date 2020-03-18 22:11
 **/
public class OnlyMain {

    public static void main(String[] args) {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threads = mxBean.dumpAllThreads(true,true);
        for(ThreadInfo info : threads){
            System.out.println(info.getThreadId() + ":::" + info.getThreadName());
        }

    }
}
