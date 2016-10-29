# LimitsChecker
Test quiz, given during employment to the bank.

In solution it is important to show good experience using OOP and Java Core, this is the main points we are estimating.
In the task expecting some abstract system of payment processing. When payment received by the system it is passed to the processing.
Payments accepting by some amount, from an account to a service.

The task is to add subsystem of payment limits to prevent fraud.
The limits may be tuning by all fields.
Just for example:

1. Not more $5000, from 9:00AM to 11:00PM by same service
2. Not more $2000, per day by same payments. (Same payment means the same account and service)
3. Not more $3000, per hour by same service.
4. Not more 20 same payments per day.
5. Not more 30 payments not more $4000 from 10:00AM to 17:00AM per service.
6. Not more 10 payments not more $3000 per two hours by same account.

In case of exceed one of limits payment have to be marked as "Submit required". If payment passes limits it should be marked as "Ready to process".
Solution must have at least one unit test for every limit, so primitive payment acceptance system must be implemented.
TO BE CONTINUED...

