## Code Challenge
The goal of this code challenge is to create a microservice using Java and any framework that you think it is
appropriate.
## Return to Main
- [Home] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## PostgreSQL Database Requirements
<pre>
Local or Remote Postgresql Database Instance, must be installed and service running.
Local IP configuration must be available on pg_hba.con file, in order to accept request from the IP address of the Test PC.
Database must be created:
name:  orangeapi
owner: postgres
Tables will be created on first runnig of the APP.
Tables used by App:
    account
    accounttransaction

(See d.properties sample   on src/main/resources project's directory) 
</pre>
### File:  db.properties
<pre>
# PostgreSQL properties
postgresql.driver=org.postgresql.Driver
postgresql.url=jdbc:postgresql://192.168.1.23:5432/orangeapi
postgresql.user=postgres
postgresql.password=Sofi@2015
postgresql.default_schema=public

# Hibernate properties
hibernate.show_sql=true
hibernate.hbm2ddl.auto=update

#C3P0 properties
hibernate.c3p0.min_size=5
hibernate.c3p0.max_size=20
hibernate.c3p0.acquire_increment=1
hibernate.c3p0.timeout=1800
hibernate.c3p0.max_statements=150
</pre>