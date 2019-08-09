## Code Challenge - TEST Account Transaction API
Return to Main: [README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## <b>TEST Account Transaction API</b>
<pre>
Account Transaction API is used for inserting and validating transactions.
As mentioned Account Transaction Table holds transaction information as Date, Reference, IBAN, Amount, Fee and Status.
It is the entity to validate transactions.
Test can be executed locally on Eclipse environment. 
In order to be allset, OrangeBookApp.war must be running
on Local Apache TOMCAT, and ready to accept request on <b>http://localhost:8080</b> . If running on a container IP address must be different.
</pre>


### <b>READ All Account Transactions</b>

If I previously execute the java program with the transactions in the Transaccion.json file, the validated transactions can be read.</br>
<center>Actions:</center>
<pre><pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/get/
JSON Data: (Not required)
</pre>
<center>Results:</center>
<pre>
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
</pre></pre>

### <b>Create Account Transaction</b>
<pre><pre>
Using Postman. 
In Sample Data base init only few account transaction were created. 
So additional transactions can be tested.
</pre>
<center>Actions:</center>
<pre>
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
</pre>
<center>Results:</center>
<pre>
<u>Result message:</u> <b>New Account Transaction has been saved with ID:176 Status:SETTLED</b>

</pre></pre>

### <b>Read ONE Account Transaction(ID=176)</b></br>
Retrieves one register giving the ID key.
<center>Actions:</center>
<pre><pre>
POSTMAN REQUEST: <b>GET</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/get/176
JSON Data: (Not required)
</pre>
<center>Result:</center>
<pre>
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
</pre></pre>

### <b>Transaction STATUS</b>

<pre><pre>
This endpoint, based on the payload and some business rules, will return the status and additional information
for a specific transaction.
</pre>
<center>Actions:</center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
    "reference": "82346B",
    "channel": "CLIENT"
}
</pre>
<center>Result:</center>
<pre>
<u>Result message when transaction is found:</u> 
{
    "reference": "82346B",
    "amount": 200.25,
    "status": "INVALID"
}
</pre></pre>

### <b>Business Rules (A)</b>
<pre><pre>
Given: A transaction that is not stored in our system
When: I check the status from any channel
Then: The system returns the status 'INVALID'
</pre>
<center>Actions:</center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
    "plreference": "XXX82346B",
    "plchannel": "CLIENT"
}
</pre>
<center>Result:</center>
<pre>
<u>Result message when transaction not found:</u> 
[
    {
        "reference": "XXX82346B",
        "status": "INVALID"
    }
]
</pre></pre>

### <b>Business Rules (B)</b>
<pre><pre>
Given: A transaction that is stored in our system
When: I check the status from CLIENT or ATM channel
And the transaction date is before today
Then: The system returns the status 'SETTLED'
And the amount substracting the fee.
</pre>
<center>Actions:</center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:

</pre>
<center>Result:</center>
<pre>
<u>Result message:</u> 

</pre></pre>

### Business Rules (C)
Given: A transaction that is stored in our system
When: I check the status from INTERNAL channel
And the transaction date is before today
Then: The system returns the status 'SETTLED'
And the amount
And the fee
<pre><pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:

</pre>

<pre>
<u>Result message when transaction not found:</u> 

</pre></pre>

### Business Rules (D)
Given: A transaction that is stored in our system
When: I check the status from CLIENT or ATM channel
And the transaction date is equals to today
Then: The system returns the status 'PENDING'
And the amount substracting the fee
<pre><pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
"reference":"12345A",
"channel":"ATM"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
"reference":"12345A",
"status":"PENDING",
"amount":190.20
}
</pre></pre>


### Business Rules (E)
Given: A transaction that is stored in our system
When: I check the status from INTERNAL channel
And the transaction date is equals to today
Then: The system returns the status 'PENDING'
And the amount
And the fee
<pre><pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
"reference":"12345A",
"channel":"INTERNAL"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
"reference":"12345A",
"status":"PENDING",
"amount":193.38,
"fee":3.18
}
</pre></pre>

### Business Rules (F)
Given: A transaction that is stored in our system
When: I check the status from CLIENT channel
And the transaction date is greater than today
Then: The system returns the status 'FUTURE'
And the amount substracting the fee
<pre><pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
"reference":"12345A",
"channel":"CLIENT"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
"reference":"12345A",
"status":"FUTURE",
"amount":190.20
}
</pre></pre>

### Business Rules (G)
Given: A transaction that is stored in our system
When: I check the status from ATM channel
And the transaction date is greater than today
Then: The system returns the status 'PENDING'
And the amount substracting the fee
<pre><pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
"reference":"12345A",
"channel":"ATM"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
"reference":"12345A",
"status":"PENDING",
"amount":190.20
}
</pre></pre>

### Business Rules (H)
Given: A transaction that is stored in our system
When: I check the status from INTERNAL channel
And the transaction date is greater than today
Then: The system returns the status 'FUTURE'
And the amount
And the fee
<pre><pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
"reference":"12345A",
"channel":"INTERNAL"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
"reference":"12345A",
"status":"FUTURE",
"amount":193.38,
"fee":3.18
}
</pre></pre>
Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)