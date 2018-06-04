package study.core

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2018/4/22.
  */
object HDFSFile {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setAppName("HDFSFile")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val lines = sc.textFile("E:\\Workspace\\wordcount.txt")

    val count = lines.map{ line => line.length()}.reduce(_+_)

    println("file's count is " + count)

  }
}
