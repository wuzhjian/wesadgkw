package thread;

/**
 * Created by Admin on 2018/4/21.
 */
public class Thread01 implements Runnable {
    public void run() {
        System.out.println("无座搜嘎UIi  1");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {

        }
        System.out.println("无座搜嘎UIi   2");
    }

    public static void main(String[] args) {
        new Thread( new Thread01()).start();
       // new Thread( new Thread01()).run();
    }
}


