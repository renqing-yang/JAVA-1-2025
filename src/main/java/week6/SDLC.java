package week6;

/**
 *  Agile Scrum : JIRA
 *      BackLog: TODO list
 *          story : secure user login
 *          ticket
 *      Sprint = 2 weeks
 *      1. Sprint Planning Meeting
 *           points: fib / hours
 *      2. Daily Standing Up 15 ~ 30 min
 *      3. Retrospective Meeting
 *      4. Demo Review Meeting (1 ~ few sprints)
 *
 * Scrum team (less than 10)
 *      1. Manager -> prod owner
 *      2. BA
 *      3. QA (belongs to qa team or your team)
 *      4. Developers
 *      5. Scrum Master
 *      6. Shared DBA
 *      dev ops team =>
 *
 * Git Branches
 * main branch ------------1.3 product
 *
 * hotfix branch
 *
 * release branch  ------------------------2.0---uat
 *                              /
 * dev branch -------------------------dev
 *                  \          /
 * feature branch    ----------   local
 *
 * AWS lambda -> microVM(fire cracker by AWS)
 *
 * CI/CD (on server)
 *  git webhook   build  ->  test   ->   report  ->    package  ->    deploy
 *              mvn build..        sonarqube(server)              ECS (fargate / ec2)
 *                      mvn test                      image
 *                                                push image to ECR
 *
 * Daily Work
 *      1. Daily Stand Up Meeting
 *      2. Meetings ..
 *      3. Coding:
 *          check out feature branch
 *          follow TDD flows
 *          send pull request (pr) code review
 *          merge to dev
 *      4. trigger the CI/CD pipeline / jenkins pipeline
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  api gateway -> (proxy) ->  Lambda
 *  import boto3
 *  def handler(event):
 *     .....
 *     ...
 *     boto3 -> connect s3
 *     boto3 -> dymanoDB
 *
 *
 *  option1: aws console
 *  option2: use git to manage your lambda code
 *           install aws sam
 *           aws sam build
 *           aws sam local invoke  / invoke local --...
 *           aws sam deploy --guided /--env
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Lambda configure
 *      1. function name
 *      2. role
 *      3. where we read lambda log => cloudwatch log group
 *      4. running time (timeout max 3s/10s/20s)
 *      5. size ->
 *              memory size (cpu)
 *              disk size
 *      6. layers (attached env, library, setting to lambda, 250MB total)
 *      7. code to lambda
 *         docker with lambda
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */