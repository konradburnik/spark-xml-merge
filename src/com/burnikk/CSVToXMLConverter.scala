package com.burnikk

import org.apache.spark.sql.SparkSession

class CSVToXMLConverter(spark: SparkSession) {

  def convert(inputCSVPath: String, outputXMLPath: String,
              tableName: String, recordName: String): Unit = {
    import spark.implicits._

    val df = spark.read
      .option("header", "true")
      .csv(inputCSVPath)

    val schema = df.schema.fields.zipWithIndex
    val header = Seq(s"<$tableName>").toDS()
    val footer = Seq(s"</$tableName>").toDS()
    val body = df.mapPartitions(partition => {
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
    val result = header.union(body.union(footer))

    result.write
      .mode("overwrite")
      .text(outputXMLPath)
  }
}