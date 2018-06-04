import java.text.SimpleDateFormat
import java.util.{UUID, Date}

import org.apache.spark.{SparkContext, SparkConf}

import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 2017/8/18.
  */
class WebLogSession{

}
object WebLogSession {

  val sdf_standard = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")
  val sdf_hdfsfolder = new SimpleDateFormat("yy-MM-dd")

  /**
    * 自定义的将日志按照日志创建的时间升序排序
    *
    * @param elementA
    * @param elementB
    * @return
    */
  def dateComparator(elementA:String, elementB:String):Boolean = {
    WebLogSession.sdf_standard.parse(elementA.split(" ")(1)).getTime < WebLogSession.sdf_standard.parse(elementB.split(" ")(1)).getTime
  }

  /**
    * 将每一个IP的日志信息按照30分钟的session分类并拼接上session信息
 *
    * @param logInfoGroup
    * @return
    */
  def distinctLogInfoBySession(logInfoGroup: List[String]): List[String] = {
    val logInfoBySession:ListBuffer[String] = new ListBuffer[String]
    var lastRequestTime:Long = 0
    var lastSessionID:String = ""

    for (logInfo <- logInfoGroup) {
      // 某IP的用户第一次访问网站的记录作为该用户的第一个session日志
      if (lastRequestTime == 0) {
        lastSessionID = UUID.randomUUID().toString()
        // 将该次访问日志记录拼上sessionID并放进按session分类的日志信息数组中
        logInfoBySession += lastSessionID + " " + logInfo
        // 记录该次访问日志的时间，并用户和下一条访问记录比较，看看时间是否超过30分钟，是的话代表新session开始
        lastRequestTime = sdf_standard.parse(logInfo.split(" ")(1)).getTime
      } else {

        // 当前记录和上一次访问时间相比超过30分钟，认为是一个新的session，重新生成sessionID
        if (sdf_standard.parse(logInfo.split(" ")(1)).getTime - lastRequestTime >= 30 * 60 * 1000) {
          lastSessionID = UUID.randomUUID().toString()
          logInfoBySession += lastSessionID + " " + logInfo
          // 记录该次访问日志时间，作为一个新的session开始的时间，并继续和下一条记录比较，看看间隔是否超过30分钟
          lastRequestTime = sdf_standard.parse(logInfo.split(" ")(1)).getTime
        } else {
          // 当前日志记录和上一次的访问时间相比没有超过30分钟，所以认为是同一个Session，继续沿用之前的sessionID
          logInfoBySession += lastSessionID + " " + logInfo
        }
      }
    }
    return logInfoBySession.toList
  }


  def main(args: Array[String]) {
    val curDate = new Date()
    val logFile = "E:\\WorkSpace\\bigdata\\src\\main\\scala\\logLinesMapRdd.log.2017-04-07"

    val conf = new SparkConf().setAppName("WebLogSession").setMaster("local")
    val sc = new SparkContext(conf)
    val logFileSource = sc.textFile(logFile,1).cache()

    // 将log信息变为(ip,log信息)的tuple格式，也就是按ip地址将log分组
    val logLinesKVMapRDD = logFileSource.map(line => (line.split(" ")(0),line)).groupByKey()
//    logLinesKVMapRDD.collect().foreach(println)

    // 对每个(IP[String],log信息[Iterator<String>])中的日志按照时间升序排序
    // 排序完后(IP[String],log信息[Iterator<String>])的格式变为log信息[Iterator<String>]
    val sortedLogRDD = logLinesKVMapRDD.map(_._2.toList.sortWith((A,B) => WebLogSession.dateComparator(A,B)))
//    sortedLogRDD.collect().foreach(println)
//    println(sortedLogRDD.getClass().getName)
//    // 将每一个IP的日志信息按照30分钟的session分类并拼接上session信息
    val logInfoBySessionRDD = sortedLogRDD.map(WebLogSession.distinctLogInfoBySession(_))
    logInfoBySessionRDD.collect().foreach(println)
    // 将List中的日志信息拆分成单条日志信息输出
//    val logInfoWithSessionRDD = logInfoBySessionRDD.flatMap(line => line).saveAsTextFile("hdfs://114.80.18.42:/weblogclean/session_log" + WebLogSession.sdf_hdfsfolder.format(curDate))


  }

}



























