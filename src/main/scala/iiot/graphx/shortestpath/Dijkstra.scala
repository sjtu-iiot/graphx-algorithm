package iiot.graphx.shortestpath

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

/**
 * The Dijkstra object contains the implementation of Dijkstra algorithm. It returns
 * the shortest distances from the source vertex, and the information of the paths.
 *
 * Created by Hangchen Yu on 3/30/2015.
 */
object Dijkstra {

  /**
   * Implement Dijkstra algorithm to calculate the shortest distance and path from the source
   * vertex.
   *
   * @param args contains three parameters `inputFileName sourceId [outputFileDirectory]`.
   *             `outputFileDirectory` is optional. The input file should contain three integer
   *             columns `<x, y, d>` which means the distance from `x` to `y` is `d`.
   * @return output formatted result of the algorithm which contains the shortest distances from
   *         the source and each node's previous vertex in the shortest paths.
   */
  def main(args: Array[String]) {

    /**
    * @param inputFile the filename of the input file which contains three integer columns `<x, y, d>`.
    *                  It means the distance from `x` to `y` is `d`.
    * @param outputFileDir the directory where the output would be placed.
    * @param sourceId the source vertex id of the graph.
    * @param sssp contains the result of the algorithm. Each vertex contains a two-element array as
    *             attribute. The first element is the distance from the source, while the second
    *             is its previous vertex in the shortest path.
    * @param format_sssp the formatted result of `sssp` which would also be stored in
    *                    `outputFileDirectory` if the parameter is provided.
    */
    if (args.length < 2) sys.error("Usage: inputFileName sourceId [outputFileDirectory]")

    val inputFile = args(0)
    val sourceId: VertexId = args(1).toInt

    val sc = new SparkContext(new SparkConf().setAppName("Dijkstra Algorithm"))

    val graph = GraphLoader.edgeListFile(sc, inputFile)

    val g = graph.mapVertices((id, _) =>
      if (id == sourceId) Array(0.0, id)
      else Array(Double.PositiveInfinity, id)
    ).mapEdges(e => (new scala.util.Random).nextInt(100))

    val sssp = g.pregel(Array(Double.PositiveInfinity, -1))(
      (id, dist, newDist) => {
        if (dist(0) < newDist(0)) dist
        else newDist
      },
      triplet => {
        if (triplet.srcAttr(0) + triplet.attr < triplet.dstAttr(0)) {
          Iterator((triplet.dstId, Array(triplet.srcAttr(0) + triplet.attr, triplet.srcId)))
        }
        else {
          Iterator.empty
        }
      },
      (a, b) => {
        if (a(0) < b(0)) a
        else b
      }
    )

    val format_sssp: RDD[String] = sssp.vertices.map(vertex =>
      "Vertex " + vertex._1 + ": distance is " + vertex._2(0) + ", previous node is Vertex " + vertex._2(1).toInt)
    format_sssp.collect.foreach(println(_))

    if (args.length > 2) {
      val outputFileDir = args(2)
      format_sssp.saveAsTextFile(outputFileDir)
    }
  }
}
