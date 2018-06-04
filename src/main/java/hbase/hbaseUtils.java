package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by Admin on 2018/3/3.
 */
public class hbaseUtils {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test");
        Put put = new Put(Bytes.toBytes("rk-1"));
        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("q1"), Bytes.toBytes("v1"));
        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("q2"), Bytes.toBytes("v2"));



    }
}
