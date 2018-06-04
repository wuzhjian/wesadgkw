package sparkML

import breeze.linalg.{diag, DenseVector, DenseMatrix}
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Admin on 2017/10/2.
  */
object ml {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("adgi").setMaster("local")
    val sc = new SparkContext(conf)

    val m1 = DenseMatrix.zeros[Double](2,3)
    println(m1)
    /**
      * 0.0  0.0  0.0
      * 0.0  0.0  0.0
      */

    val v1 = DenseVector.zeros[Double](3)
    println(v1)  // DenseVector(0.0, 0.0, 0.0)

    val v2 = DenseVector.ones[Double](3)
    println(v2)  // DenseVector(1.0, 1.0, 1.0)

    val v3 = DenseVector.fill(3){5.0}
    println(v3)  // DenseVector(5.0, 5.0, 5.0)

    val v4 = DenseVector.range(1, 10, 2)
    println(v4) // DenseVector(1, 3, 5, 7, 9)

    val m2 = DenseMatrix.eye[Double](3)  // 单位阵
    println(m2)
    /**
      * 1.0  0.0  0.0
      * 0.0  1.0  0.0
      * 0.0  0.0  1.0
      */

    val v6 = diag(DenseVector(1.0, 2.0, 3.0))  // 对角矩阵
    println(v6)
    /**
      * 1.0  0.0  0.0
      * 0.0  2.0  0.0
      * 0.0  0.0  3.0
      */

    val v8 = DenseVector(1,2,3,4)
    println(v8)  // DenseVector(1, 2, 3, 4)


    val v9 = DenseVector(1,2,3,4).t   // 矩阵转置
    println(v9)  //Transpose(DenseVector(1, 2, 3, 4))

    val v10 = DenseVector.tabulate(3){i=> 2*i}  // 通过索引创建向量。索引从0开始
    println(v10)  // DenseVector(0, 2, 4)

    val m4 = DenseMatrix.tabulate(3,2){case (i,j) => i+j}  // i代表行号，j代表列号，创建3行2列的矩阵，矩阵元素为行号与列号的和。行号和列号从0开始
    println(m4)
    /**
      * 0  1
      * 1  2
      * 2  3
      */

    val v11 = new DenseVector(Array(1,2,3,4))  // 通过数组创建向量
    println(v11)  // DenseVector(1, 2, 3, 4)

    val m5 = new DenseMatrix(2,3, Array(11,12,12,21,22,23))
    println(m5)
    /**
      * 11  12  22
      * 12  21  23
      */

    val v12 = DenseVector.rand(4)
    println(v12)  // DenseVector(0.4230636282395239, 0.3325677500304953, 0.5379817242935592, 0.5277379173747565)

    val m6 = DenseMatrix.rand(2,3)
    println(m6)
    /**
      * 0.9432497237995368  0.1376842182610507  0.9438199693505676
      * 0.6606657744637179  0.835798539755324   0.4713065617014678
      */
  }
}
