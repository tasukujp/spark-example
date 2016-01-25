package com.example;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tasukujp on 2016/01/09.
 */
public class RDDSample {
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
        JavaRDD<Double> aaaaa = distData.map(v -> v * 1.08);
        JavaDoubleRDD distDouble = distData.mapToDouble(v -> v * 1.08);
        distDouble.distinct();
        distDouble.mean();

        JavaPairRDD<String, Integer> pairs = hoge.mapToPair(v -> new Tuple2(v, 1));
        pairs.reduceByKey()

//        MLUtils.loadLibSVMFile(jsc.sc(), datapath).toJavaRDD();
        sc.stop();
    }
}
