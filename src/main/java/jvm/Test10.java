package jvm;

/**
 * Created by Admin on 2018/5/12.
 */
public class Test10 {
    private Object instance;

    public Test10() {
        byte[] m = new byte[20 * 1024 * 1024];
    }

    public static void main(String[] args) {
        Test10 m1 = new Test10();
        Test10 m2 = new Test10();

        m1.instance = m2;
        m2.instance = m1;

        m1 = null;
        m2 = null;
        System.gc();
    }
}


