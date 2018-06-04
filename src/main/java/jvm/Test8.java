package jvm;

/**
 * Created by Admin on 2018/1/20.
 * 测试最大线程数
 */
public class Test8 {
    public static class MyThread extends Thread {
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        int i = 0;
        try {
            for (i = 0; i < 10000000; i++) {
                new MyThread().start();
            }
        } catch (OutOfMemoryError e) {
            System.out.println("线程数量" + i);
        }
    }
}
