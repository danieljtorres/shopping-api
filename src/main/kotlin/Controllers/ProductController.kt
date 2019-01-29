package controllers

import services.*
import models.*
import io.javalin.*
import kotlinx.coroutines.runBlocking

object ProductController {
  fun getAll(ctx: Context) {
    runBlocking {
      val products = ProductService.getAll()
  
      val data: Data = when (products.isNotEmpty()) {
        true -> Data(products, 200, null, null)
        else -> Data(products, 204, null, "No se encontraron resultados")
      }
  
      ctx.json(data)
    }
  }
  
  fun get(ctx: Context) {
    runBlocking {
      val id: Int
      var data: Data
  
      try {
        id = ctx.validatedPathParam("id").asInt().getOrThrow()
        
        val product: TProduct? = ProductService.get(id)
        data = when (product != null) {
          true -> Data(product, 200, null, null)
          else -> Data(product, 204, null, "No se encontraron resultados")
        }
      } catch (error: Throwable) {
        data = Data(null, 400, "Parametro 'id' incorrecto", "El parametro no es un entero")
      }
  
      ctx.json(data)
    }
  }
  
  fun save(ctx: Context) {
    runBlocking {
      val product: TProduct
      var data: Data
      
      try {
        product = ctx.validatedBodyAsClass(TProduct::class.java).getOrThrow()
  
        val newProductId: Int? = ProductService.insert(product)
        data = when (newProductId != null) {
          true -> Data(newProductId, 200, null, null)
          else -> Data(newProductId, 204, null, "Hubo un problema al insertar el producto")
        }
      }catch (error: Throwable) {
        data = Data(null, 400, "Cuerpo incorrecto", "El cuerpo es de clase: ${TProduct::class.java.kotlin}")
      }
      ctx.json(data)
    }
  }
  
  fun update(ctx: Context) {
    ctx.result("Hola")
  }
  
  fun delete(ctx: Context) {
    ctx.result("Hola")
  }
}