import java.text.SimpleDateFormat
import java.util.{Date, Locale}

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Administrator on 2017/8/18.
  */
class WebLogClean extends Serializable {
  def weblogParser(logLine:String):String = {
    // 过滤掉信息不全或者格式不正确的日志信息
    val isStandardLogInfo = logLine.split(" ").length >=12
    if (isStandardLogInfo) {

      // 过滤掉多余的符号
      val newLogLine:String = logLine.replace(" - - "," ").replace("[","").replace("+0800]","")
      // 将日志格式替换成正常格式
      val logInfoGroup:Array[String] = newLogLine.split(" ")
      val oldDateFormat = logInfoGroup(1)

      // 如果访问时间不存在也不是一个正确的信息
      if (oldDateFormat == "-") return " "
      val newDateFormat = WebLogClean.sdf_standard.format(WebLogClean.sdf_origin.parse(oldDateFormat))
      return newLogLine.replace(oldDateFormat,newDateFormat)
    } else {
      return " "
    }
  }
}
object WebLogClean {
  val sdf_origin = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",Locale.US)  // 26/May/2017:10:01:24
  val sdf_standard = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")
  val sdf_hdfsfolder = new SimpleDateFormat("yy-MM-dd")

  def main(args: Array[String]) {

    val curDate = new Date()
    val weblogclean = new WebLogClean
//    val logFile = "/input/tmonitor.log.2017-05-27"
//    val logFile = "file:///tmonitor.log.2017-05-08"
    val logFile = "E:\\WorkSpace\\bigdata\\src\\main\\scala\\tmonitor.log.2017-04-07"

    val conf = new SparkConf().setAppName("WebLogCleaner").set("spark.hadoop.validateOutputSpecs", "true").setMaster("local[1]")
    val sc = new SparkContext(conf)
    val logFileSource = sc.textFile(logFile,1).cache()

    val logLinesMapRdd = logFileSource.map(x=> weblogclean.weblogParser(x)).filter(line => line != " ").collect().foreach(println)
//    logLinesMapRdd.saveAsTextFile("hdfs://114.80.18.42:/weblogclean/"+WebLogClean.sdf_hdfsfolder.format(curDate))

  }

}



























