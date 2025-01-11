# Scala Future recover subtlety

This example demonstrates a potential pitfall when using `recover` with Scala Futures. While `recover` handles exceptions, it doesn't prevent the Future from completing successfully, leading to silent failures if not handled carefully.

The `bug.scala` file contains code that showcases this issue. The `bugSolution.scala` file provides a corrected version that uses `recoverWith` for proper error handling.