package com.burnikk

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import org.apache.spark.sql.SparkSession

class CSVToXMLConverter(spark: SparkSession) {

  def convert(inputCSVPath: String, outputXMLPath: String): Unit = {
    import spark.implicits._

    val df = spark.read
                  .option("header", "true")
                  .csv(inputCSVPath)

    val result = df.mapPartitions(partition => {
      val xstream = new XStream(new DomDriver())
      val data = partition.map(record => {
        val xmlString = xstream.toXML(record)
        xmlString
      })
      data
    })

    result.write
          .mode("overwrite")
          .text(outputXMLPath)
  }
}