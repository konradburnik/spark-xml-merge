package com.burnikk

import org.apache.spark.sql.SparkSession

class CSVToXMLConverter(spark: SparkSession) {

  def convert(inputCSVPath: String, outputXMLPath: String, recordName: String = "record"): Unit = {
    import spark.implicits._

    val df = spark.read
      .option("header", "true")
      .csv(inputCSVPath)

    val schema = df.schema.fields.zipWithIndex
    val result = df.mapPartitions(partition => {
      partition.map(record => {
        val value = schema.map(field => {
          val fieldName = field._1.name
          val fieldValue = record(field._2)
          s"<$fieldName>$fieldValue</$fieldName>"
        }).mkString
        s"""<$recordName>
           |  $value
           |</$recordName>""".stripMargin
      })
    })

    result.write
      .mode("overwrite")
      .text(outputXMLPath)
  }
}