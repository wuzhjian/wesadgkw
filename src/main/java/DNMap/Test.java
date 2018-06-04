package DNMap;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/4.
 */
public class Test {
    public static void main(String[] args) {


        DNHashMap<String, String> dnmap = new DNHashMap<String, String>();

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dnmap.put("key" + i, "value" + i);
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println("key: " + "key" + i + " value:" + dnmap.get("key" + i));
        }

        long t2 = System.currentTimeMillis();

        // System.out.println("DNHashMap 耗时： " + (t2-t1));
        System.out.println("---------------------------------HashMap-------------------------------");

        HashMap<String, String> map = new HashMap<String, String>();

        long t3 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            map.put("key" + i, "value" + i);
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println("key: " + "key" + i + " value:" + map.get("key" + i));
        }

        long t4 = System.currentTimeMillis();

        System.out.println("DNHashMap 耗时： " + (t2-t1));
        System.out.println("HashMap 耗时： " + (t4-t3));

    }
}
