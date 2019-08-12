## Code Challenge - PostgreSQL DATA BASE
Return to Main: [README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)

## <b>PostgreSQL Database Requirements</b>
### <b>Local, Remote or microsevice  Postgresql Database Instance.</b>
<pre>
Local or Remote Postgresql Database Instance, must be installed and service running.
Local IP configuration must be available on pg_hba.con file,
in order to accept request from the IP address of the Test PC and Docker containers.
</pre>
### <b>The Database must be created (Very Iportant):</b>
<pre>
<b><u>name:</u>  orangeapi</b>
<b><u>owner:</u> postgres</b>
Tables will be created on first runnig of OrangeBookApp on local eclipse o microservice container.
Tables used by App:
    account
    accounttransaction
</pre>
### <b>Configuration tables:</b>
<pre> 
On Project Directory: .../src/main/resources
See <b>db.properties</b>  
And <b>Hibernate.cfg.xml</b>
Properties Url, user and password must be adjusted as your Database settings.
</pre>

### <b>Hibernate File:  db.properties (Sample used)</b>
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
### <b>Hibernate.cfg.xml (Sample used)</b>
<pre>
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
        <property name="hibernate.connection.url">jdbc:postgresql://192.168.1.23:5432/orangeapi</property>
        <property name="hibernate.connection.password">Sofi@2015</property>
        <property name="hibernate.connection.username">postgres</property>
</pre>
### <b>Initial Data Base Setup.</b>
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
    <b>accounts_init_db.sql</b>
    <b>transactions_init_db.sql</b>
    (*) Next App release will be avoid this step.
</pre>
## <b>Initial DB Data </b>
<pre>
OrangeBookApp requires some initial data to be setup before running transaction tests. This is not strictly necessary bacuse business logic takes care of validations and also accounts can be created using Postmas, as described on <b>Account API Test</b> chapter.
New Accounts can be created, <b>accounts_init_db.sql</b> script provides sample initial accounts values. 
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
    <b>InitDBTables.java</b>       Provides Java code for same purpose.
    <b>HibernateUtil.java</b>      Provides local Hibernate DB session.
This classes uses json files provided on <b>json</b> directory of main app. 
A json parser reader is modelled in order to read this files:
    Account.json
    Transaction.json
This file can be executed as a java progran under eclipse environment.
</pre>
### Reset Data Base.
<pre>
When it is neccessary to start over a new App test. Database "orangeapi" can be set to initial sattus.
Both tables AccountTransaction and Account, must be truncated and start again with java programas and scripts.
Only sequence numbers for records will start with last id sequences.
Also tables can be droped, but in this case OrangeBookApp.war App must be re-started and initial sql script 
executed in order to set tables properly to start again.


Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)