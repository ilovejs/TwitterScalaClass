object collections2 {
	 def f(s: String) = "f(" + s + ")"        //> f: (s: String)String
	 
	 def g(s: String) = "g(" + s + ")"        //> g: (s: String)String
	 
	 val fComposeG = f _ compose g _          //> fComposeG  : String => String = <function1>
	 
	 fComposeG("yay")                         //> res0: String = f(g(yay))
	 
	 val fAndThenG = f _ andThen g _          //> fAndThenG  : String => String = <function1>
	 
	 fAndThenG("Yag")                         //> res1: String = g(f(Yag))
	 
	 //PartialFunction
	 val one: PartialFunction[Int, String] = { case 1 => "one" }
                                                  //> one  : PartialFunction[Int,String] = <function1>
	 //is a method on PartialFunction that can be used to determine if the PartialFunction will accept a given argument.
	 one.isDefinedAt(1)                       //> res2: Boolean = true
	 
	 one.isDefinedAt(2)                       //> res3: Boolean = false
	 
	 //try it
	 one(1)                                   //> res4: String = one
	 
	 val two: PartialFunction[Int, String] = { case 2 => "two" }
                                                  //> two  : PartialFunction[Int,String] = <function1>
	 
	 val three: PartialFunction[Int, String] = { case 3 => "three" }
                                                  //> three  : PartialFunction[Int,String] = <function1>
	 
	 val wildcard: PartialFunction[Int, String] = { case _ => "something else" }
                                                  //> wildcard  : PartialFunction[Int,String] = <function1>
	 
	 //make partial function
	 val partial = one orElse two orElse three orElse wildcard
                                                  //> partial  : PartialFunction[Int,String] = <function1>
	 
	 partial(5)                               //> res5: String = something else
	 partial(3)                               //> res6: String = three
	 partial(2)                               //> res7: String = two
	 partial(1)                               //> res8: String = one
	 partial(0)                               //> res9: String = something else
	 
	 //defined class PhoneExt
	 case class PhoneExt(name: String, ext: Int)
	 val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))
                                                  //> extensions  : List[collections2.PhoneExt] = List(PhoneExt(steve,100), Phone
                                                  //| Ext(robey,200))
                                                  
   //A PartialFunction is a subtype of Function so filter can also take a PartialFunction!
	 extensions.filter { case PhoneExt(name, extension) => extension < 200 }
                                                  //> res10: List[collections2.PhoneExt] = List(PhoneExt(steve,100))

	
}