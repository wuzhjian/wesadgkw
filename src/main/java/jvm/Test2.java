package jvm;

/**
 * Created by Admin on 2018/1/10.
 */
public class Test2 {

    /**
     * 三个字
     */
    public void test1() {
        {
            // a 的作用域在这个大括号内
            long a = 0;
        }
        // b复用a
        long b = 0;
    }

    /**
     * 五个字
     */
    public void test2() {
        long a = 0;
        long b = 0;
    }
}
