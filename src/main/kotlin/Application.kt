import controllers.*
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import kotlinx.coroutines.*
import services.DatabaseFactory

fun main() = runBlocking<Unit> {
  runBlocking { DatabaseFactory.init() }

  val app = Javalin.create().apply {
    enableCorsForAllOrigins()
    enableStaticFiles("/public")
  }.start(7000)

  app.routes {
    path("/products") {
      get(ProductController::getAll)
      post(ProductController::save)
      path(":id") {
        get(ProductController::get)
        patch(ProductController::update)
        delete(ProductController::delete)
      }
    }
  }
}