## Code Challenge - PostgreSQL DATA BASE
The goal of this code challenge is to create a microservice using Java and any framework that you think it is
appropriate.</br>
Return to Main: </br>
[README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)

## PostgreSQL Database Requirements
<pre>
### Local, Remote or microsevice  Postgresql Database Instance.
Local or Remote Postgresql Database Instance, must be installed and service running.
Local IP configuration must be available on pg_hba.con file,
in order to accept request from the IP address of the Test PC.
Database must be created:
name:  orangeapi
owner: postgres
Tables will be created on first runnig of the APP.
Tables used by App:
    account
    accounttransaction

(See d.properties sample   on src/main/resources project's directory) 
</pre>
### Hibernate File:  db.properties (Sample used)
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
### Initial Data Base Setup.
<pre>
Database "orangeapi", has to be created using postgresql command lines,
or any PG Administrator. (Recommended PGAdmin).
Once Database is created, OrangeBookApp creates the two tables, 
than will be used on test exercises.
Tables:
    Account.   Entity that store accounts and balance amount.
    AccountTransaction.  Entity that store account transaction processed.

Two SQL sripts are provided on sql directory of OrangeBookApp project, 
for additional Database settings. 
They must be run on this order:
    accounts_init_db.sql
    transactions_init_db.sql
    (*) Next App release will be avoid this step.
</pre>
## Initial Data Base Data
<pre>
OrangeBookApp requires soma initial data to be setup before running transaction tests.
New Accounts must be created, <b>accounts_init_db.sql</b> script provides sample initial accounts values. 
INSERT INTO public.account(
	id, account_iban, balance, name)
	VALUES 
	(1, 'ES9820385778983000760236', 1000.00, 'Luis Amesty Linares'),
	(2, 'ES9820385778983000760234', 12000.00, 'Maria Auxiliadora Amesty'),
	(3, 'ES9820385778983000760230', 10000.00, 'Maria Alejandra Amesty'),
	(4, 'ES9820385778983000760240', 8000.00, 'Luis Amesty Morello'),
	(5, 'ES9820385778983000760238', 7000.00, 'Maria Virginia Linares') ;
</pre>
<pre>
Additional Java Class is provided for Initial Data Base:
    InitDBTables.java       Provides Java code for same purpose.
    HibernateUtil.java      Provides local Hibernate DB session.
This classes uses json files provided on <b>json</b> directory of main app. 
A json parser reader is modelled in order to read this files:
    Account.json
    Transaction.json
</pre>