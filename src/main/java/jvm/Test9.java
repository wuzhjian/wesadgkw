package jvm;

/**
 * Created by Admin on 2018/5/12.
 */
public class Test9 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";

        System.out.println(s1 == s2);

        String s3 = new String("abc");

        System.out.println(s1 == s3);
        System.out.println(s1 == s3.intern());
    }
}
