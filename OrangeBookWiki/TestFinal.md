## Code Challenge - FINAL TESTS
The goal of this code challenge is to create a microservice using Java and any framework that you think it is
appropriate.</br>
Return to Main: </br>
[README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## <b>FINAL TESTS</b>
<pre>
OnangeBookApp must be tested as required on Challenge Test.
On this chapter i will write te TEST as they were made by me running the APP with real data.

## <b>ASUMPTIONS</b>
<pre>
FEES are not well defined, if they are positive or negative.
Payload process is for registering Deducting and Addition transactions.
Today Date is not well defined.
PENDING, SETTLED, FUTURE, INVALID definition.
</pre>
<center><b><b>I am asumming:</b></b></center>
<pre>

<b>- FEES: </b>
FEES are Allways, positive values and amount is deducted from balance.
<b>- Payload logic: </b>
Transaction is recorded and Amount plus and fee value considered.
Amount is positive: (Positive Amount value) - (fee) is added.
Amount is negative: (Positive Amount value) + (fee) value is deducted.
<b>- Payload logic (Allways response positive values): </b>
When consulting transaction from any channel, responses are allways positive values. I assume that i have to show then allways in postive value, even if they are negative or positive. Considering what it is assumed before, in reference to the fees.
<b>- Today Date:</b>
Today Date if taking assumming yyyy-MM-dd 00:00:00.000
Zero hour.
<b>- SETTLED:</b> It means that transaction is already applyed before today date.
<b>- FUTURE:</b> It means that transaction will be applyed after today day. Transactions with date greater than today date.
<b>- INVALID:</b> It means that transaction reference consulted does not exists.
<b>- PENDING:</b> It means that transaction was made with today date and will be applyed after today day.
</pre></pre>
## <b>Business Logic Table</b>
| DATE         	| SOURCE   	| STATUS  	| Amount               	| Fee 	|
|--------------	|----------	|---------	|----------------------	|-----	|
| Before Today 	| CLIENT   	| SETTLED 	| YES (substract fee)  	| NO  	|
| Before Today 	| ATM      	| SETTLED 	| YES (substract fee)  	| NO  	|
| Before Today 	| INTERNAL 	| SETTLED  	| YES                  	| YES  	|
| Today 	    | CLIENT   	| PENDING 	| YES (substract fee)  	| NO  	|
| Today 	    | ATM      	| PENDING	| YES (substract fee)  	| NO  	|
| Today 	    | INTERNAL 	| PENDING  	| YES                  	| YES  	|
| After Today 	| CLIENT   	| FUTURE	| YES (substract fee)  	| NO  	|
| After Today 	| ATM      	| PENDING 	| YES (substract fee)  	| NO  	|
| After Today 	| INTERNAL 	| FUTURE  	| YES                  	| YES  	|

## <b>Create Transaction</b>
<pre><pre>
This endpoint will receive the transaction information and store it into the system.
It is IMPORTANT to note that a transaction that leaves the total account balance bellow 0 is not allowed.
</pre>
<center><b>Actions:</b></center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/add
SETTINGS: Body - Raw - JSON (application/json)
JSON Data:
 {
	"reference":"20190711",
	"account_iban": "ES9820385778983000760234",
	"fecha":"2019-07-11T19:58:42.000Z",
	"amount":-500.38,
	"fee":2.00,
	"description":"Dept Store payment"
}
</pre>
<center><b><b>Results:</b></b></center>
<pre>
<u>Result message:</u>
 <b>New Account Transaction has been saved with ID: XXXX Status:OK</b>
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


## <b>Transaction STATUS</b>

<pre><pre>
This endpoint, based on the payload and some business rules, will return the status and additional information
for a specific transaction.
</pre>
<center><b>Actions:</b></center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
    "reference": "20190711",
    "channel": "XXXXXXX"
}
</pre>
<center>Result:</center>
<pre>
<u>Result message when transaction is found:</u> 
{
    "reference": "20190711",
    "amount": 500.38,
    "fee": 2.00,
    "status": "SETTLED"
}
</pre></pre>

