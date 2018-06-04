package thread;

import java.util.concurrent.*;

/**
 * Created by Admin on 2018/5/30.
 */
public class ThreadPoolTestV2 {
    // 固定大小的线程池
    // 初始化一个指定线程数的线程池，其中corePoolSize == maxinumPoolSize,使用LinkedBlockingQuene作为阻塞队列，当线程池中没有可执行任务时，也不会释放线程
    private static ExecutorService executor = Executors.newFixedThreadPool(10);
    private static ThreadPoolExecutor executor_ = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    // 缓冲线程池

}
