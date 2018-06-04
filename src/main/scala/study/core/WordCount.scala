package study.core

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2018/4/22.
  */
object WordCount {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")

    val sc = new SparkContext(conf)

    val lines = sc.textFile("E:\\Workspace\\wordcount.txt")

    val words = lines.flatMap{line => line.split(" ")}

    val pairs = words.map(word => (word, 1))
    val  wordCounts = pairs.reduceByKey(_+_)

    wordCounts.foreach(wordCount => println(wordCount._1 + " appeared"  + wordCount._2 + " times."))
  }
}
