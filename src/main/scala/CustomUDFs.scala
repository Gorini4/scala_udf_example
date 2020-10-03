import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

object CustomUDFs {

  def ipToInt(ipString: String): Long = {
    // 192.168.0.1 > 23458723
    val ipGroups = ipString
      .split("\\.")
      .map(x => x.toLong)

    assert(ipGroups.length == 4)

    (ipGroups(0) << 24) + (ipGroups(1) << 16) + (ipGroups(2) << 8) + ipGroups(3)
  }

  val ipToIntUDF: UserDefinedFunction = udf(ipToInt _)

}
