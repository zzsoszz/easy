package com.sillycat.easysprayrestserver.model

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import spray.json.DefaultJsonProtocol
import spray.json.pimpAny
import spray.json.pimpString

class JSONMarshallSpec extends FlatSpec with ShouldMatchers with DefaultJsonProtocol {

  import com.sillycat.easysprayrestserver.model.ProductJsonProtocol._
  import com.sillycat.easysprayrestserver.model.CartJsonProtocol._
  
  "Marshalling User without ID JSON" should "result in an User Object without id" in {
    val jsonUser = """{
    		"userName":"Carl",
    		"age": 31, 
    		"userType": "ADMIN", 
    		"createDate": "2012-05-21 12:34", 
    		"expirationDate": "2013-05-12 12:34",
    		"password": "111111"
      }"""
    implicit val formatter = (new UserJsonProtocol(1)).UserJsonFormat
    val userAST = jsonUser.asJson

    info("UserAST: " + userAST.prettyPrint)
    val user: User = userAST.convertTo[User]
    info("User Object: " + user.toJson)
    assert(user.age === 31)
    assert(user.id === None)
  }
  
  "Marshalling Cart JSON" should "result in an Cart Object" in {
    val jsonCart = """{
    		"id" : 2,
    		"cartName" : "HomeCart",
    		"cartType" : "DIRECT",
    		"user" : {
    			"id" : 3, 
    			"userName" : "Carl",
    			"age": 31, 
    			"userType": "ADMIN", 
    			"createDate": "2012-05-21 12:34", 
    			"expirationDate": "2013-05-12 12:34",
    			"password": "111111"
    		},
    		"products" : [
    			{
    				"id" : 1,
    				"productName" : "iphone 5",
    				"productDesn" : "A good mobile device.",
    				"createDate" : "2012-05-22 13:33",
    				"expirationDate" : "2012-05-22 14:33",
    				"productCode" : "IPHONE5"
    			},
    			{
    				"id" : 2,
    				"productName" : "iphone 4s",
    				"productDesn" : "A good mobile device.",
    				"createDate" : "2012-05-22 13:33",
    				"expirationDate" : "2012-05-22 14:33",
    				"productCode" : "IPHONE4S"
    			}
            ]
      }"""
    val cartAST = jsonCart.asJson
    
    info("CartAST: " + cartAST.asJsObject)
    val cart: Cart = cartAST.convertTo[Cart]
    info("Cart Object: " + cart.toJson)
    assert(cart.toJson === cartAST)
  }
  
  "Marshalling Product JSON" should "result in an Product Object" in {
    val jsonProduct = """{
    			"id" : 1,
    			"productName" : "iphone 5",
    			"productDesn" : "A good mobile device.",
    			"createDate" : "2012-05-22 13:33",
    			"expirationDate" : "2012-05-22 14:33",
    			"productCode" : "IPHONE5"
    		}"""
    val productAST = jsonProduct.asJson
    
    info("ProductAST: " + productAST.asJsObject)
    val product: Product = productAST.convertTo[Product]
    info("Product Object: " + product.toJson)
    assert(product.toJson === productAST)
  }

  "Marshalling User JSON" should "result in an User Object" in {
    val jsonUser = """{
    		"id": 1 ,
    		"userName":"Carl",
    		"age": 31, 
    		"userType": "ADMIN", 
    		"createDate": "2012-05-21 12:34", 
    		"expirationDate": "2013-05-12 12:34",
    		"password": "111111"
      }"""
    implicit val formatter = (new UserJsonProtocol(1)).UserJsonFormat
    val userAST = jsonUser.asJson

    info("UserAST: " + userAST.prettyPrint)
    val user: User = userAST.convertTo[User]
    info("User Object: " + user.toJson)
    assert(user.toJson === userAST)
  }
  


}