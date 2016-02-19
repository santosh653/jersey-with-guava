Building Asynchronous RESTful Services With Jersey
=================================================
How to run this project

01) Requirements JDK 1.8.x.x, Maven 3.0

02) Run maven command: "mvn clean package compile exec:java -DskipTests" 

03) Use a REST client (Poster, Postman etc..) and first add book objects to program
    Example 
    {
      "title":"test tile",
      "author":"test author"
    }
    
    Use this url "http://localhost:9080/books" with POST request.

Basic Coverages

* Jersey with google guave ListenableFuture support for Asynchronous webservices
* Jersey unit testing

