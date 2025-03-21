package week4;

/**
 *  1. authentication: identity verification
 *      401
 *      ID token (user info)
 *  2. authorization:
 *      role: admin1(endpoint1:read), admin2(endpoint1:write), admin3(endpoint1:write, endpoint1:read), user..
 *      scope: endpoint1:read,  endpoint1:write
 *      endpoint1: endpoint1:read,  endpoint1:write
 *      endpoint2: endpoint2:read,
 *
 *      403
 *  3. OAuth2.0
 *      implicit
 *      user
 *       |
 *      api gateway(server)   - security center(server)
 *      |
 *      endpoint(server)
 *
 *      a. api gateway (security config => endpoint1: endpoint1:read) -> verify token with security center
 *      b. api gateway -> endpoint -> security center
 *
 *      explicit
 *      user   -      security center
 *       |
 *      app(frontend)
 *      |
 *     backend  - code ->   security center
 *             <- access token
 *
 *      a. user visit app frontend
 *      b. app redirect your request to (predefined/provided by security center) security center
 *      c. input your user identity
 *      d. redirect to a callback url?code=xxxxx
 *      e. app gonna get access_token by using the code
 *      f. user send request to endpoint(header Authorization access_token)
 *
 *  4. OpenID = authentication flow + authorization flow(OAuth 2.0)
 *  5. JWT  =  header.payload.signature
 *      a. header = encryption info / algo name
 *      b. payload =  issue time / exp time / auth from ? / user info / scopes / token for ?
 *      c. signature = encrypted(header.payload)
 *  6. HTTPS (key exchange process)
 *      use public key + private key to get a symmetric between each user and server
 *
 *      client(public key)  ---  server(private key)
 *
 *                      -> public key[random data]
 *                      <- private key[hash(random data)]
 *                  generate symmetric key by using random data
 *                          symmetric encrypt data
 *  7. CORS
 *      app1 -request> app2
 *      /endpoint1  get
 *
 *      flow:
 *      a. preflight request to /endpoint1  option
 *         return response header: access origin(*,  localhost:..) / access header(Authorization, ...) / access method(get/post..)
 *      b. browser will verify access-origin / header/ method
 *      c. you can send request to /endpoint1 get
 *  8. CSRF
 *          link get
 *  9. SQL / XSS injection
 *      xml injection: username: <script></script>
 *      sql injection: username: ;drop table / or true; drop table
 *                  "select where username =" + input value
 *                  Query query = Query("select where username =?1")
 *                  query.set(1, input);
 * 10. spring security
 *
 *    request -> filter1 -> filter2  ->  (spring mvc) dispatcher servlet
 *
 *              class FilterClass {
 *                  .. doFilter(chain) {
 *                      //pre filter logic
 *                      chain.doFilter();
 *                      //post filter logic
 *                  }
 *              }
 *    configure spring security
 *    a. configure authentication manager
 *    b. impl user details service (load user by user name)
 *    c. impl user details
 *    d. add jwt filter into spring security filter chain
 *
 *
 *    authentication flow
 *    a. create controller /auth/login
 *       inject authentication manager in controller
 *       authManager.authenticate(user request login data)
 *       return error message 401
 *       or return jwt token
 *
 *    authorization flow
 *    a. create customized jwt filter
 *       get Authorization header , extract token
 *       use jwt library to verify token
 *       if valid -> save it to security context(thread local)
 *                   access endpoint, @PreAuthorize("hasRole['Admin']")
 *       not valid -> 403
 *
 */