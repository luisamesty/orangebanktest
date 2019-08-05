## Code Challenge - TEST Account Transaction API
The goal of this code challenge is to create a microservice using Java and any framework that you think it is
appropriate.</br>
Return to Main: </br>
[README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## TEST Account Transaction API
<pre>
Account Transaction API is used for CRUD transactions.
As mentioned Account Transaction Table holds transaction information as Date, Reference, IBAN, Amount, Fee and Status.
It is the entity to validate transactions.
Test can be executed locally on Eclipse environment. 
In order to be allset, OrangeBookApp.war must be running
on Local Apache TOMCAT, and ready to accept request on <b>http://localhost:8080</b>
</pre>
### Create Account Transaction
<pre>
Using Postman. 
In Sample Data base init only few account transaction were created. 
So additional transaction no. 64 can be tested.
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/post
JSON Data:
{
        "id": 64,
        "account_iban": "ES9820385778983000760236",
        "amount": 2.00,
        "fee": 2.00,
        "trdescription": "Dept Store payment",
        "trstatus": "** Por revisar ***",
        "reference": "00000B",
        "date": "2019-07-16T16:55:42.000Z"
}
<u>Result message:</u> <b>Estado HTTP 415 – Tipo de medio no soportado</b>
** To be solved
</pre>
### Read All Account Transactions
<pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/get/
JSON Data: (Not required)
<u>Result message:</u> 
[
    {
        "id": 60,
        "account_iban": "ES9820385778983000760236",
        "amount": 3.19,
        "fee": 3.18,
        "trdescription": "Restaurant payment",
        "trstatus": "** Por revisar ***",
        "reference": "12345A",
        "date": "2019-07-16T16:55:42.000Z"
    },
    {
        "id": 61,
        "account_iban": "ES9820385778983000760236",
        "amount": 5.35,
        "fee": 5.35,
        "trdescription": "Dept Store payment",
        "trstatus": "** Por revisar ***",
        "reference": "12345B",
        "date": "2019-07-16T16:55:42.000Z"
    },
    {
        "id": 62,
        "account_iban": "ES9820385778983000760236",
        "amount": 2.00,
        "fee": 2.00,
        "trdescription": "Restaurant payment",
        "trstatus": "** Por revisar ***",
        "reference": "12346A",
        "date": "2019-07-16T16:55:42.000Z"
    }
]
</pre>
### Read ONE Account Transaction(ID=61)
<pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/get/61
JSON Data: (Not required)
<u>Result message:</u> 
{
    "id": 61,
    "account_iban": "ES9820385778983000760236",
    "amount": 5.35,
    "fee": 5.35,
    "trdescription": "Dept Store payment",
    "trstatus": "** Por revisar ***",
    "reference": "12345B",
    "date": "2019-07-16T16:55:42.000Z"
}
</pre>
### Update Account Transaction (ID=63)
<pre>
POSTMAN REQUEST: <b>PUT</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/put/63
JSON Data:
{
        "id": 63,
        "account_iban": "ES9820385778983000760236",
        "amount": 2.00,
        "fee": 2.00,
        "trdescription": "Dept Store payment",
        "trstatus": "** Por revisar ***",
        "reference": "12346B",
        "date": "2019-07-16T16:55:42.000Z"
}
</pre>
<u>Result message:</u> <b>Estado HTTP 415 – Tipo de medio no soportado</b>
<b>** To be solved</b>
### Delete Account Transaction (ID=63)
<pre>
POSTMAN REQUEST: <b>DELETE</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/del/63
JSON Data: (Not required)
<u>Result messages:</u> 
<b>Account Transaction has been deleted successfully..</b>
<u>Error message whe Accoount ID is not found:</u>
<b>** ERROR ** Account Transaction DO NOT EXIST. NO transaction has been deleted with ID:63</b>
</pre>