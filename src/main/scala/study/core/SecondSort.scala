package study.core

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2018/4/22.
  */
object  SecondSort {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setAppName("SecondSort")
      .setMaster("local")
    val sc = new SparkContext(conf)


    val lines = sc.textFile("F:\\教程\\Spark 2.0从入门到精通-278讲\\000.课程代码+软件包\\第39讲-Spark核心编程：高级编程之二次排序\\文档\\sort.txt")

    val pairs = lines.map{line => (
      new SecondSortKey(line.split(" ")(0).toInt, line.split(" ")(1).toInt), line
      )}

    val sortedPairs = pairs.sortByKey()

    val sortedLines = sortedPairs.map(sortedPair => sortedPair._2)
    sortedLines.foreach{
      sortedLine => println(sortedLine)
    }
  }
}
