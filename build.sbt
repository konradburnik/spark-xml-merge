name := "spark-xml-merge"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.6"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.6"
libraryDependencies += "com.databricks" %% "spark-xml" % "0.5.0"

mainClass in assembly := Some("com.burnikk.XMLMerger")

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