import java.sql.DriverManager

/**
  * Created by Admin on 2017/8/20.
  */
object SparkSQL4JDBC {
  def main(args: Array[String]) {
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    val conn = DriverManager.getConnection("jdbc:hive2://slave1:1000/","spark", "spark")
    try {
      val statement = conn.createStatement()
      statement.executeQuery("use hive")
      val rs = statement.executeQuery("select ordernumber,amount from xxx where amount > 3000")

      while (rs.next()) {
        val ordernumber = rs.getString("ordernumber")
        val amount = rs.getString("amount")
        println("ordernumber = %s, amount = %s".format(ordernumber, amount))
      }
    } catch {
      case e:Exception => e.printStackTrace()
    }
    conn.close()
  }
}
