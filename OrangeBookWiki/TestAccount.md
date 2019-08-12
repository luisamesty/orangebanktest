## Code Challenge - TEST Account API
Return to Main: [README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## <b>TEST Account API</b>
<pre>
This test has to be run on <b>OrangeBookApp.war</b> APP.
Account API is used for CRUD accounts.
As mentioned Account Table holds account information as Name, IBAN, initial balance and current balance.
It is the entity to validate transactions on iban code and balance.
These tests haven't been requested on the Code Challenge, but i think it was neccesary for transaction
 validation and also for me, to begin with building the APP in order to be familiarized with Test Challenge 
 Requirements. 
Test can be executed locally on Eclipse environment. 
In order to be allset, OrangeBookApp.war must be running
on Local Apache TOMCAT, and ready to accept request on:
<b>http://localhost:8080</b>. 
If running as a microservice on a container, IP address must be changed.
</pre>
## <b>READ All Accounts</b>
<pre><pre>
If Database is created and also the accounts initiated as mentioned before on PostgreSQL chapter, then you can read all stored accounts coming from <b>Account.json</b> File using <b>InitDBTables.java</b> program.
</pre>
<center><b>Actions</b>:</center>
<pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/
JSON Data: (Not required)
</pre>
<center><b><b>Results</b></b>:</center>
<pre>
<u><b>Result message: (all accounts included)</b></u> 
[
    {
        "id": 1,
        "name": "Luis Amesty Linares",
        "account_iban": "ES9820385778983000760236",
        "balance": 1000.00,
        "initbalance": 1000.00
    },
    {
        "id": 2,
        "name": "Maria Auxiliadora Amesty",
        "account_iban": "ES9820385778983000760234",
        "balance": 12000.00,
        "initbalance": 12000.00
    },
    {
        "id": 3,
        "name": "Maria Alejandra Amesty",
        "account_iban": "ES9820385778983000760230",
        "balance": 10000.00,
        "initbalance": 10000.00
    },
    {
        "id": 4,
        "name": "Luis Amesty Morello",
        "account_iban": "ES9820385778983000760240",
        "balance": 8000.00,
        "initbalance": 8000.00
    },
    {
        "id": 5,
        "name": "Maria Virginia Amesty",
        "account_iban": "ES9820385778983000760238",
        "balance": 7000.00,
        "initbalance": 7000.00
    },
]
</pre></pre>

## <b>Read ONE Account (ID=4)</b>
<pre><pre>
Read only one Account. Record number must be indicated on URL.
</pre>
<center><b>Actions</b>:</center>
<pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/4
JSON Data: (Not required)
</pre>
<center><b>Results</b>:</center>
<pre>
<u>Result message: (only Account ID = 4)</u> 
{
    "id": 4,
    "name": "Luis Amesty Morello",
    "account_iban": "ES9820385778983000760240",
    "balance": 8000.00,
    "initbalance": 8000.00
}

</pre></pre>

## <b>INSERT ONE Account</b>
<pre><pre>
Insert one Account TEST in this case a new account can be added to Account table. Next sequence ID is taken.
</pre>
<center><b>Actions</b>:</center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/
JSON Data: 
    {
        "name": " Haydee Amesty",
        "account_iban": "ES9820385778983000760239",
        "balance": 15500.00,
        "initbalance": 15500.00
    }
</pre>
<center><b>Results</b>:</center>
<pre>
<u><b>Result message:</b></u> 
New Account has been saved with ID:9
<u><b>Error message when IBAN is equal to an existent one:</b></u>
** ERROR ** EXIST Account IBAN:ES9820385778983000760239. NO Account has been saved with ID:0
</pre></pre>

## <b>Update Account (ID=3)</b>
<pre><pre>
Update values from a previosly created account. In this case we show account id 3 (Previos Values):
        "name": "Maria Alejandra Amesty",
        "account_iban": "ES9820385778983000760230",
        "balance": 10000.00,
        "initbalance": 10000.00
        
And will be changed to (New Values):
        "name": "Maria Alejandra Amesty",
        "account_iban": "ES9820385778983000760239",
        "balance": 12500.00,
        "initbalance": 112500.00
</pre>
<center><b>Actions #1</b>:</center>
<pre>
POSTMAN REQUEST: <b>PUT</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/3
JSON Data:
{
        "id": 3,
        "name": "Maria Alejandra Amesty",
        "account_iban": "ES9820385778983000760239",
        "balance": 12500.00,
        "initbalance": 12500.00
}
</pre>
<center><b>Result #1</>:</center>
<pre>
<u>Result messages:</u> 
<b>Account has been updated successfully.</b>

</pre>
<center><b>Action #2</b>:</center>
<pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/3
JSON Data: (Not required)

</pre>
<center><b>Result #2</b>:</center>
<pre>
<u>Result messages (Observe New Updated Values):</u> 
{
    "id": 3,
    "name": "Maria Alejandra Amesty",
    "account_iban": "ES9820385778983000760239",
    "balance": 12500.00,
    "initbalance": 12500.00
}


</pre></pre>
## <b>Delete Account (5)</b>
<pre><pre>
Delete Account can be used to erase an account from Database.
Account has to be unused on any transaction, in order to leave data consistency.
</pre>
<center>Action #1:</center>
<pre>
POSTMAN REQUEST: <b>DELETE</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/account/5
JSON Data: (Not required)
</pre>
<center>Result #1:</center>
<pre>
<u>Result messages:</u> 
<b>Account has been deleted successfully.</b>
<u>Error message when Accoount ID is not found:</u>
<b>** ERROR ** Account DO NOT EXIST. NO Account has been deleted with ID:8</b>
</pre></pre>

Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)