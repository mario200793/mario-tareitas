package org.myorg.quickstart

import org.apache.flink.api.scala._
import org.apache.flink.table.api._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.sources.{CsvTableSource,TableSource}

object Clase2{
  case class WC ( frecuency:Int, number:Int, word:String)
  def main(args: Array[String]): Unit ={
    val env = ExecutionEnvironment.getExecutionEnvironment
    val tEnv = BatchTableEnvironment.create(env)
    val input = env.readCsvFile[(WC)](filePath = "/home/aulae1/Escritorio/ParaKNN.csv" )
    val expr = input.toTable(tEnv)
    //expr.collect()
    val result= expr.groupBy('word).select('word, 'frecuency.count / 'number.sum as 'count, 'number.max as 'maximo).filter('word === "Good").toDataSet[(String,Long,Int)].print()
  }
}
