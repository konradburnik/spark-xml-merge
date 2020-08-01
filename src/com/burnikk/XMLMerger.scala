package com.burnikk

import org.apache.spark.sql.SparkSession

object XMLMerger {
  // Takes multiple XML files as input and produces a single XML as output

  def merge(xmlFiles: Array[String], xmlOutputFile: String): Unit = {

    val spark: SparkSession = SparkSession.builder()
    .config("spark.master", "local[*]")
    .config("appName", "XMLMerger")
    .getOrCreate()

    // import spark.implicits._

    xmlFiles.foreach(file => {
        val df = spark.read
          .format("com.databricks.spark.xml")
          .option("rowTag", "record")
          .load(file)
        println(file)
        df.show()
      }
    )
  }

  def main(args: Array[String]): Unit = {
    merge(List("/Users/kburnik/Documents/github/SAF-T/test-records.xml").toArray, "out.xml")
  }

}
