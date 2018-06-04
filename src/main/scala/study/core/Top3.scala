package study.core

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2018/4/22.
  */
object Top3 {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setAppName("Top3")
      .setMaster("local")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("F:\\教程\\Spark 2.0从入门到精通-278讲\\000.课程代码+软件包\\第40讲-Spark核心编程：高级编程之topn\\文档\\top.txt", 1)

    val pairs = lines.map{ line => (line.toInt, line)}

    val sortedPairs = pairs.sortByKey(false)

    val sortedNumber = sortedPairs.map(sortedPair => sortedPair._1)

    val top3Number = sortedNumber.take(3)

    for (num <- top3Number) {
      println(num)
    }


  }
}
