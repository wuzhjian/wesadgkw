package study.core

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2018/4/22.
  */
object GroupTopn {
  def main(args: Array[String]) {
    val conf=new SparkConf().setAppName("GroupTopn").setMaster("local")
    val sc=new SparkContext(conf)


    //读取HDFS中的数据
    val lines=sc.textFile("F:\\教程\\Spark 2.0从入门到精通-278讲\\000.课程代码+软件包\\第40讲-Spark核心编程：高级编程之topn\\文档\\score.txt")

    lines.map( m=> {
      val  info = m.split(" ")

      (info(0), info(1).toInt)
    }).groupByKey().map(m => {
      val className = m._1
      val top3 = m._2.toArray.sortWith(_>_).take(3)
      (className,top3)
    }).foreach(item => {
      val className = item._1

      println("班级："+className+"的前3名是：")

      item._2.foreach(m => {
        println(m)
      })
    })


  }
}
