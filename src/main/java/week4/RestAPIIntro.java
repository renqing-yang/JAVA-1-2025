package week4;


/**
 *      loadbalancer(sticky session)
 *        /   \   \
 *      s1    s2   s3
 *
 *
 * Rest API
 * 1. http
 * 2. get post delete put patch
 * 3. endpoint
 *    /student?a=x&b=x    get
 *    /student/{id} get
 *    /student    post
 *    /student/{id}  put
 *    /student/{id} delete
 * 4. stateless
 * 5. json / xml
 * 6. Header (Accept, Content-Type)
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  endpoint : /student
 *  http method: get
 *  request param: age / name /.. pagination
 *  request body: none
 *  response body:
 *      {
 *          data: [{}, {}, {}]
 *      }
 *  status code: 200, 400,
 * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  /student/{id} get
 *  response body:
 *      {
 *          data: {stu..}
 *      }
 *  status code: 200, 404
 * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  /student    post
 *  request body(do not contain id)
 *      {
 *          ..
 *      }
 *  response body
 *      {
 *
 *      }
 *   status: 201
 * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  /student/{id}  put
 *
 *  status: 204
 * * * * * * * * * * * * * * * * * * * * * * * * * *
 * /student/{id} delete
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * *
 * tomcat server
 *
 *      while(true) {
 *          socket build connection -> Thread pool -> thread(connection) -> (SpringMVC)dispatcher servlet(/*) -> handler mapping -> Controller
 *                                                                                          ||
 *                                                                              http message converter
 *                                                                                          |
 *                                                                                       jackson
 *      }
 *
 *
 *  private ip + port - NAT - public ip + port
 * * * * * * * * * * * * * * * * * * * * * * * * * *
 * How do you design rest api
 * 1. clarify requirements
 * 2. provide rest api design
 * 3. TDD (Test Driven Development)
 *      a. come up test cases
 *      b. do OOD design (skeleton) put TODO in code
 *      c. write test (unit test / integration test) with junit / mockmvc/ mockito
 *      d. impl TODO
 *      e. execute junit / test cases
 *      ..
 * 4. code review (pull request)
 * 5. consider security of your rest api
 * 6. documentation
 * 7. performance
 *       a. data structure / algorithm performance
 *       b. jvm gc
 *       c. database performance / cache
 *       d. vertical scaling / horizontal scaling
 *       e. ...
 * 8. fault tolerance
 * ...
 *
 *   * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Spring Boot[Spring + other features]
 *
 *  1. Spring IOC AOP
 *
 *  IOC
 *      Spring Bean: objects / classes managed by Spring
 *      Dependency Injection (impl of IOC in Spring) :
 *          1. @Autowired
 *          2. @Component / @Service / @Controller / @Repository / @Bean
 *              bean scope
 *                  a. singleton
 *                  b. prototype
 *                  c. request
 *                  d. session
 *                  e. global session
 *
 *
 *
 *          prototype vs request
 *          class A {
 *              @autowired
 *              xx  //= new Instance()
 *          }
 *          class B {
 *              @autowired
 *              xx //= new Instance()
 *          }
 *
 *          prototype -> request1, request2 request3 -> visit same instanceA and get same xx
 *          request   -> request1, request2 request3 -> visit same instanceA and get diff xx
 *
 *
 *
 *          thread safe ?
 *          class A {
 *              inject B
 *          }
 *          class B {
 *              only has method () {}
 *          }
 *
 *                     B instance (shared value)
 *
 *          T1 stack        T2 stack
 *          method1()       method1()
 *
 *  AOP
 *
 * class Aspect {
 *      @Before
 *      @PointCutting(location)
 *      public void xx() {//logic a}
 * }
 *
 *
 *  a. Map relationship between classes/methods and  List<Aspect/MethodInvocation>
 *
 *  class A {
 *      method1() {has before aop aspect}
 *
 *      method2() {no before logic}
 *  }
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Spring -> package war file -> upload to tomcat (start tomcat by yourself)
 *  Spring -> write code -> manually setup configuration in class / xml..
 *
 *  Spring Boot -> package jar file(contains classes/code/tomcat) -> java -jar ..
 *  Spring Boot -> auto configuration
 *              -> application properties (instead of xml)
 *
 *
 *  Why Spring Boot
 *  1. auto config
 *  2. application.properties
 *  3. embedded tomcat
 *  4. actuator
 *  ..
 *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Questions:
 *  1. why Rest api
 *  2. stateless vs stateful
 *  3. coding: design rest api
 *  4. post vs put
 *  5. request param vs path variable
 *  6. how springmvc work
 *  7. what is TDD
 *  8. How do you design rest api / what you need to consider when you design rest api
 *  9. status code
 *  10. Why Spring: IOC , AOP
 *  11. what is IOC
 *  12. what is AOP
 *  13. diff IOC and DI
 *  14. why spring boot
 *  15. bean scopes in DI
 *  16. annotations you used in spring boot
 *
 *
 *
 *
 *
 *
 */