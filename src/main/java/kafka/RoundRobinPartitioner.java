package kafka;


import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Admin on 2018/1/27.
 */
public class RoundRobinPartitioner implements Partitioner {
    private static AtomicLong next = new AtomicLong();

    public RoundRobinPartitioner(VerifiableProperties verifiableProperties){}

    public int partition(Object key, int numPartitions) {
        long nextIndex = next.incrementAndGet();

        return (int)nextIndex % numPartitions;
    }
}
