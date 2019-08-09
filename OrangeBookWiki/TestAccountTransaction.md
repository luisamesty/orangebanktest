## Code Challenge - TEST Account Transaction API
Return to Main: [README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## TEST Account Transaction API
<pre>
Account Transaction API is used for inserting and validating transactions.
As mentioned Account Transaction Table holds transaction information as Date, Reference, IBAN, Amount, Fee and Status.
It is the entity to validate transactions.
Test can be executed locally on Eclipse environment. 
In order to be allset, OrangeBookApp.war must be running
on Local Apache TOMCAT, and ready to accept request on <b>http://localhost:8080</b> . If running on a container IP address must be different.
</pre>

### Read All Account Transactions
<pre>
If I previously execute the java program with the transactions in the Transaccion.json file, the validated transactions can be read.
</pre>
<pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/get/
JSON Data: (Not required)
<u>Result message:</u> 
[
    {
        "id": 90,
        "account_iban": "ES9820385778983000760236",
        "treference": "12345A",
        "trfecha": "2019-07-16T16:55:42.000Z",
        "tramount": 3.19,
        "trfee": 3.18,
        "trdescription": "Restaurant payment",
        "trstatus": "SETTLED",
        "trchannel": "CLIENT"
    },
    {
        "id": 91,
        "account_iban": "ES9820385778983000760236",
        "treference": "12345B",
        "trfecha": "2019-07-16T16:55:42.000Z",
        "tramount": 5.35,
        "trfee": 5.35,
        "trdescription": "Dept Store payment",
        "trstatus": "SETTLED",
        "trchannel": "CLIENT"
    },
    {
        "id": 92,
        "account_iban": "ES9820385778983000760236",
        "treference": "12346A",
        "trfecha": "2019-07-16T16:55:42.000Z",
        "tramount": 2.00,
        "trfee": 2.00,
        "trdescription": "Restaurant payment",
        "trstatus": "SETTLED",
        "trchannel": "CLIENT"
    },
    {
        "id": 93,
        "account_iban": "ES9820385778983000760236",
        "treference": "12346B",
        "trfecha": "2019-07-16T16:55:42.000Z",
        "tramount": 2.00,
        "trfee": 2.00,
        "trdescription": "Dept Store payment",
        "trstatus": "SETTLED",
        "trchannel": "CLIENT"
    }
]
</pre>
### Create Account Transaction
<pre>
Using Postman. 
In Sample Data base init only few account transaction were created. 
So additional transactions can be tested.
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/add
SETTINGS: Body - Raw - JSON (application/json)
JSON Data:
 {
		"account_iban": "ES9820385778983000760234",
    	"treference":"12346B",
		"trfecha":"2019-07-16T19:58:42.000Z",
		"tramount":2000.00,
		"trfee":2.00,
		"trdescription":"Dept Store payment",
		"trchannel" : "CLIENT"
}
<u>Result message:</u> <b>New Account Transaction has been saved with ID:176 Status:SETTLED</b>

</pre>
### Read ONE Account Transaction(ID=176)
<pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/get/176
JSON Data: (Not required)
<u>Result message:</u> 
{
    "id": 176,
    "account_iban": "ES9820385778983000760234",
    "treference": "12346B",
    "trfecha": "2019-07-16T19:58:42.000Z",
    "tramount": 2000.00,
    "trfee": 2.00,
    "trdescription": "Dept Store payment",
    "trstatus": "SETTLED",
    "trchannel": "CLIENT"
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

Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)