package thread;

/**
 * Created by Admin on 2018/3/19.
 */
public class threadlocal {

    private ThreadLocal myThreadLocal = new ThreadLocal();

    private ThreadLocal myThreadLocal2 = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "This is the initial value";
        }
    };
    public ThreadLocal getMyThreadLocal() {
        String threadLocalValue = (String) myThreadLocal.get();
        return myThreadLocal;
    }

    public void setMyThreadLocal(ThreadLocal myThreadLocal) {
        myThreadLocal.set("A thread local value");
        this.myThreadLocal = myThreadLocal;

    }


    public static void main(String[] args) {

    }


}
