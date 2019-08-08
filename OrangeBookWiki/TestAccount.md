## Code Challenge - TEST Account API
Return to Main: [README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## TEST Account API
<pre>
This test has to be run on <b>OrangeBookApp.war</b> APP.
Account API is used for CRUD accounts.
As mentioned Account Table holds account information as Name, IBAN, initial balance and current balance.
It is the entity to validate transaction on iban code and balance.
This test whas not been requested on the Code Challenge, but it was interesting for me to begin with building the APP in order to be familiarized with this kind of project. Additionally to have this table ready to validate accounts and transaction amounts.
Test can be executed locally on Eclipse environment. 
In order to be allset, OrangeBookApp.war must be running
on Local Apache TOMCAT, and ready to accept request on <b>http://localhost:8080</b>. If running on a container IP address must be different.
</pre>
### Read All Accounts
<pre>
If Database is created and also the accounts initiated as mencioned before. The you can read stored accounts.

POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/
JSON Data: (Not required)
</pre>
<pre>
<u><b>Result message: (all accounts included)</b></u> 
[
    {
        "id": 86,
        "name": "Maria Auxiliadora Amesty",
        "account_iban": "ES9820385778983000760234",
        "balance": 12000.00,
        "initbalance": 12000.00
    },
    {
        "id": 87,
        "name": "Maria Alejandra Amesty",
        "account_iban": "ES9820385778983000760230",
        "balance": 10000.00,
        "initbalance": 10000.00
    },
    {
        "id": 88,
        "name": "Luis Amesty Morello",
        "account_iban": "ES9820385778983000760240",
        "balance": 8000.00,
        "initbalance": 8000.00
    },
    {
        "id": 89,
        "name": "Maria Virginia Amesty",
        "account_iban": "ES9820385778983000760238",
        "balance": 7000.00,
        "initbalance": 7000.00
    },
    {
        "id": 85,
        "name": "Luis Amesty Linares",
        "account_iban": "ES9820385778983000760236",
        "balance": 1100.00,
        "initbalance": 1000.00
    },
    {
        "id": 94,
        "name": "Luis P Amesty Linares",
        "account_iban": "ES9820385778983000760986",
        "balance": 8000.00,
        "initbalance": 12000.00
    }
]
</pre>

### Read ONE Account (ID=85)
<pre>
Recod numer must be indicated on URL.
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/85
JSON Data: (Not required)
<u>Result message: (only Account ID = 85)</u> 
{
    "id": 85,
    "name": "Luis Amesty Linares",
    "account_iban": "ES9820385778983000760236",
    "balance": 12000.00,,
    "initbalance": 1000.00
}

</pre>

### Update Account (ID=85)
<pre>
POSTMAN REQUEST: <b>PUT</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/85
JSON Data:
{
    "id": 85,
    "name": "Luis Amesty Linares",
    "account_iban": "ES9820385778983000760236",
    "balance": 12000.00,
    "initbalance": 1000.00
}
<u>Result messages:</u> 
<b>Account has been updated successfully.</b>
<u>Error message when Accoount ID is not found:</u>
** ERROR ** Account and ID Missmatch. NO Account has been updated with ID:85 / 1
</pre>
### Delete Account (85)
<pre>
POSTMAN REQUEST: <b>DELETE</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/85
JSON Data: (Not required)
<u>Result messages:</u> 
<b>Account has been deleted successfully.</b>
<u>Error message when Accoount ID is not found:</u>
<b>** ERROR ** Account DO NOT EXIST. NO Account has been deleted with ID:85</b>
</pre>

Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)