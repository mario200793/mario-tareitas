package mx.cic


import org.apache.flink.api.scala._
import org.apache.flink.api.common.operators.Order


object Clase{
  def main(args: Array[String]) {
    val env = ExecutionEnvironment.getExecutionEnvironment //Batch
    val Archivo_csv = env.readCsvFile[(String,Int,Int)]("/home/aulae1/Escritorio/Animales.csv")
    //val arch = Archivo_csv.filter(n => n._2>200 && n._3>20)

    val agrupado = Archivo_csv.map(n => (n._1,n._2 * n._3))//.groupBy(0).sum(1) // Con _ entra en conflicto asi que creamos el alias n.
    //val arch = agrupado.setParallelism(1).sortPartition(1,Order.ASCENDING)
    val arch = agrupado.setParallelism(1).sortPartition(1,Order.DESCENDING)//.first(2).print() // me lo ordena dependiendo con cuantos map los tenga, es decir con cuantos nucleos le ponga al setparalelism, si le pongo tengo que ponerle un reduce.
    arch.print()
  }
}