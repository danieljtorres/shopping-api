package models

import org.jetbrains.exposed.sql.*

object Products: Table() {
  val id = integer("id").autoIncrement().primaryKey()
  val name = varchar("name", 50)
  val image = varchar("image", 100)
  val measure = varchar("measure", 4)
}

data class TProduct(
  val id: Int?,
  val name: String,
  val image: String,
  val measure: String
) {
  constructor(rs: ResultRow): this(
    rs[Products.id],
    rs[Products.name],
    rs[Products.image],
    /*Measure.valueOf(*/rs[Products.measure]/*).getText()*/
  )
  
  enum class Measure(private val text: String) {
    Unit("Unidad"),
    Kgs("Kilos"),
    Lts("Litros");
    
    fun getText() = this.text
  }
}