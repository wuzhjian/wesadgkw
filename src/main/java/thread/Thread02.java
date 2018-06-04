package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Admin on 2018/4/21.
 */
public class Thread02 implements Callable<String> {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Thread02());
        new Thread(task).start();
        task.cancel(true);
        System.out.println(task.get());
    }

    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(i);
        }
        return sb.toString();
    }
}


