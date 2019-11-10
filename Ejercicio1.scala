package mx.cic

import org.apache.flink.api.scala._
import org.apache.flink.api.common.operators.Order

object Ejercicio1{
  def main(args: Array[String]) {
    val env = ExecutionEnvironment.getExecutionEnvironment //Batch
    val Archivo_csv = env.readCsvFile[(Double,Double,String)]("/home/aulae1/Escritorio/ParaKNN.csv")
    val k = 3
    val p1 = 3
    val p2 = 7
    val distancias = Archivo_csv.map( n =>(n._1,n._2,n._3, (n._1 - p1)*(n._1 - p1 )+(n._2 - p2)*(n._2 - p2) , 1) )
    val new_distancias = distancias.setParallelism(1).sortPartition(3,Order.ASCENDING)//.print()
    val ultimo = new_distancias.first(k).groupBy(2).sum(4).setParallelism(1).sortPartition(4,Order.DESCENDING).first(1).collect()
    println("Es: " + ultimo(0)._3 + "por una cantidad : " + ultimo(0)._5 +  " de :" +  k)
  }
}
