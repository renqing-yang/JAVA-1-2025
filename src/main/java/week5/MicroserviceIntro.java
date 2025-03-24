package week5;

/**
 * Monolithic
 *      1. horizontal and vertical scaling
 *      ..
 * AWS
 *    serverless(only submit code/config to aws) vs EC2 vs local server room(manage/own xx)
 *
 *
 *          user
 *           |
 *      API Gateway     -  Cognito
 *          |      \
 *        Lambda    ALB - EC2(inventory) - DB
 *          |         \
 *       DynamoDB   EC2(order)
 *                      |
 *                     DB
 *
 *
 *  Microservice
 *      1. Api Gateway (aws api gateway, spring cloud gateway)
 *             a. authorization
 *             b. rate limiter
 *                  sol1: token bucket [max 1000 tokens]
 *
 *                         \    /
 *                          \__/ ->
 *                          keep dropping token to bucket every 1min/ every 30s
 *                  sol2: [time1, time2, time3, time4]
 *                          0       0      0     0.59
 *
 *                       [time1, time2, time3, time4]
 *  *                     0.59    0.59   0.59    1
 *
 *                      3 requests per s
 *
 *             c. global unique id ...
 *                  sol1: db generator/sequence
 *                  sol2: number (64 digit number)
 *                        timestamp + machine_id + process_id + t_id + serial number
 *
 *                        if serial number is 4 digit -> 0000 ~ 1111
 *                  sol3: uuid
 *
 *      2. Discovery Service / Service Registration..  (aws cloudmap, spring cloud eureka..)
 *              a -> http://b-name/.. -> b
 *                 \                    /
 *                  discovery service
 *            [b-name: ip1:port, ip2:port, ip3:port]
 *      3. Centralized Config Service (spring cloud config)
 *      4. Centralized Security Service
 *      5. Circuit Breaker (spring cloud Hystrix)
 *              b -> c(shutdown)
 *
 *            status
 *            open  b -> c
 *                  track how many requests have failed
 *                  limit 3 failed requests in last 5 requests -> if > 3 -> change status to close
 *
 *            close return default result
 *                  send background request b -> c (check health status) -> if ok -> change status to open
 *
 *
 *
 *
 *
 */