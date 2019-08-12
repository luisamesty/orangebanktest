## Code Challenge - TEST Account Transaction API
Return to Main: [README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## <b>TEST Account Transaction API</b>
<pre>
Account Transaction API is used for inserting and validating transactions.
As mentioned Account Transaction Table holds transaction information as Date,
Reference, IBAN, Amount, Fee and Status.
It is the entity to validate transactions.
Test can be executed locally on Eclipse environment. 
In order to be allset, OrangeBookApp.war must be running
on Local Apache TOMCAT, and ready to accept request on:
 <b>http://localhost:8080</b> . 
 If running as a microservice on a container, IP address must be changed.
</pre>

## <b>ASUMPTIONS</b>
<pre>
<b>FEES</b> are not well defined, if they are positive or negative.
Payload process is for registering Deducting and Addition transactions.
I am asumming:
<b>- FEES: </b>
FEES are Allways, positive values and amount is deducted from balance.
<b>- Payload logic: </b>
Transaction is recorded and Amount plus and fee value considered.
Amount is positive: (Positive Amount value) - (fee) is added.
Amount is negative: (Positive Amount value) + (fee) value is deducted.
</pre>

## <b>READ All Account Transactions</b>

If Iyou previously execute the java program with the transactions in the <b>Transaccion.json</b>
file, the validated transactions can be read.</br>
<center><b>Actions:</b></center>
<pre><pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/get/
JSON Data: (Not required)
</pre>
<center><b>Results:</b></center>
<pre>
<u>Result message:</u> 
[
    {
        "id": 17,
        "account_iban": "ES9820385778983000760236",
        "treference": "12345A",
        "trfecha": "2019-07-16T16:55:42.000Z",
        "tramount": -493.37,
        "trfee": 1.00,
        "trdescription": "Restaurant payment",
        "trstatus": "OK",
        "trchannel": "CLIENT"
    },
    {
        "id": 18,
        "account_iban": "ES9820385778983000760236",
        "treference": "12345B",
        "trfecha": "2019-07-16T18:55:42.000Z",
        "tramount": -893.37,
        "trfee": 1.14,
        "trdescription": "Dept Store payment",
        "trstatus": "OK",
        "trchannel": "CLIENT"
    },
    {
        "id": 19,
        "account_iban": "ES9820385778983000760236",
        "treference": "12346A",
        "trfecha": "2019-07-16T19:55:42.000Z",
        "tramount": -1193.38,
        "trfee": 2.00,
        "trdescription": "Restaurant payment",
        "trstatus": "OK",
        "trchannel": "CLIENT"
    }
]
</pre></pre>

## <b>Create Account Transaction</b>
<pre><pre>
Using Postman. 
In Sample Data base init only few account transaction were created. 
So additional transactions can be tested.
</pre>
<center><b>Actions:</b></center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/add
SETTINGS: Body - Raw - JSON (application/json)
JSON Data:
 {
	"reference":"912345678910",
	"account_iban": "ES9820385778983000760234",
	"fecha":"2019-07-16T19:58:42.000Z",
	"amount":-500.38,
	"fee":2.00,
	"description":"Dept Store payment"
}
</pre>
<center><b><b>Results:</b></b></center>
<pre>
<u>Result message:</u>
 <b>New Account Transaction has been saved with ID:1 Status:OK</b>
<u>Result message with Same reference:</u>
 <b>** ERROR Account Transaction not SAVED *** Status:** TRANSACTION ERROR REFERENCE EXISTS ** REF:912345678910</b>
<u>Result message with Wrong Account:</u>
 <b>** ERROR Account Transaction not SAVED *** Status:** TRANSACTION ERROR ACOUNT INVALID** REF:912345678910 IBAN:ES9820385778983000760234-9</b>
<u>Result message from Account:</u>
    {
        "id": 2,
        "name": "Maria Auxiliadora Amesty",
        "account_iban": "ES9820385778983000760234",
        "balance": 11497.62,
        "initbalance": 12000.00
    },
The new balance is 11497.62 = 12,000.00 - 500.38 - 2.00
</pre></pre>

## <b>Read ONE Account Transaction(ID=176)</b></br>
Retrieves one register giving the ID key.
<center><b>Actions:</b></center>
<pre><pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/get/1
JSON Data: (Not required)

</pre>
<center>Result:</center>
<pre>
<u>Result message:</u> 
{
    "id": 1,
    "account_iban": "ES9820385778983000760234",
    "treference": "912345678910",
    "trfecha": null,
    "tramount": -500.38,
    "trfee": 2.00,
    "trdescription": "Dept Store payment",
    "trstatus": "OK",
    "trchannel": null
}
</pre></pre>

Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)