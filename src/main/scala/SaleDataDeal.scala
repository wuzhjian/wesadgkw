import org.apache.hadoop.hive.ql.exec.spark.session.SparkSession
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext

/**
  * Created by Admin on 2017/8/20.
  */
object SaleDataDeal {
  def main(args: Array[String]) {
    if (args.length != 3){
      sys.exit(1)
    }

    Logger.getLogger("org.apache.hadoop").setLevel(Level.ERROR)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR)

    // 设置运行环境
    val spark = new SparkConf().setAppName("assda")
    val sc = new SparkContext(spark)
    val sqlContext = new SQLContext(sc)
    // 使用spaekSql查出每个店的销售数量和金额
    sqlContext.sql("use hive")
    val sqldata = sqlContext.sql("select a.locationid, sum(b.qty) totalqty,sum(b.amount) totalamount " + "" +
      " from tbStock a join tbStockDetail b on a.ordernumber=b.ordernumber group by a.locationid" ).cache()

    // 将查询数据转化成向量
    val vectors = sqldata.rdd.map(r=> Vectors.dense(r.getLong(1).toDouble,r.getLong(2).toDouble))

    // 对数据聚集
    val numClusters = args(0).toInt
    val numIterations = args(1).toInt

    val model = KMeans.train(vectors, numClusters, numIterations)

    // 打印聚簇中心点
    val result = sqldata.rdd.map{
      line =>
        val linevectore = Vectors.dense(line.getLong(1).toDouble, line.getLong(2).toDouble)
        val prediction = model.predict(linevectore)
        line(0) + " " + line.getLong(1) + " " + line.getLong(2) + " " + prediction
    }

    println("Prediction detal:")
    result.collect().foreach(println)
    result.saveAsTextFile(args(2))

  }
}
