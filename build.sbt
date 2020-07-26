name := "spark-csv-to-xml"

version := "0.1"

scalaVersion := "2.12.11"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.6" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.6" % "provided"

mainClass in assembly := Some("com.burnikk.CSVToXML")

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*)  => MergeStrategy.first
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case PathList("org","xmlpull", xs @ _*) => MergeStrategy.last
  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case _ => MergeStrategy.first
}