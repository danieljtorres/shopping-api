package services

import models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
  
  private val db by lazy {
    Database.connect(
      url = "jdbc:sqlserver://DESKTOP-5R4MB7K;databaseName=ShoppingHistory", user = "danieljtorres", password = "1503",
      driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
    )
  }
  suspend fun <T> query(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction {
      addLogger(StdOutSqlLogger)
      block()
    }
  }
  
  
  suspend fun init() {
    this.db
    this.query {
      SchemaUtils.create(
        Products/*,
        PurchaseModel,
        PurchaseItemModel*/
      )
    }
  }
}