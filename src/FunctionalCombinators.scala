//Work sheet doesn't evaluate ourMap()

object FunctionalCombinators extends App {
  val numbers = List(1,2,3,4);
  
  def timesTwo(i: Int) = i * 2;
  
  //What we’d like is to be able to write our own functional combinators.
  //:: is prepend elements, ::: is prepend a list
  def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
    numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) =>
      fn(x) :: xs
    }
  }

  val nums4 = ourMap(numbers, timesTwo(_))
  
  println(nums4)   //List(2, 4, 6, 8)
  println(numbers) //List(1, 2, 3, 4)
  
  
}