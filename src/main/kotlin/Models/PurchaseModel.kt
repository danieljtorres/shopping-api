package models

import org.jetbrains.exposed.sql.*

object PurchaseModel: Table() {
  val id = integer("id").autoIncrement().primaryKey()
  val date = datetime("date")
}

data class TPurchase(
  val id: Int?,
  val date: String
)