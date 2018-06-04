package hbase

import org.apache.hadoop.hbase.HBaseConfiguration

/**
  * Created by Admin on 2017/9/24.
  */
object test {
  def main(args: Array[String]) {
    val conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum","master")
    conf.set("hbase.zookeeper.property.clientPort","2181")
    val hBaseUtils = new HbaseUtils()
    val admin = hBaseUtils.getAdmin(conf)

    val list = List("family1","family2")
    hBaseUtils.createTable(admin, "test2",list)

    println(hBaseUtils.insertData(conf,"test2","rowkey1","family1","李四","lisi2"))
    val row = hBaseUtils.getRow(conf,"test2","rowkey1")
    row.foreach(a => {
      print(new String(a.getRow)+ " ")
      print(a.getTimestamp + " ")
      print(new String(a.getFamily) + " ")
      print(new String(a.getValue) + " ")
    })

  print(hBaseUtils.delRow(conf, "test2", "rowkey1"))


  }
}
