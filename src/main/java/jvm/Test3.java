package jvm;

/**
 * Created by Admin on 2018/1/10.
 */
public class Test3 {
    public static void test1() {
        // 方法内部不能通过强制gc将局部变量gc掉
        {
            byte[] b = new byte[6*1024*1024];
            b = null;
        }
//        int a= 0;
        System.gc();
        System.out.println("hello world");
    }
    public static void test2() {
        {
            int c = 0;
            byte[] b = new byte[6*1024*1024];
        }
        int a = 0;  // a复用了c的空间
        int d = 0;
        System.gc();
        System.out.println("hello world");
    }

    public static void test3() {
        byte[] b = new byte[6*1024*1024];
        System.out.println("hello world");
    }
    public static void main(String[] args) {
        // java -verbose:gc Test3   打印GC信息
        test3();
        System.gc();  //方法执行完之后可以被gc掉
    }
}
