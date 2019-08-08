## Code Challenge - WEB TESTS
The goal of this code challenge is to create a microservice using Java and any framework that you think it is
appropriate.</br>
Return to Main: [README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## WEB TESTS
<pre>
<b><u>OrangeBooktestApp.war</u></b> must be running and accepting reques.
</pre>
Four WEB test has been develped:
- <b>Accounts:</b>
Posibility to enter new accounts and delete. Update will be ready soon.
- <b>Transactions:</b>
List all transactions in table AccountTransaction, with all attibutes.
- <b>Transactions by IBAN: </b>
List all transaction filterd by IBAN.
- <b>Transactions by REFERENCE: </b>
List all transaction filterd by REFERENCE.
### Accounts
<pre>
User can Insert and Delete Accounts. Update will be available soon.
URL: http://localhost:8080/OrangeBookTestApp/accounts
![Screenshot] (https://github.com/luisamesty/orangebanktest/blob/master/OrangeBookWiki/images/accounts.png) accounts.png
<p align="center">
  <img src="https://github.com/luisamesty/orangebanktest/blob/master/OrangeBookWiki/images/accounts.png" width="550" title="hover text">
</p>
</pre>
### Transacctions
<pre>
Use can visualize al transaction on table AccountTransaction.
URL: http://localhost:8080/OrangeBookTestApp/transactions
![Screenshot] (https://github.com/luisamesty/orangebanktest/blob/master/OrangeBookWiki/images/transactions.png) transactions.png
<p align="center">
  <img src="https://github.com/luisamesty/orangebanktest/blob/master/OrangeBookWiki/images/transactions.png" width="550" title="hover text">
</p>
</pre>
### Transactions by IBAN
<pre>
Use can visualize al transaction on table AccountTransaction.
Filtered by IBAN Account number.
URL: http://localhost:8080/OrangeBookTestApp/tr_search
![Screenshot] (https://github.com/luisamesty/orangebanktest/blob/master/OrangeBookWiki/images/transactions_by_IBAN.png) transactions_by_IBAN.png
<p align="center">
  <img src="https://github.com/luisamesty/orangebanktest/blob/master/OrangeBookWiki/images/transactions_by_IBAN.png" width="550" title="hover text">
</p>
</pre>
### Transactions by REFERENCE
<pre>
Use can visualize al transaction on table AccountTransaction.
Filtered by REFERENCE number.
URL: http://localhost:8080/OrangeBookTestApp/tr_search
![Screenshot] (https://github.com/luisamesty/orangebanktest/blob/master/OrangeBookWiki/images/transactions_by_REF.png) transactions_by_REF.png
<p align="center">
  <img src="https://github.com/luisamesty/orangebanktest/blob/master/OrangeBookWiki/images/transactions_by_REF.png" width="550" title="hover text">
</p>
</pre>
Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)