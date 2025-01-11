```scala
import scala.concurrent.{ExecutionContext, Future}

class MyClass(implicit ec: ExecutionContext) {
  def myMethod(x: Int): Future[Int] = Future {
    if (x == 0) throw new IllegalArgumentException("x cannot be 0")
    x * 2
  }
}

object Main extends App {
  implicit val ec = scala.concurrent.ExecutionContext.global

  val myClass = new MyClass()
  
  // Using recoverWith to properly handle and signal failure.
  val futureResult = myClass.myMethod(0).recoverWith { 
    case e: IllegalArgumentException => Future.failed(e) 
  }

  futureResult.onComplete { 
    case scala.util.Success(value) => println(s"Success: $value") 
    case scala.util.Failure(exception) => println(s"Failure: ${exception.getMessage}") 
  }

  Thread.sleep(1000)
}
```