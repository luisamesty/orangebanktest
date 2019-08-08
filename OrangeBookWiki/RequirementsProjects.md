## Code Challenge - Apps and projects developed
Return to Main: [README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## Apps and projects developed
<pre>
App has been separated on three projects:
OrangeCodeChallenge(Main GitHub)
    OrangeBookApp
    OrangeBookWiki    
    OrangeBookTestApp
</pre>
### OrangeCodeChallenge(Main GitHub)
<pre>
This is the main directory that contains all projects.
Some elements used on this main App are : Java, Spring, Maven, Hibernate, PostgreSQL, JUnit, Tomcat, HTML, CSS, MarkUP files.
Includes the main README.md file.
</pre>
#### OrangeBookApp
<pre>
This is the App for Handling Accounts and Transactions.
This App is based on Spring and Hibernate API running on a PostgreSQL Database.
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
Embedded Apache Tomcat required for testing web application.
OrangeBookApp.war: The first time is initiated, having created th database <b><u>orangeapi</u></b> as will be mentioned on PotgreSQL chapter, this APP creates Tables structure.
This APP accepts individual transactions using Postman.
Also two Java main classes are included:
    src..com..utils.InitDBTables.java
    src..com..transactions.CreateTransaction.java

<u><b>InitDBTables.java</b></u>: Once Database is created with both tables Account and AccountTransaction. This java program creates 5 sample Accounts, taken from a <u><b>Account.json</b></u> file on main <u><b>json</b></u> directory of project
<u><b>InitDBTables.java</b></u>: Once Database is created with both tables Account and AccountTransaction. This java program creates 5 sample Accounts.
<u><b>CreateTransaction.java</b></u>: Once Database is created with both tables Account and AccountTransaction and Accounts created using previous java program. This java program creates sample transactions. Test user just have to modify <u><b>Transaction.json</b></u> to change the test scope.
</pre>
#### OrangeBookWiki
<pre>
This project contains only md files for README links and documentation.
Future Wiki for App on GitHub.
</pre>
#### OrangeBookTestApp
<pre>
This is another App for testing using an web browser.
It shares same Database, but for a mistake made for me at the begining it was initially developed on a different version on Hibernate.
So i have had to handle differences on API classes, that should be the same ones.
What I should have done from the beginning is to do a plugin project with all the common classes and two projects, a project for the API and another web project for the WEB tests. Sharing the same resources and the same version of Spring, Hibernate and Java.
</pre>
Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)

