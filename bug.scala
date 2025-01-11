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

  // This will successfully handle the exception, but not recover from it.
  val futureResult = myClass.myMethod(0).recover { case e: IllegalArgumentException => 0 }

  futureResult.onComplete { case result => println(result) }

  Thread.sleep(1000)
}
```