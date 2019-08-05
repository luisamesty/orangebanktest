## Code Challenge - TEST Account API
The goal of this code challenge is to create a microservice using Java and any framework that you think it is
appropriate.</br>
Return to Main: </br>
[README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## TEST Account API
<pre>
Account API is used for CRUD accounts.
As mentioned Account Table holds account information as Name, IBAN, and balance.
It is the entity to validate transaction on iban code and balance.
Test can be executed locally on Eclipse environment. 
In order to be allset, OrangeBookApp.war must be running
on Local Apache TOMCAT, and ready to accept request on <b>http://localhost:8080</b>
</pre>
### Create Account
<pre>
Using Postman. 
In Sample Data base init only 5 accounts were created. So additional accout no. 6 can be tested for creating.
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/
JSON Data:
   {
    	"id": 6,
        "name": "Luis G Amesty Linares",
        "account_iban": "ES9820385778983000760286",
        "balance": 8000
    }
<u>Result message:</u> <b>New Account has been saved with ID:6</b>
</pre>
### Read All Accounts
<pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/
JSON Data: (Not required)
<u>Result message: (all accounts included)</u> 
[
    {
        "id": 1,
        "name": "Luis Amesty Linares",
        "account_iban": "ES9820385778983000760236",
        "balance": 1000.00
    },
    {
        "id": 2,
        "name": "Maria Auxiliadora Amesty",
        "account_iban": "ES9820385778983000760234",
        "balance": 12000.00
    },
    {
        "id": 3,
        "name": "Maria Alejandra Amesty",
        "account_iban": "ES9820385778983000760230",
        "balance": 10000.00
    },
    {
        "id": 4,
        "name": "Luis Amesty Morello",
        "account_iban": "ES9820385778983000760240",
        "balance": 8000.00
    },
    {
        "id": 5,
        "name": "Maria Virginia Linares",
        "account_iban": "ES9820385778983000760238",
        "balance": 7000.00
    },
    {
        "id": 64,
        "name": "Luis G Amesty Linares",
        "account_iban": "ES9820385778983000760286",
        "balance": 8000.00
    }
]
</pre>
### Read ONE Account (ID=1)
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/1
JSON Data: (Not required)
<u>Result message: (only Account ID = 1)</u> 
[
    {
        "id": 1,
        "name": "Luis Amesty Linares",
        "account_iban": "ES9820385778983000760236",
        "balance": 1000.00
    }
 ]
</pre>
### Update Account (ID=1)
<pre>
POSTMAN REQUEST: <b>PUT</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/1
JSON Data:
{
    "id": 1,
    "name": "Luis G Amesty Linares",
    "account_iban": "ES9820385778983000760236",
    "balance": 8000
}
<u>Result messages:</u> 
<b>Account has been updated successfully.</b>
<u>Error message when Accoount ID is not found:</u>
** ERROR ** Account and ID Missmatch. NO Account has been updated with ID:10 / 1
</pre>
### Delete Account (5)
<pre>
POSTMAN REQUEST: <b>DELETE</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/5
JSON Data: (Not required)
<u>Result messages:</u> 
<b>Account has been deleted successfully.</b>
<u>Error message when Accoount ID is not found:</u>
<b>** ERROR ** Account DO NOT EXIST. NO Account has been deleted with ID:59</b>
</pre>