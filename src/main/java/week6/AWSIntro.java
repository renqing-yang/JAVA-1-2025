package week6;

/**
 *  API Gateway ->
 *      resource : http / websocket / rest api
 *      example:
 *          /myEndpoint/xx  put => lambda
 *          authorizer: Lambda Authorizer / Cognito
 *                      scope
 *          lambda timeout
 *          validate request body / header ...
 *
 *          CORS
 *          /myEndpoint/xx  option => mock
 *          access-allow-header: CONTENT-TYPE...Authorization
 *          access-allow-method: PUT,POST,GET..
 *          access-allow-origin: *
 *
 * Cognito : identity management / security provider
 *      user pool:
 *          1. free hosted UI => login page
 *          2. create user
 *              email. name, ...custom:attribute
 *              provide sign up (from aws only or user can sign up)
 *          3. attach user to diff group
 *          4. create customized scopes for access_token
 *          5. create customized lambda
 *                  1. pre-token-generation
 *                  2. before sending token to user
 *
 *  S3:
 *      1. has no customized index
 *      2. /bucket_name/xxx/xx/x/object_name
 *      3. immutable
 *              put / post
 *      4. version
 *      5. set up life cycle hook => delete/move obj to other places after X days
 *      6. to access private bucket (policy)
 *             a. attach role to lambda / ec2
 *             b. generate a pre-signed url (type of object, timeout, obj name)
 *                      get / put
 *      7. upload object in single request
 *         multipart upload in multiple request
 *              1. send initial request to s3
 *                  <- upload id
 *              2. upload many chunks to s3 in parallel
 *                  -> upload id
 *                  -> key
 *              3. send final request to s3
 *                  -> upload id
 *                  -> [key1, 2, 3, 4, 5]
 *                  <- MD5/..hashing value
 *      8. cross region replica
 *
 *  Route53(cross-region)
 *      1. health check -> diff region
 *      2. DNS
 *  NLB(region)
 *      1. network layer 4: TCP , UDP
 *
 *  ALB(region)
 *      1. application layer 7: http, websocket
 *      2. /endpoint -> diff target group
 *      3. target group
 *             ASG : auto scaling group
 *                  EC2(install cloudwatch agent)
 *
 *  CloudWatch
 *      1. monitor -> dashboard
 *      2. logs/group
 *      3. alert
 *      4. event bridge -> send event to lambda / sns ...
 *
 *  CloudTrail
 *
 *  VPC
 *      1. subnet: CIDR block
 *              private subnet:
 *              public subnet : attached with internet gateway
 *      2. route table
 *
 *  VPC Endpoint
 *      ec2 -> vpc endpoint -> aws network -> s3 / dynamodb
 *
 *  VPC Link
 *      api gateway ->vpc link-> vpc ec2
 *
 *  AWS Connect
 *      1. customer support dashboard
 *      2. flow chart
 *          phone call -> number -> flow
 *                                  |
 *                                play prompt(?) to you
 *                                 |
 *                               options
 *                             /    |     \
 *                            1     2      3
 *                            |     |       |
 *                     waiting queue
 *                           |
 *                 support team answer your call
 *
 *  AWS LEX
 *      1. chat bot
 *          intention ->
 *
 *  AWS POLLY -> convert text to audio
 *  AWS transcribe -> convert audio to text
 *  Kinesis -> streaming
 *
 *  EC2
 *  DynamoDB
 *      1. partition key / sharding key
 *      2. sorted key
 *      3. global secondary index
 *      4. DAX
 *
 *  SQS
 *      1. standard queue
 *      2. FIFO
 *      3. dead letter queue(rollback)
 *      4. visibility timeout
 *  CloudMap
 *  ECS
 *      1. task definition
 *          a. docker image repo / location
 *          b. container size
 *          c. ec2 / fargate
 *          d. cloud map path / service name
 *          f. connect to load balancer
 *          g. set up health check endpoint / script
 *          h. cloudwatch log group
 *          i. iam role
 *          ..
 *      2. service -> different tasks
 *
 *  ECR
 *      1. docker repository
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 * AWS GLUE
 *      1. ETL process
 *      2. read data from "data source"
 *      3. spark / language -> map reduce job
 *      4. catalog
 *      5. save to s3
 *              a. csv
 *              b. parquet
 *
 * AWS Sagemaker
 *      1. train model -> output model -> s3 model
 *      2. deploy model -> sagemaker endpoint
 *
 *      1. train model -> ec2 / aws batch..
 *      2. deploy model -> ec2
 *
 * AWS Athena -> query s3
 *
 */