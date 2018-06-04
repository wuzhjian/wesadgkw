package thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2018/5/30.
 */
public class TheadPoolTest {
    // 核心线程数理
    private static int corePoolSize = 3;

    // 最大线程数量
    private static int maxPoolSize = 5;

    // 线程存活时间：当前线程数理超过corePoolSize时，10s钟空闲即关闭线程
    private static int keepAlliveTime = 10000;

    // 缓冲队列
    private static java.util.concurrent.BlockingQueue<Runnable> workQueue = null;

    // 线程池
    private static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        workQueue = new LinkedBlockingDeque<Runnable>(5);
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAlliveTime, TimeUnit.SECONDS, workQueue);
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 200; i++) {
                System.out.println("=========第" + i + "次");
                threadPoolExecutor.execute(new Thread01());
                System.out.println("线程池中正在执行的线程数量：" + threadPoolExecutor.getPoolSize());
                System.out.println("线程池缓存的任务队列数量：" + threadPoolExecutor.getQueue().size());
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
