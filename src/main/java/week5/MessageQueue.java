package week5;

/**
 *  order service(producer) -> queue(cluster) ->  notification service
 *                              disk
 *
 *
 *  1. using Asynchronous process
 *  2. can take more request than server capacity
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  active mq / rabbit mq
 *  Queue model / pub sub model
 *  Producer1                                   Consumer1
 *  Producer1       -       Queue       -       Consumer2
 *  Producer1                                   Consumer3
 *
 *  SQS : queue model
 *  SNS : pub-sub
 *      - SQS - consumer
 *  SNS - SQS - consumer
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  kafka
 *  Broker1 : server
 *          : T1
 *            p1(leader)
 *            p2(follower)
 *
 *  Broker2 : server
 *          : T1
 *            p1(follower)
 *            p2(leader)
 *
 *  Consumer Group
 *  Consumer1(server)
 *  Consumer2(server)
 *
 *
 *
 *  What is kafka topic, broker, partition, offset
 *  what is re-balance in kafka
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  How to handle duplicate message / or retry messages problems
 *    1. global unique message id and Idempotent Service
 *    2. cache / use sns to deduplicate
 *          consumer
 *          get message
 *          check message duplication
 *          process message
 *          -------
 *          write id into cache to deduplicate
 *          commit tx
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Multi DB TX / Global TX - 1
 *   service  -> message queue
 *    |
 *   DB
 *
 *   options
 *   1. begin db tx1
 *      begin message queue tx2
 *      insert to db
 *      push msg to message queue
 *      commit tx1
 *      --service shuts down / crashes
 *      commit tx2
 *
 *   2. begin db tx1
 *      begin message queue tx2
 *      insert to db
 *      commit tx1
 *       --service shuts down / crashes
 *      push msg to message queue
 *       --service shuts down / crashes
 *      commit tx2
 *
 *   3. begin db tx1
 *      begin message queue tx2
 *      push msg to message queue
 *      commit tx2
 *       --service shuts down / crashes
 *      insert to db
 *      commit tx1
 *
 *   service  -> message queue
 *    |
 *   DB
 *
 *   change data capture and outbox pattern
 *
 *   service1
 *     |
 *    DB(outbox table) -  service2(monitor) - message queue
 *
 *   service1
 *   begin db tx
 *   insert data to db
 *   insert message to outbox table
 *   commit tx
 *
 *   service2
 *   read outbox table
 *   push message to message queue
 *   commit mq tx
 *   mark message as processed in outbox table
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Multi DB TX / Global TX - 2
 *
 *  Two Phase Commit / Three Phase Commit
 *
 *           coordinator(server) - log
 *           /      \
 *         DB1      DB2
 *
 *      phase1: check with two db
 *              pre-run query(see syntax issues / other issues)
 *      phase2: send commit request to both db
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Multi DB TX / Global TX - 3
 *  SAGA pattern
 *      +100
 *     s1 - mq1 - s2 - mq2 - s3
 *      |        |           |
 *     db1      db2         db3
 *
 *    1. s1 commit tx in db1 , push message to mq1
 *    2. s2 commit tx in db2
 *          success -> push message to mq2 ..
 *          if error happens -> push message to dead letter queue / rollback queue -> commit -100 in s1 / db1
 *
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  How to execute message only once in your application ? impossible
 *  How to avoid duplicate messages
 *  how to handle global transaction
 *  what is queue model
 *  what is pub sub model
 *  why use message queue , benefits of message queue
 *  What is kafka topic, broker, partition, offset
 *  what is re-balance in kafka
 *  diff rabbitmq and kafka and sqs
 *  when we use rabbitmq when we use kafka and sqs
 *
 */