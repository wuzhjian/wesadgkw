package jvm;

import java.util.Vector;

/**
 * Created by Admin on 2018/1/11.
 * java -Xmx11M Test5
 */
public class Test5 {
    public static void main(String[] args) {
        Vector v = new Vector();
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024*1024];
            v.add(b);
            System.out.println(i);
        }
        // 获取系统可用的最大堆内存
        System.out.println("系统可用的最大堆内存"+ Runtime.getRuntime().maxMemory()/1024/1024+"M");
    }
}
