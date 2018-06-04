/**
 * Created by Admin on 2017/8/13.
 */
public class ss {
    public long ss = 100000L;

    public static void main(String[] args) {
        Integer a =1;
        Integer b = a;
        b++;
        System.out.println(a);
        System.out.println(b);
        StringBuffer s1 = new StringBuffer("Hello");
        StringBuffer s2 = new StringBuffer("Hello");
        changeBuffer(s1,s2);
        System.out.println(s1);
        System.out.println(s2);
    }

    private static void changeBuffer(StringBuffer s1, StringBuffer s2) {
        s1.append("world");
        s2 = s1;
    }
}
