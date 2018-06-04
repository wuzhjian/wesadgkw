package duoxianc;

/**
 * Created by Admin on 2017/8/29.
 * 继承Thread类
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
class MyThread extends Thread{
    int i = 0;

    @Override
    public void run() {
        while (i < 10) {
            System.out.println(this.getName() + "    i的值   " + i);
            i++;
        }
    }
}