## <b>Business Rules (A)</b>
<pre><pre>
Given: A transaction that is not stored in our system
When: I check the status from any channel
Then: The system returns the status 'INVALID'
</pre>
<center><b>Actions:</b></center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
    "reference": "20190711-1",
    "channel": "CLIENT"
}
</pre>
<center>Result:</center>
<pre>
<u>Result message when transaction not found:</u> 
{
    "reference": "20190711-1",
    "status": "INVALID"
}

</pre></pre>

## <b>Business Rules (B)</b>
<pre><pre>
Given: A transaction that is stored in our system
When: I check the status from CLIENT or ATM channel
And the transaction date is before today
Then: The system returns the status 'SETTLED'
And the amount substracting the fee.
</pre>
<center><b>Action #1:</b></center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
    "reference": "20190711",
    "channel": "CLIENT"
}
<u>Result message #1:</u> 
{
    "reference": "20190711",
    "amount": 502.38,
    "status": "SETTLED"
}
</pre>
<center><b>Action #2:</b></center>
<pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
    "reference": "20190711",
    "channel": "ATM"
}

<u>Result message #2:</u> 
{
    "reference": "20190711",
    "amount": 502.38,
    "status": "SETTLED"
}

</pre></pre>

## Business Rules (C)
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
{
    "reference": "20190711",
    "channel": "INTERNAL"
}
</pre>
<pre>
<u>Result message:</u> 
{
    "reference": "20190711",
    "amount": 500.38,
    "fee": 2.00,
    "status": "SETTLED"
}
</pre></pre>

## Business Rules (D)
Given: A transaction that is stored in our system
When: I check the status from CLIENT or ATM channel
And the transaction date is equals to today
Then: The system returns the status 'PENDING'
And the amount substracting the fee
<pre><pre>
Transaction:
{
	"reference":"20190811",
	"account_iban": "ES9820385778983000760234",
	"date":"2019-08-11T19:58:42.000Z",
	"amount":-300.20,
	"fee":2.00,
	"description":"Dept Store payment"
}
</pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
    "reference": "20190811",
    "channel": "ATM"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
    "reference": "20190811",
    "amount": 302.20,
    "status": "PENDING"
}
</pre></pre>


## Business Rules (E)
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
"reference":"20190811",
"channel":"INTERNAL"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
    "reference": "20190811",
    "amount": 300.20,
    "fee": 2.00,
    "status": "PENDING"
}
</pre></pre>

## Business Rules (F)
Given: A transaction that is stored in our system
When: I check the status from CLIENT channel
And the transaction date is greater than today
Then: The system returns the status 'FUTURE'
And the amount substracting the fee
<pre><pre>
Transaction:
{
	"reference":"20190816",
	"account_iban": "ES9820385778983000760234",
	"date":"2019-08-16T19:58:42.000Z",
	"amount":-250.40,
	"fee":2.00,
	"description":"Dept Store payment"
}
</pre>
POSTMAN REQUEST: <b>POST</b>
POSTMAN URL: http://localhost:8080/OrangeBookApp/transaction/getstatus
JSON Data:
{
"reference":"20190816",
"channel":"CLIENT"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
    "reference": "20190816",
    "amount": 252.40,
    "status": "FUTURE"
}
</pre></pre>

## Business Rules (G)
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
"reference":"20190816",
"channel":"ATM"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
    "reference": "20190816",
    "amount": 252.40,
    "status": "PENDING"
}
</pre></pre>

## Business Rules (H)
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
"reference":"20190816",
"channel":"INTERNAL"
}
</pre>

<pre>
<u>Result message when transaction not found:</u> 
{
    "reference": "20190816",
    "amount": 250.40,
    "fee": 2.00,
    "status": "FUTURE"
}
</pre></pre>

Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)