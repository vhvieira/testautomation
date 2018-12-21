Account Transactions Story

Narrative:
In order to ensure that the account service API is working
As a consumer/client
I want to perform and check account operations

Scenario: The bank user should be able to make transfers
Given an account for cpf number 06444870976 exists in the system
And an account for cpf number 04573677925 exists in the system
When a deposit of 500.00 is made for the account 06444870976
And a transfer of 300.00 is made from the account 06444870976 to the account 04573677925
Then the response code should be INF001 and the message should be Operation completed with success
When the account 06444870976 informations are requested
Then an account should exist and it should have the balance 200.00
When the account 04573677925 informations are requested
Then an account should exist and it should have the balance 300.00
