package duoxianc;

/**
 * Created by Admin on 2017/8/29.
 * 实现Runnable接口
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyRunnable implements Runnable{
    int i = 0;
    public void run() {
        while (i < 10) {
            System.out.println(Thread.currentThread().getName() + "   i的值   " + i);
            i++;
        }
    }
}
