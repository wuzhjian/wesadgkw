package jvm;

import java.util.Vector;

/**
 * Created by Admin on 2018/1/11.
 */
public class Test6 {
    public static void main(String[] args) {
        Vector v = new Vector();
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024*1024];
            v.add(b);
            if (v.size()==3){
                v.clear();
            }
        }

    }
}
