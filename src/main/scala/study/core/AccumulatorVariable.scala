package study.core

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2018/4/22.
  */
object AccumulatorVariable {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setAppName("AccumulatorVariable")
      .setMaster("local")
    val sc = new SparkContext(conf)

    val sum = sc.accumulator(0)

    val numArray = Array(1,2,3,4,5)

    val numbers = sc.parallelize(numArray,1)

    numbers.foreach{ num => sum += num}

    println(sum)

  }
}
