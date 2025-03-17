package week3;

/**
 * SELECT *
 * FROM HR.EMPLOYEES
 *
 * select first_name, last_name, 0 as count
 * from hr.employees
 *
 *
 * select first_name, last_name, salary, (case when salary > 10000 then 1 else 0 end)
 * from hr.employees
 *
 *
 * select first_name, last_name, salary, (case salary when 24000 then 'a' else 'b' end)
 * from hr.employees
 *
 * --aggregation functions : avg, sum, min, max, count
 * select max(salary) as max
 * from hr.employees
 *
 * select count(*)
 * from hr.employees
 *
 * select count(commission_pct)
 * from hr.employees
 *
 * --where
 * select *
 * from hr.employees
 * where salary > 10000 and employee_id > 100
 * order by salary desc
 *
 * --subquery
 *
 * select *
 * from (select * from hr.employees) t
 *
 *
 *
 * --group by
 * table x
 * cola,  colb
 *   1     x..
 *   1     x..
 *   2     x..
 *
 *   group by cola
 *   resultset contains 2 rows
 *
 * select salary, count(*) as cnt
 * from hr.employees
 * group by salary
 * -- order by salary desc
 * having cnt < 4
 * order by cnt desc, salary desc
 *
 * -- union distinct(A + B),  union all A + B
 * select count(*) from
 * (
 *     select first_name, last_name from hr.employees
 *     union all
 *     select first_name, last_name from hr.employees
 * )
 *
 * select count(*) from
 * (
 *     select first_name from hr.employees
 *     union
 *     select first_name from hr.employees
 * )
 *
 * -- intersect  duplicate part of (A, B)
 * select count(*) from
 * (
 *     select first_name, last_name from hr.employees
 *     intersect
 *     select first_name, last_name from hr.employees
 * )
 *
 * --A minus B :   A - duplicate part of (A, B)
 * select count(*) from
 * (
 *     select first_name, last_name from hr.employees
 *     minus
 *     select first_name, last_name from hr.employees
 * )
 *
 * --cross join
 * tableA      tableB
 * a           c
 * b           d
 * c
 *
 * result
 * a,  c
 * a,  d
 * b,  c
 * b,  d
 * c,  c
 * c,  d
 *
 * select *
 * from A, B
 *
 * --inner join == join
 * select department_id from hr.departments order by department_id desc
 * select department_id from hr.employees order by department_id desc
 *
 * select e.*, d.department_name
 * from hr.employees e join hr.departments d on e.department_id = d.department_id
 *
 * select e.*, d.department_name
 * from hr.employees e, hr.departments d
 * where e.department_id = d.department_id
 *
 *
 * --left outer join / right outer join  ==  left join / right join
 *
 *
 * select e.*, d.department_name
 * from hr.employees e right join hr.departments d on e.department_id = d.department_id
 *
 *
 * select e.*, d.department_name
 * from hr.employees e full join hr.departments d on e.department_id = d.department_id
 * --question1 ,  select 2nd highest salary from hr.employees table
 * select max(salary)
 * from hr.employees
 * where salary < (select max(salary) from hr.employees)
 *
 * select max(salary)
 * from hr.employees
 * where salary not in (select max(salary) from hr.employees);
 *
 * select *
 * from hr.employees e1
 * where 2 = (select count(distinct(salary)) from hr.employees e2 where e2.salary > e1.salary)
 *
 * select *
 * from (
 *     select e.employee_id, e.salary, dense_rank() over (order by salary desc) as rank
 *     from hr.employees e
 * )
 * where rank = 2
 *
 * emp table
 * id, salary
 * 1,  100  -> (select count(distinct(salary)) from hr.employees e2 where e2.salary > 100)
 * 2,  100
 * 3,  200
 *
 * --question2  get Kth highest salary in diff dept,  return dept_id, salary
 *
 * select *
 * from (
 *     select department_id, salary, dense_rank() over (partition by department_id order by salary desc) as rank
 *     from hr.employees
 *     order by department_id desc
 * ) t
 * where rank = 2
 *
 * --question3 get 3rd highest sum of salary from diff depts,  return dept_id, sum_salary
 * select salary, count(*) as cnt
 * from hr.employees
 * group by salary
 * -- order by salary desc
 * having cnt < 4
 * order by cnt desc, salary desc
 *
 *
 * Select department_id, sum_salary
 * from
 * (
 *     select department_id, sum(salary) as sum_salary, dense_rank() over (order by sum(salary) desc) as rank
 *     from hr.employees
 *     group by department_id
 * ) r
 * where rank=3
 *
 *
 * diff union and union all
 * diff where and having
 * diff inner join and outer join and cross join
 * coding: question 1, 2, 3 (finish sql question in 3 ~ 5 mins on whiteboard)
 *
 *
 *
 * Tomorrow:
 * 1. write few queries
 * 2. transaction
 * 3. index 
 */