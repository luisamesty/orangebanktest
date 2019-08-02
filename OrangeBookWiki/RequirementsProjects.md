## Code Challenge
The goal of this code challenge is to create a microservice using Java and any framework that you think it is
appropriate.
## Return to Main
- [Home] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## Apps and Projects
<pre>
App has been separated on three projects:
OrangeCodeChallenge(Main GitHub)
    OrangeBookApp
    OrangeBookTestApp
    OrangeBookWiki
</pre>
### OrangeCodeChallenge(Main GitHub)
<pre>
This is the main directory that contains all projects.
Some elements used on this main App are : Java, Spring, Maven, Hibernate, PostgreSQL, JUnit, Tomcat, HTML, CSS, MarkUP files.
</pre>
#### OrangeBookApp
<pre>
This is the App for Handling Accounts and Transactions.
This App is base on Spring and Hibernate running on a PostgreSQL Database.
Some of dependencies imported on pom.xml:
- spring-webmvc
    version: 4.3.10.RELEASE
- spring-orm
    version: 4.3.10.RELEASE
- hibernate-core
    version: 5.2.11.Final
- hibernate-c3p0
    version: 5.2.11.Final
- postgresql
    driver version: 9.4.1211
- javax.servlet-api
    version: 3.1.0
- maven-compiler-plugin
    version: 3.6.0
Embedded Apache Tomcat required for testing web application
</pre>
#### OrangeBookTestApp
<pre>
This is the App for testing.
It's based on same dependencies.
</pre>
#### OrangeBookWiki
<pre>
This project contains only md files for README links and documentation.
Future Wiki for App on GitHub.
</pre>
