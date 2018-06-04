package thread;

/**
 * Created by Admin on 2018/3/26.
 */
public class Reentrant {
    public synchronized void outer() {
        inner();
    }

    public synchronized void inner() {
        // do something
    }
}
