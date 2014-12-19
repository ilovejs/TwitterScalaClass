object collections {
   val numbers = List(1, 2, 3, 4)                 //> numbers  : List[Int] = List(1, 2, 3, 4)
	 var sets    = Set(1, 1, 2)               //> sets  : scala.collection.immutable.Set[Int] = Set(1, 2)
	 var tuples  = ("location", 13)           //> tuples  : (String, Int) = (location,13)
	 
	 tuples._1                                //> res0: String = location
	 /*
	 tuples match {
	 	case ("location", port) => ...
	 	case (host, port) => ...
	 }
	 */
	 
	 //special operator
	 1 -> 2                                   //> res1: (Int, Int) = (1,2)
	 
	 Map(1 -> 2)                              //> res2: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2)
	 
	 Map(1 -> Map("foo" -> "bar"))            //> res3: scala.collection.immutable.Map[Int,scala.collection.immutable.Map[Stri
                                                  //| ng,String]] = Map(1 -> Map(foo -> bar))
   //Map to function
	 /*
	 Map("timesTwo" -> { timesTwo() })
	 */
	 
	 /*
	  Option's interface is like:
	  Option itself is generic，it has 2 subclass： Some[T] or None
	 trait Option[T] {
	  def isDefined: Boolean
	  def get: T
	  def getOrElse(t: T): T
	}
	
	val nums = Map(1 -> "one", 2 -> "two")
 	nums.get(2)
 	//return None
  nums.get(9)
  
  // We want to multiply the number by two, otherwise return 0.
	val result = if (res1.isDefined) {
	  res1.get * 2
	} else {
	  0
	}
	//even simpler
	var result = res1.getOrElse(0) * 2
	
	//
	var result1 = res1 match {
		case Some(n) => n * 2
		case None    => 0
	}
	*/
	
	//Functional Combinators
	//List(1,2,3) map squared
	
	val nums2 = List(1,2,3)                   //> nums2  : List[Int] = List(1, 2, 3)
	nums2.map((i:Int) => i * 2)               //> res4: List[Int] = List(2, 4, 6)
	
	def timesTwo(i: Int): Int = i * 2         //> timesTwo: (i: Int)Int
	//partially evaluated function
	nums2.map(timesTwo _)                     //> res5: List[Int] = List(2, 4, 6)

  //returns nothing
	nums2.foreach((i: Int) => i * 2)
	
	//return of type Unit (i.e. void)
	val doubled = nums2.foreach((i: Int) => i * 2)
                                                  //> doubled  : Unit = ()
	//filter
	nums2.filter((i: Int) => i % 2 == 0)      //> res6: List[Int] = List(2)
	
	def isEven(i: Int): Boolean = i % 2 == 0  //> isEven: (i: Int)Boolean
	
	nums2.filter(isEven _)                    //> res7: List[Int] = List(2)
	
	//zip
	List(1, 2, 3).zip(List("a", "b", "c"))    //> res8: List[(Int, String)] = List((1,a), (2,b), (3,c))
	
	//partition
	val nums3 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                                  //> nums3  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  //(List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))
	nums3.partition(_ % 2 == 0)               //> res9: (List[Int], List[Int]) = (List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))

  //find first elem match predicate
	nums3.find((i: Int) => i > 5)             //> res10: Option[Int] = Some(6)

  //drop the first nth elems
  //=> List(6, 7, 8, 9, 10)
	nums3.drop(5)                             //> res11: List[Int] = List(6, 7, 8, 9, 10)
	//drop the first odd number
	nums3.dropWhile(_ % 2 != 0)               //> res12: List[Int] = List(2, 3, 4, 5, 6, 7, 8, 9, 10)
	
	//0 is the starting value (Remember that numbers is a List[Int]), and m acts as an accumulator.
	numbers.foldLeft(0)((m: Int, n: Int) => m + n)
                                                  //> res13: Int = 10
	
	//accumulate from left
	numbers.foldLeft(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
                                                  //> m: 0 n: 1
                                                  //| m: 1 n: 2
                                                  //| m: 3 n: 3
                                                  //| m: 6 n: 4
                                                  //| res14: Int = 10
  //accumulate from right
	numbers.foldRight(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
                                                  //> m: 4 n: 0
                                                  //| m: 3 n: 4
                                                  //| m: 2 n: 7
                                                  //| m: 1 n: 9
                                                  //| res15: Int = 10
	List(List(1, 2), List(3, 4)).flatten      //> res16: List[Int] = List(1, 2, 3, 4)
	 
	//flatMap is a frequently used combinator that combines mapping and flattening.
	//flatMap takes a function that works on the nested lists and then concatenates the results back together.
	val nestedNumbers = List(List(1, 2), List(3, 4))
                                                  //> nestedNumbers  : List[List[Int]] = List(List(1, 2), List(3, 4))
	nestedNumbers.flatMap(x => x.map(_ * 2))  //> res17: List[Int] = List(2, 4, 6, 8)
	
	//Think of it as short-hand for mapping and then flattening:
	nestedNumbers.map((x: List[Int]) => x.map(_ * 2))
	             .flatten                     //> res18: List[Int] = List(2, 4, 6, 8)

	
  val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
                                                  //> extensions  : scala.collection.immutable.Map[String,Int] = Map(steve -> 100
                                                  //| , bob -> 101, joe -> 201)
  
  extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200)
                                                  //> res19: scala.collection.immutable.Map[String,Int] = Map(steve -> 100, bob -
                                                  //| > 101)
  
  extensions.filter({case (name, extension) => extension < 200})
                                                  //> res20: scala.collection.immutable.Map[String,Int] = Map(steve -> 100, bob -
                                                  //| > 101)
  
}