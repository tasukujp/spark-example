import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.rdd.RDD;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tasukujp on 2016/01/09.
 */
public class RddSample {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("example");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> distData = sc.parallelize(data);

        List<String> list = Arrays.asList("hoge", "fuga", "bar");
        JavaRDD<String> hoge = sc.parallelize(list);
        JavaRDD<String> fuga = hoge.filter(a -> a.contains("Spark"));
        fuga.toArray();
        fuga.unpersist();
        fuga.first();

        JavaPairRDD<String, Integer> bar = sc.parallelizePairs(null);
        JavaRDD aaaaa = distData.map(Double::new);
        JavaDoubleRDD distDouble = distData.mapToDouble(Double::new);
        distDouble.mean();


//        MLUtils.loadLibSVMFile(jsc.sc(), datapath).toJavaRDD();
        sc.stop();
    }
}
