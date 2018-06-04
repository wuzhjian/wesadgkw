package kafka;

import kafka.producer.Partitioner;

/**
 * Created by Admin on 2018/1/27.
 */
public class HashPartitioner implements Partitioner{

    public int partition(Object key, int numPartitions) {
        if (key instanceof Integer) {
            return Math.abs(Integer.parseInt(key.toString())) % numPartitions;
        }
        return Math.abs(key.hashCode() % numPartitions);
    }
}
