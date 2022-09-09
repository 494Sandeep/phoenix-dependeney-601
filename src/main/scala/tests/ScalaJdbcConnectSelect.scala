package tests

object ScalaJdbcConnectSelect {
  def main(args: Array[String]): Unit = {
    import java.sql.{Connection,DriverManager}

    val url = args.head
    val driver = "org.apache.phoenix.jdbc.PhoenixDriver"
    val username = ""
    val password = ""
    var connection:Connection = null

    println(s"Phoenix URL: ${url}")
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs = statement.executeQuery("SELECT * FROM TM_ALERTS")
      while (rs.next) {
        val id = rs.getString("ALERT_ID")
        println(s"id: $id")
      }
    } catch {
      case e: Exception => e.printStackTrace
    }finally {
      connection.close
    }
    println(url)
  }
}