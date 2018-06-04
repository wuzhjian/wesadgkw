import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 2017/9/2.
 */
public class BroadcasterVariable {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("BroadcastVariable")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        final int factor = 3;
        final Broadcast<Integer> factorBroadcast = sc.broadcast(factor);

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        JavaRDD<Integer> multipleNumbers = numbers.map(new Function<Integer, Integer>() {
            public Integer call(Integer integer) throws Exception {
                int fac = factorBroadcast.value();
                return integer*fac;
            }
        });
        multipleNumbers.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });


    }
}
