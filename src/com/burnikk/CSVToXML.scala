package com.burnikk

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object CSVToXML {
  def main(args: Array[String]): Unit = {

    if (args.size < 2) {
      val appName = getClass.getSimpleName.split('$').head
      val appDesc = "Convert CSV to XML"
      val appParams = "<csv-path> <xml-path> [table-name] [record-name]"
      println(s"\n$appName - $appDesc\n\nUsage:\n\t$appName $appParams\n")
    } else {
      val spark: SparkSession = SparkSession.builder()
        .config("spark.master", "local[*]")
        .config("appName", "CSVToXMLConverter")
        .getOrCreate()

      val rootLogger = Logger.getRootLogger()
      rootLogger.setLevel(Level.ERROR)

      new CSVToXMLConverter(spark).convert(
        inputCSVPath = args(0),
        outputXMLPath = args(1),
        tableName = if (args isDefinedAt 2) args(2) else "table",
        recordName = if (args isDefinedAt 3) args(3) else "record"
      )
    }
  }
}
