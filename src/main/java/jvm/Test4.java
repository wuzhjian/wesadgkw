package jvm;

/**
 * Created by Admin on 2018/1/11.
 */
public class Test4 {
    public static void main(String[] args) {
        byte[] b1 = new byte[1024*1024];
        byte[] b2 = new byte[1024*1024];
        System.gc();
    }
}
