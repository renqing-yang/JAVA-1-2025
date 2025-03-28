package week5;

/**
 * CAP
 *      Consistency
 *      Availability
 *      Partition Tolerance
 *
 * BASE
 *      Basic Availability
 *      Soft Stage
 *      Eventually Consistency
 * * * * * * * * * * * * * * * * *
 * Single Leader DB
 * write -> Leader / Master / Primary  (single instance)
 *
 * read -> Follower / Slave / Secondary (multiple instances)
 *
 * Sync data from Leader -> Followers
 *      user write to leader
 *      leader commit tx
 *      leader using background threads -> sync data to (0 ~ N) followers
 *      leader return response to user until N followers have got updates
 *      *      *      *      *      *      *      *      *      *      *      *
 * Multi Leader DB (redis)
 *          slot1 ~ 100     101 ~...
 *          Leader1          Leader2              Leader3
 *        /     \           /       \            /       \
 *     F1       F2
 *
 * All Leader / Leaderless (Cassandra / Consistent hashing)
 *  0      idx1 idx1+ 1   idx2
 * [][][][id1][][][]... [id5] -> max length = 1000
 *         |              |
 *        id2
 *         |
 *        id3
 *         |
 *        id4
 *
 *
 *             N1(0)
 *
 *    N4(40000)         N2(10000)
 *
 *           N3(20000)
 *
 *         read consistency level = 1 ~ replica factor
 *                            N1
 *              user -> N4 -> N3
 *                            N2
 *                   read (read consistency level) number of nodes
 *                   rc 1 => read fastest node
 *                   rc 2 or 3 => read data from fastest node
 *                                read hashing result(data) from other nodes
 *                                compare data from fastest node and other hashing result
 *                                if they are same => return result
 *                                if they not same => trigger "read repair" => return result
 *
 *         write consistency level = 1 ~ replica factor
 *                            N1  finished -> response -> N4
 *              user -> N4 -> N3  failed
 *                            N2  finished -> response -> N4
 *
 *                       wc 1 ~ 2 => success
 *                       wc 3 => error response to user
 *
 *         replica factor = 3
 *
 *         rc + wc > rf
 *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    *
 *   Sequential io: appending new log => log file
 *
 *   write -> mem table(memory) -> immutable SStable(sorted string table, saved in disk)
 *       |
 *      commit log(disk)
 *
 *   read -> mem table(memory) -> blooming filter -> SSTables -> merge them -> return result
 *
 *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    *
 *    mongodb
 *                      |
 *                  Mongos(Gateway)  -   Mongos(Config server)
 *          /           |            \
 *       sharding       sharding        sharding
 *       primary
 *       second
 *       second
 *       second
 *
 *
 *
 *  key: sharding location 1, 2, 3
 *
 * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    *
 * Elastic search
 * inverted index
 *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    * *    *
 * Question:
 * 1. what is CAP
 * 2. how to do DB cluster
 * 3. redis cluster, why redis, when to use redis
 * 4. do you know cassandra
 * 5. what is write consistency , read consistency , replica factor , what is QUORUM
 * 6. what is consistent hashing
 * 7. cassandra vs other databases
 *
 *
 *
 *
 *
 *
 */