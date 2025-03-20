package week4;

/**
 * Why ORM
 * 1. map data from table to pojo/entity
 * 2. connection pool
 * 3. entity relations
 *      1 - m
 *      @Entity
 *      @Table("..")
 *      class Department {
 *          @Id
 *          @GeneratedType(GeneratedValue.Indentity)
 *          private String id;
 *          @Column("..")
 *          private String name;
 *          @OneToMany
 *          private List<Employee> employeeList;
 *
 *      }
 *
 *      class Employee {
 *          @ManyToOne
 *          private Department dept;
 *      }
 *      Lazy Loading vs Eager Loading
 *      Department dept = load dept from database
 *      List<Employee> employees = dept.getEmployeeList();
 *      for(Employee e: employees) {
 *          print(e info)
 *      }
 *  4. HQL (hibernate query language)
 *  5. CriteriaQuery = Dynamic Query = Builder Pattern
 *
 *      select ..
 *      from ..
 *      where age and grad
 *
 *
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *
 *  How to use ORM (Hibernate / JPA)
 *  1. create Pom.xml dependency
 *  2. session factory (hibernate)
 *     entity manager factory (jpa)
 *  3. get session / EntityManager
 *  4. session.get() , session.load()
 *     entityManager.find()
 *     provide class type Employee.class,  "primary key"
 *
 *     entityManager.createQuery().. / createNativeQuery
 *
 *  How to use Spring Data JPA
 *  1. only write interface extends JpaRepository
 *
 *
 *
 *
 *
 * Questions
 * 1. why orm
 * 2. diff hibernate and jpa
 * 3. diff lazy loading and eager loading
 * 4. what is @Query in spring data jpa
 * 5. what is @Transactional annotation and transaction propagation
 * 6. how to use orm / hibernate
 * 7. what is pom.xml / maven
 * 8. coding: impl spring boot project from scratch(no repository part) on whiteboard
 *
 * Homework (exception, transaction, log)
 * 1. project1: create student and teacher (m - m)
 *              a. create 3 entity classes
 *                 1 - m  m - 1
 *              b. create student endpoints
 *              c. create junction table endpoints
 *              d. create m - m relationship in database
 *              e. deadline is Monday before 1:30pm cdt
 *              include your sql query scripts in github
 * 2. project2: create project which takes user request and fetch data from 3rd party api
 *              a. finish in 20min
 *              b. use rest template to fetch https://jsonmock.hackerrank.com/api/countries
 *              c. impl api to display name and translations
 *                  user send name in requestparam -> get name and translations
 *                 impl api to get all
 *                 ** /endpoint  get all
 *                 ** /endpoint?requestparam
 *
 *                 class A {
 *                     B[] data;
 *                 }
 *                 class B {
 *                     C translations;
 *                 }
 *              d. deadline is Monday before 1:30pm cdt
 * 3. project3: add authentication and authorization to project1
 *              a. deadline is Tuesday before 1:30pm cdt
 *
 *
 *
 *
 */