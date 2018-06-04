package jvm;

/**
 * Created by Admin on 2018/1/9.
 */
public class Test {
    // 主线程 非守护线程
    public static void main(String[] args) {
        int length =args.length;
        for (int i = 0; i < length; i++) {
            System.out.println(args[i]+" ");
        }
        System.out.println();
    }
}


