<p align="center"><img width=12.5% src="https://github.com/PanagiotisDrakatos/SpringBoot-MVC-Hibernate-JPA-Mysql/blob/master/Images/logo.png"></p>
<p align="center"><img width=55% src="https://github.com/PanagiotisDrakatos/SpringBoot-MVC-Hibernate-JPA-Mysql/blob/master/Images/login-pi-logo-large.png"></p>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;



# Basic Overview
A simple, but at the same time powerful secure login and signup system, which mixes a lot of microservices together and make a unique choice to adopt it. In addition, it comes along with the latest Spring framework Techonologies. Furthemore, this example is composed by a combination of  Spring Boot 1.5.6,Spring Security, Mysql,Hibernate and JPA,  Bootstrap 3 for the form design as well as jQuery for event handling.

<p align="center">
  <img src="https://github.com/PanagiotisDrakatos/SpringBoot-MVC-Hibernate-JPA-Mysql/blob/master/Images/login.png">
</p>


# Why should i use it 

 &nbsp; Because you will make your __life easier__. Imagine that you want to create a big website with much complexity for the backend in addition, you must use a lot of microservices architectures in your Web Project. :sweat: Firstly, relax you have already saved a lot of time. Your login and sign up page of your system is ready, it's secure and full open source. Independently who you are, this is your right choice to start. :yum: :yum: :yum: <br />
 &nbsp; You have the ability to expand it as you wish. Basically, you declare your component and the relationship between them and the framework takes care of the lifecycle of your components and wires them together into a useful graph of objects. If you consider how many libraries Spring integrates with, the competition fades away. A good way to convince yourself is to look at the number of guides or how-tos are available on the <a href="http://spring.io/guides"> SpringWebsite</a>. All of them follow one important pattern: specify the correct dependency, initialize a Spring component and autowire library beans into it.
 
<p align="center">
  <img src="https://github.com/PanagiotisDrakatos/SpringBoot-MVC-Hibernate-JPA-Mysql/blob/master/Images/register.png">
</p>
 
# Deployment
In order to deploy this projects, you need to perform some steps:
* About 15 minutes :kissing_closed_eyes: 
* A favorite text editor or IDE
* <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html">JDK 1.8</a> or later
* <a href="http://www.gradle.org/downloads">Gradle 2.3+</a> or <a href="https://maven.apache.org/download.cgi">Maven 3.0+</a>
* You can also import the code straight into your IDE:
     * <a href="/guides/gs/sts">Spring Tool Suite (STS)</a>
     * <a href="/guides/gs/intellij-idea/">IntelliJ IDEA</a>
     
# Installation

## Configure MySQL
In order to create, execute, and optimizing SQL queries for this project i am using <a href="https://www.mysql.com/products/workbench/">MySQLWorkbench<a/> but you can also use whatever tool you want, as well as mysql command  if you feel comfortable with it.

The code below represent the mysql schema that we will need to create for our web project.

```sql
CREATE DATABASE db_example; 
CREATE USER 'tutorialuser'@'localhost' IDENTIFIED BY 'tutorialmy5ql';
GRANT ALL PRIVILEGES ON *.* TO 'tutorialuser'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```
Don't Forget to insert roles to the users before to continue in the next step. [Build&Deploy](#build&Deploy)

```SQL
INSERT INTO `role` VALUES (1,'ADMIN');
INSERT INTO `role` VALUES (2,'USER');
```

## Build and Deploy
___
```
$ cd github-maven-example/example
$ mvn clean install
```

This is a Spring Boot project, so you can deploy it by simply using the main class: ```Application.java``` 

The compiled, source, and Javadoc WAR files will be placed in your target folder just simple ran the coomand below if you want to start the project from the war file. (__Attention!!!__  [Configure MySQL](#configure-mysql) before continue).
```
$ cd (Your Path here)\SpringBoot-Registration-Login-ThePerfectExample\target\
$ java -jar original-Spring-Full-Security-1.0-SNAPSHOT.war
```

If everything is working properly you will able to open the <a href="https://127.0.0.1:8443/">https://127.0.0.1:8443/</a>

you will automatically be redirected  in the Loginpage


# Usage
___
## Basic Functionality

### SpringDataJPA
  In order to start leveraging the Spring Data programming model with JPA, a DAO interface needs to extend the JPA specific Repository interface – JpaRepository. This will enable Spring Data to find this interface and automatically create an implementation for it.By extending the interface we get the most relevant CRUD methods for standard data access available in a standard DAO out of the box.
  
  To define more specific access methods, Spring JPA supports quite a few options – you can:
   * simply __define a new method__ in the interface.
   * provide the actual __JPQ query__ by using the @Query annotation.
   * use the more advanced __Specification and Querydsl support__ in Spring Data.
   * define __custom queries__ via JPA Named Queries.
   
When Spring Data creates a new Repository implementation, it analyses all the methods defined by the interfaces and tries to __automatically generate queries from the method names.__ While this has some limitations, it is a very powerful and elegant way of defining new custom access methods with very little effort.   

Let’s look at an example: if the managed entity has a name field (and the Java Bean standard `getName` and `setName` methods), we’ll define the `findByName` method as well as `findByRole` in the DAO interface; this will automatically generate the correct query
   
 ```java
 
 @Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
    
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
 }
 
}
 ```

## SecurityPerspective

Here in this content we will define the security matters which resulting and we will make a brief explanation from the security perspective about how we solve these ones.

### AuthenticationProviders

   An AuthenticationProvider is an abstraction for fetching user information from a specific repository (like a database, LDAP, custom third party source, etc. ). It uses the fetched user information to validate the supplied credentials. Simply put, when multiple authentication providers are defined, the providers will be queried in the order they’re declared. 
  For a quick demonstration, we’ll configure  an in-memory authentication provider.

  In our configuration class, let’s now create and add the authentication providers using the ___AuthenticationManagerBuilder___. 

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    public SecurityConfig() {
        super();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);

    }
 }
