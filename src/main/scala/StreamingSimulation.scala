/**
  * Created by Admin on 2017/8/10.
  */

import java.io.PrintWriter
import java.net.ServerSocket

import scala.io.Source

/**
  * Created by Administrator on 2017/8/10.
  */
object StreamingSimulation {
  /**
    * 定义随机获取整数的方法
    *
    * @param length
    * @return
    */
  def index(length: Int) = {
    import java.util.Random
    val rdm = new Random
    rdm.nextInt(length)
  }

  def main(args: Array[String]) {
    //调用该模拟器需要3个参数，分别数文件路径，端口号和时间间隔（单位ms）
    if (args.length < 3){
      System.err.println("Usage: <filename><port><millisecond>")
      System.exit(1)
    }

    // 获取指定文件总的行数
    val filename = args(0)
    val lines = Source.fromFile(filename).getLines().toList
    val filerow = lines.length

    //指定监听端口，当外部程序请求时建立连接
    val listener = new ServerSocket(args(1).toInt)
    while (true){
      val socket = listener.accept()
      new Thread(){
        override def run() = {
          println("Got client connected from:" + socket.getInetAddress)
          val out =new PrintWriter(socket.getOutputStream(), true)

          while (true) {
            Thread.sleep(args(2).toInt)
            //当该端口接收请求时，随机获取某行数据发送给对方
            val content = lines(index(filerow))
            println(content)
            out.write(content + '\n')
            out.flush()
          }
          socket.close()
        }
      }.start()
    }
  }

}
