import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2017/10/21.
  */
object md6 {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("MD5")
    val sc = new SparkContext(conf)
    val hiveContext = new HiveContext(sc)
    hiveContext.sql("use t_tmonitor")
    hiveContext.udf.register("md5",(line: String)=>MD5Util.string2MD5(line))

    hiveContext.sql("set hive.exec.dynamic.partition=true")
    hiveContext.sql("set hive.exec.dynamic.partition.mode=nonstric")
    hiveContext.sql("set hive.exec.max.dynamic.partitions=100000")
    hiveContext.sql("set hive.exec.max.dynamic.partitions.pernode=100000")
    hiveContext.sql("select name,wordCount(name) as count,computeLength(name) as length from bigDataTable ")
    val sql =
      """
        |INSERT overwrite TABLE page_md5_map partition (pyear,pmonth,pday)
        |select md5(concat(idaction_url,idaction_path)) as md5,concat(idaction_url,idaction_path) as page_name,
        |pyear,pmonth,pday
        |from t_log_link_visit_action where pday='2017-02-15'
        |group by idaction_url,idaction_path,pyear,pmonth,pday
      """.stripMargin
    hiveContext.sql(sql)
  }
}