```
### PasswordEncoding
This project discusses use password encoding for registration process because storing the password in plaintext may can lead to critical security issues.
 There are a few encoding mechanism supported by Spring Security – and for the article we’ll use BCrypt, as it’s usually the best solution available. Most of the other mechanism, such as the MD5PasswordEncoder and ShaPasswordEncoder use weaker algorithms and are now deprecated.
 
 We’ll start by defining the simple __BCryptPasswordEncoder__ as a bean in our configuration:
 
 ```java
 @Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
 ```
 
  Older implementations – such as SHAPasswordEncoder – would require the client to pass in a salt value when encoding the password.BCrypt, however, __will internally generate a random salt__ instead. This is important to understand because it means that each call will have a different result, and so we need to only encode the password once.
  Also be aware that the BCrypt algorithm generates a String of length 60, so we need to make sure that the password will be stored in a column that can accommodate it. A common mistake is to create a column of a different length and then get an Invalid Username or Password error at authentication time.
 
### PersistentRememberMe
 The classic solution used a cookie called remember-me – containing the username, expiration time and MD5 hash containing the password. Because it contains a hash of the password, __this solution is potentially vulnerable__ if the cookie is captured. With that in mind – let’s take a look at the this approach – using PersistentTokenBasedRememberMeServices to store the persisted login information in a database table between sessions.
 
First – we need to have the login information in the database – we need a table creating to hold the data:


```sql
create table if not exists persistent_logins ( 
  username varchar_ignorecase(100) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null
);
```

Navigate in the <a href="https://github.com/PanagiotisDrakatos/SpringBoot-Registration-Login-ThePerfectExample/blob/master/src/main/java/com/github/tutorial/Spring/SecurityConfig.java">Config</a> class, and for the sake of completeness, here is the way persistence is set up:


```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.tokenRepository(persistentTokenRepository())
              
    }
  @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
```
As we mentioned, the standard __TokenBasedRememberMeServices__ was storing the hashed user password in the cookie.

This solution – the __PersistentTokenBasedRememberMeServices__ uses a __unique series identifier for the user__. This identifies the initial login of the user and remains constant each time the user gets logged in automatically during that persistent session. It also contains a __random token__ that is regenerated each time a user logs in via the persisted remember-me functions.This combination of randomly generated series and token are persisted, making a brute force attack very unlikely.
### Session
  Spring Session has the simple goal of free up session management from the limitations of the HTTP session stored in the server. The solution makes it easy to share session data between services in the cloud without being tied to a single container (i.e. Tomcat). Additionally, it supports multiple sessions in the same browser and sending sessions in a header.
  Spring Session uses a filter, org.springframework.web.filter.DelegatingFilterProxy, which accepts the HttpRequest and constructs and injects its own Request object down the hierarchy. This way it gains control to the way new sessions are created, since the session object is attached to the HttpRequest Object.


Here we should setup  the session. 
```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.essionManagement()
                .sessionFixation().migrateSession()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/invalidSession")
                .maximumSessions(1)
                .expiredUrl("/invalidSession");
              
    }
```
In addition we should also setup the SessionListener in order to be ready to handle callbacks from the methods `sessionCreated(final HttpSessionEvent event)`
`sessionDestroyed(final HttpSessionEvent event)`

```java
public class SessionListenerWithMetrics extends HttpSessionEventPublisher {

    private final AtomicInteger activeSessions;
    private final AtomicInteger timeoutSessions;
    private final Counter counterOfActiveSessions;
    private int SessionTimeout = 1 * 50;

    public SessionListenerWithMetrics() {
        super();
        activeSessions = new AtomicInteger();
        timeoutSessions = new AtomicInteger();
        counterOfActiveSessions = MetricRegistrySingleton.metrics.counter("web.sessions.active.count");
//      context.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    }

    // API

    @Override
    public final void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println("Session Created: ");
        event.getSession().setMaxInactiveInterval(SessionTimeout);
        timeoutSessions.set(event.getSession().getMaxInactiveInterval());
        counterOfActiveSessions.inc();
    }

    @Override
    public final void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println("Session Destroyed: ");
        counterOfActiveSessions.dec();
    }
 }
```

Don't hesitate to ask me whatever you want. 
contact with me ::: 8095446907

# Contributing
___
 2. Create your feature branch: git checkout -b my-new-feature
 3. Commit your changes: git commit -am 'Add some feature'
 4. Push to the branch: git push origin my-new-feature
 5. Submit a pull request 
 6. :smile: :smile: :smile:
 
 
 
