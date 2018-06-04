import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2018/4/14.
  */
object wordCount {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("wordCount")
    val sc = new SparkContext(conf)

    val line = sc.textFile("file:///E:\\Workspace\\wordcount.txt")

    val wordcount = line.flatMap(_.split(" ")).map(x=>(x,1))
      .reduceByKey(_+_).count()
    // wordcount.collect().foreach(println(_))
  }
}
