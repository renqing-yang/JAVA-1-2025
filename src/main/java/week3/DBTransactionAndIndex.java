//Locks in Database
//        Write Lock: exclusive lock => insert / update / delete ... select for update
//        Read Lock : select ..share
//
//        ------------------------------------------------------------------------
//        transaction
//        ACID
//        Atomicity
//        Consistency
//        Isolation Level (issues: dirty read, non-repeatable read, phantom read, lost update)
//        Read Uncommitted
//        Read Committed
//        Repeatable Read
//        Serializable : all select statements are using read lock
//        Durability
//
//        user1
//        begin tx
//        insert 5  rows in tableA
//        insert 5 rows in tableA
//        insert 15 rows in tableB
//        commit
//
//        user2
//        begin tx
//        read from tableA at time1
//        -----
//        read from xxx    at time1
//        commit
//
//        ------------------------------------------------------------------------
//        MVCC :
//
//        emp
//        id, name     row_id, tx_id, rollback pointer
//        1 , B          ..     2    ,  |
//        |
//        1 , A          ..     1    ,  null
//
//
//        Read View
//
//        user
//        begin tx (assigned tx_id = 3,  Isolation = read commit)
//        select   (get current newest read view(1))  => 1, A
//        --tx2 commit
//        select   (get current newest read view(1, 2)) => 1, B
//
//
//
//        user
//        begin tx (assigned tx_id = 3,  Isolation = read commit)
//        select   (get current newest read view(1))  => 1, A
//        --tx2 commit
//        select   (get previous generated read view(1)) => 1, A
//
//        ------------------------------------------------------------------------
//        Lost Update and Optimistic lock
//        user1           user2
//        read a=1        read a=1
//        update a+1
//        commit
//        update a+1
//        commit
//
//        db(a=2)
//
//        id, name, version
//        1,  A   , 1 /timestamp
//
//        user1           user2
//        read a=1,v=1    read a=1,v=1
//        update a+1,v+1
//        where v=1
//        commit db(a=2,v=2)
//        update a+1, v+1 where v=1 -> throw exception
//
//        retry
//        read a=2,v=2
//        update a+1, v+1 where v=2
//        commit db(a=3,v=3)
//        --------------------------------------------------------
//
//        B+ tree
//
//        []  root
//        insert (1, 10, 200)
//        [1, 10, 200]  root
//        insert (50)
//        [50]   root
//        /   \
//        [1, 10]  [50, 200]
//        insert (60, 70, 80)
//        [50, 70]   root
//        /   \       \
//        [1, 10]  [50, 60]  [70, 80, 200]
//        insert (90, 100, 110)
//        [90]  root
//        /       \
//        [50, 70]            [90, 110]
//        /     \      \              |       \
//        [1, 10]<->[50, 60]<->[70, 80]<->[90, 100]<->[110, 200]
//        row_id               row_id
//
//
//
//        bitmap
//        id, state           row_id, NJ, NY
//        1 ,  NJ                     1 , 0
//        2 ,  NY                     0 , 1
//        3 ,  NJ                     0 , 1
//
//        NJ: 100
//        NY: 011
//        NJ or NY => 100 | 011 => 111
//        NJ and Ny => 100 & 011 => 000
//
//
//
//        10101010101010101 mark visited index
//
//
//        16 time slots  -> 01110101111
//
//        11111110000
//
//
//        clustered index
//        hash clustered index -> java hashmap
//
//
//        --------------------------
//        Full table scan : scan entire table (multiple blocks)
//        Index access path:
//        index unique scan
//        index range scan
//        index full scan
//
//        select /*+ full(e) full(d) use_nl(e d) leading(e) parallel(10) */ employee_id, department_name from hr.employees e join hr.departments d on e.department_id = d.department_id
//        -- select /*+ index(e) parallel(10) */ * from hr.employees e
//        select employee_id, department_name from hr.employees e join hr.departments d on e.department_id = d.department_id
//        select employee_id, department_name from hr.employees e, hr.departments d where e.department_id = d.department_id
//        select employee_id from hr.employees where employee_id >= 100 and employee_id <= 101
//
//
//
//
//        merge join : two sorted id -> two pointer to merge
//        hash join : hashing + bucket
//        nested loop join  : O(N^2)
//
//
//
//
//        Today's questions
//        1. what is transaction
//        2. why do we need transaction
//        3. ACID
//        4. isolation levels
//        5. what is dirty read / non-repeatable read / phantom read / lost update
//        6. database lock
//        7. optimistic lock
//        8. what is B+ tree
//        9. clustered index vs non-clustered index
//        10. bitmap index
//        11. when b+ tree, and when bitmap index, and when shouldn't we use index
//        12. how to optimize db performance
//        13. what is execution plan
//        14. what is hint
//        15. name some hints in database
//
//
//        next monday :
//        table design
//        oracle architecture
//
//        next tuesday :
//        will send mock interview link to your email on Monday
//        30 min mock interview