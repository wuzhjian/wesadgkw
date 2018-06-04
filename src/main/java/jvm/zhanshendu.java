package jvm;

/**
 * Created by Admin on 2018/1/9.
 * 栈深度
 */
public class zhanshendu {
    private int count = 0;

    public void testAdd(long a,long b, long c) {
        count++;
        testAdd(a,b,c);
    }

    public void test() {
        try {
            testAdd(1L,2L,3L);
        }catch (Throwable e){
            // java.lang.StackOverflowError
            System.out.println(e);
            System.out.println("栈深度："+ count);
        }
    }

    public static void main(String[] args) {
        new zhanshendu().test();
    }
}
