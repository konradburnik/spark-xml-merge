# spark-csv-to-xml-converter

Convert a given CSV file to XML format using Apache Spark.

## Usage

### Calling as a JAR directly
```
java -jar spark-csv-to-xml-assembly-0.1.jar input.csv output.xml
```

### Invoke the library in code

```scala
import com.burnikk.CSVToXML

object Converter {
    def main(args: Array[String]): Unit = {
      CSVToXML.main(args)
    }
  }
```
