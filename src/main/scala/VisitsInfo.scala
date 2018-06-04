import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2017/8/19.
  */
class VisitsInfo {

}
object VisitsInfo {
  val sdf_standard = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")
  val sdf_hdfsfolder = new SimpleDateFormat("yy-MM-dd")

  // 自定义的将日志信息按日志创建的时间升序排序、
  def dateComparator(elementA:String, elementB:String):Boolean = {
    WebLogSession.sdf_standard.parse(elementA.split(" ")(2)).getTime < WebLogSession.sdf_standard.parse(elementB.split(" ")(2)).getTime
  }


  def getVisitsInfo(logInfoGroup: List[String]):String = {
    // 获取用户在该次session里面所访问页面的总数
    // 先用map函数将某次session里的所有访问记录编程(url,logInfo)元组形式，然后再用groupBy函数按url分组，最后统计共有几组
    val visitPageNum = logInfoGroup.map(log => (log.split(" ")(4),log)).groupBy(x => x._1).count(p => true)

    // 获取该次session的ID
    val sessionID = logInfoGroup(0).split(" ")(0)

    // 获取该次session的开始时间
    val startTime = logInfoGroup(0).split(" ")(2)

    // 获取该次session的结束时间
    val endTime = logInfoGroup(logInfoGroup.length - 1).split(" ")(2)

    // 获取该次session第一次访问url
    val entryPage= logInfoGroup(0).split(" ")(4)

    // 获取该次session最后一次访问的url
    val leavePage = logInfoGroup(logInfoGroup.length-1).split(" ")(4)

    // 获取该次session的用户IP
    val IP = logInfoGroup(0).split(" ")(1)

    // 获取该次session的用户从哪个网站过来
    val refereral = logInfoGroup(0).split(" ")(8)

    return sessionID + " " + startTime + " " + endTime + " " + entryPage + " " + leavePage + " " + visitPageNum + " " + IP + " " + refereral

  }

  def main(args: Array[String]) {
    val curDate = new Date()
    val logFile = ""
    val conf = new SparkConf().setAppName("VisitsInfo").setMaster("local")
    val sc = new SparkContext(conf)

    val logFileSource = sc.textFile(logFile,1).cache()

    // 将log信息变为(session,log信息)的tuple格式，也就是按照session将log分组
    val logLinesKVMapRDD = logFileSource.map(line => (line.split(" ")(0),line)).groupByKey()

    // 对每个(session[String],log信息[Iterator<String>])中的日志按照升序排序
    // 排完序后(session[String],log信息[Iterator<String>])的格式变为log信息[Iterator<String>]
    val sortedLogRDD = logLinesKVMapRDD.map(_._2.toList.sortWith((A,B)=>VisitsInfo.dateComparator(A,B)))

    // 统计每一个单独的Session的相关信息
    sortedLogRDD.map(VisitsInfo.getVisitsInfo(_)).saveAsTextFile("hdfs://114.80.18.42:/weblogclean/visits_log\" + WebLogSession.sdf_hdfsfolder.format(curDate)")



  }

}
