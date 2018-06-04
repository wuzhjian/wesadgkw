package hbase

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{Cell, HColumnDescriptor, TableName, HTableDescriptor}
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.filter.CompareFilter

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Admin on 2017/9/23.
  */
class HbaseUtils {

  var comp = new mutable.HashMap[String, CompareFilter.CompareOp]()
  comp.put("EQUAL", CompareFilter.CompareOp.EQUAL)
  comp.put("GRATER", CompareFilter.CompareOp.GREATER)
  comp.put("GREATER_OR_EQUAL", CompareFilter.CompareOp.GREATER_OR_EQUAL)
  comp.put("LESS", CompareFilter.CompareOp.LESS)
  comp.put("LESS_OR_EQUAL", CompareFilter.CompareOp.LESS_OR_EQUAL)
  comp.put("NO_OP", CompareFilter.CompareOp.NO_OP)
  comp.put("NOT_EQUAL", CompareFilter.CompareOp.NOT_EQUAL)


  def getAdmin(conf: Configuration): HBaseAdmin ={
    val connection = ConnectionFactory.createConnection(conf)
    connection.getAdmin.asInstanceOf[HBaseAdmin]
  }

  /**
    * 根据指定的管理员，表明，列族名创建表
    *
    * @param admin  创建的HBaseAdmin对象
    * @param tName  需要创建的表名
    * @param columnNames  列族名称的集合
    * @return
    */
  def createTable(admin:HBaseAdmin, tName:String, columnNames:List[String]): Boolean ={
    if (admin.tableExists(tName)) false

    val tableDesc = new HTableDescriptor(TableName.valueOf(tName))
    columnNames.foreach(columnName => tableDesc.addFamily(new HColumnDescriptor(columnName)))   //添加列
    admin.createTable(tableDesc)
    true
  }

  def insertData(conf: Configuration, tableName: String, rowKey: String, columnFamily: String, column: String, value: String ): Boolean = {
    val table = ConnectionFactory.createConnection(conf).getTable(TableName.valueOf(tableName))
    val put = new Put(Bytes.toBytes(rowKey))
    put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(column), Bytes.toBytes(value))
    table.put(put)
    true
  }

  def deleteTable(conf: Configuration, tableName: String): Boolean={
    val admin = getAdmin(conf)
    if (admin.tableExists(tableName)) {
      admin.disableTable(tableName)
      admin.deleteTable(tableName)
    }
    true
  }

  /**
    * 根据指定的配置信息全表扫描指定的表
    *
    * @param conf
    * @param tableName
    * @return
    */
  def getByScan(conf: Configuration, tableName: String): ArrayBuffer[Array[Cell]] ={
    var arrBuffer = ArrayBuffer[Array[Cell]]()
    val scanner = new Scan()
    val table = ConnectionFactory.createConnection(conf).getTable(TableName.valueOf(tableName))
    val results = table.getScanner(scanner)
    var res:Result = results.next()
    while (res != null) {
      arrBuffer += res.rawCells()
      res = results.next()
    }
    arrBuffer
  }

  def getRow(conf:Configuration, tableName: String, row: String): Array[Cell] ={
    val table = ConnectionFactory.createConnection(conf).getTable(TableName.valueOf(tableName))
    val get = new Get(Bytes.toBytes(row))
    val res = table.get(get)
    res.rawCells()
  }

  /**
    * 删除表指定row数据
    *
    * @param conf
    * @param tableName
    * @param row
    */
  def delRow(conf: Configuration, tableName: String, row: String): Unit={
    val table = ConnectionFactory.createConnection(conf).getTable(TableName.valueOf(tableName))
    table.delete(new Delete(Bytes.toBytes(row)))
  }

  /**
    * 更新某个指定的数据
    *
    * @param conf
    * @param tableName  要更新的表名
    * @param row  要更新的row
    * @param family  要更新的列族
    * @param qualifier  需要修改的列
    * @param compareon 只能为  EQUAL、GREATER、GREATER_OR_EQUAL、LESS、LESS_OR_EQUAL、NO_OP、NOT_EQUAL   不区分大小写
    * @param value  需要更改的值
    * @param newvalue
    * @return
    */
//  def updateByDelete(conf: Configuration, tableName: String, row: String, family: String,
//                     qualifier: String, compareon: String, value: String, newvalue: String):Boolean ={
//    if (comp.get(compareon.toUpperCase) == null) false
//    val table = ConnectionFactory.createConnection(conf).getTable(TableName.valueOf(tableName))
//    val mut = new RowMutations(Bytes.toBytes(row))
//    mut.add(new Delete(Bytes.toBytes(row)).addColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier)))
//    mut.add(new Put(Bytes.toBytes(row)).addColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier), Bytes.toBytes(newvalue)))
//    print("----------" + comp.get(compareon.toUpperCase()))
//
//    table.checkAndMutate(Bytes.toBytes(row),Bytes.toBytes(family),Bytes.toBytes(qualifier), comp.get(compareon.toUpperCase()),Bytes.toBytes(value),mut)
//  }

  def findByOldTime(conf: Configuration, tableName: String, row: String, cFamily: String, qualifier: String, timestamp: Long): Result = {
    val table = ConnectionFactory.createConnection(conf).getTable(TableName.valueOf(tableName))
    val get = new Get(Bytes.toBytes(row))
    get.setTimeStamp(timestamp)
    get.addColumn(Bytes.toBytes(cFamily), Bytes.toBytes(qualifier))
    table.get(get)
  }










}
