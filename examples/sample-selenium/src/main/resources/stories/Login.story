Narrative:
In order to use the bank services
As a user
I want to authenticate in the system

Scenario: User must be able to login in Sample Bank application
Given User is on Login Page
When User login as <username> with password <password>
Then User <username> must be successful logged in
Examples:
|username|password|
|admin   |admin   |