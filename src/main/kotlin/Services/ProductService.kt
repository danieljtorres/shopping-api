package services

import models.*
import org.jetbrains.exposed.sql.*

object ProductService {
  private val db = DatabaseFactory
  
  suspend fun getAll(): List<TProduct> {
    return db.query {
      val query = Products.selectAll()
      query.map { TProduct(it) }
    }
  }
  
  suspend fun get(id: Int): TProduct? {
    return db.query {
      val query = Products.select{ Products.id eq id }.map { TProduct(it) }
      when(query.isNotEmpty()) {
        true -> query.first()
        else -> null
      }
    }
  }
  
  suspend fun insert(product: TProduct): Int? {
    return db.query {
      val query = Products.insert{
        it[Products.name] = product.name
        it[Products.image] = product.image
        it[Products.measure] = product.measure
      } get Products.id
      query
    }
  }
}