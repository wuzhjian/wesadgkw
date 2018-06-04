package thread;

/**
 * Created by Admin on 2018/3/26.
 */
public class Reentrant2 {
    Lock lock = new Lock();

    public void outer() throws InterruptedException {
        lock.lock();
        inner();
        lock.unlock();
    }

    public synchronized void inner() throws InterruptedException {
        lock.lock();
        // do something
        lock.unlock();
    }
}
