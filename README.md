# graphx-algorithm
Graph algorithms implemented in GraphX and Spark styles

# Import
0. Install necessary dependencies shown below
1. Open [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), click "Check out from Version Control", choose "GitHub" and paste
```
https://github.com/sjtu-iiot/graphx-algorithm
```
2. Import as an external model "Maven", click "Next", Chose "Import Maven projects automatically" (this step may not be needed)
3. Open "File - Project Structure", find "SDKs", add JDK path to it, e.g.
```
/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home
```
4. Find "Global Libraries" under "SDKs", add "Scala SDK". Click "Download", and choose "2.10.5"
5. Now you can use `mvn package` to compile the project

# Dependencies
1. [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) >= 14
2. [Scala](http://www.scala-lang.org/download/2.10.5.html) SDK == 2.10.5
3. Spark 1.3.0
4. GraphX 2.1.0
5. [Apache Maven](https://maven.apache.org/download.cgi) >= 3.3.1
6. [Java SE Development Kits](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

# Development Directory
The implementations of the algorithms are placed in package `org.apache.spark.graphx.iiot`. The other modified/added
files are listed as follows:

- org.apache.spark.graphx.GraphLoaderPlus

# Spark Project Example
[SimpleSparkApp](https://github.com/sryza/simplesparkapp)

# Spark Docs
[Apache Spark example with Java and Maven](http://www.robertomarchetto.com/spark_java_maven_example)

[Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

[How-to: Run a Simple Apache Spark App in CDH 5](http://blog.cloudera.com/blog/2014/04/how-to-run-a-simple-apache-spark-app-in-cdh-5/)

# Scala Docs
[How to use Scala Docs](https://wiki.scala-lang.org/display/SW/Introduction)

[Generating Documentation with scaladoc](https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch14s09.html#)

# Algorithms
[A question on Quora](http://www.quora.com/What-are-the-10-algorithms-one-must-know-in-order-to-solve-most-algorithm-challenges-puzzles)
