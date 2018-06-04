package thread;

/**
 * Created by Admin on 2018/3/26.
 */
public class Lock2 {
    boolean isLocked = false;
    Thread lockedBy = null;
    int lockCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();

        while (isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockCount++;
        lockedBy = callingThread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockCount--;
            if (lockCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
