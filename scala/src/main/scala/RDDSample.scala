import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by tasukujp on 2016/01/17.
 */
class RDDSample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(Seq(1,2,3,4,5,6,7,8,9))
    val rdd2 = rdd.map(v => v.toDouble)
    val rdd3 = rdd.meanApprox(1);

    val st = rdd.stats()
    st.mean
    rdd2.histogram(5)
    rdd2.stdev()
    rdd2.mean()
    rdd2.getClass()

    val rdd4 = sc.parallelize(Seq("hoge","fuga"))
    rdd4.max()
  }
}
