package thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2018/3/27.
 */
public class ReadWriteLock3 {

    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();

    private int writeAccess = 0;
    private int writeRequests = 0;
    private Thread writingThread = null;

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread callingThread = Thread.currentThread();

        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        writeRequests--;
        writeAccess++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() {
        writeAccess--;
        if (writeAccess == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if (hashReaders()) return false;
        if (writingThread == null) return true;
        if (!isWriter(callingThread)) return false;
        return true;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    private boolean hashReaders() {
        return readingThreads.size() > 0;
    }

    

}
