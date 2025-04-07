# Banking_Sector_APIs
 This project is a RESTful API designed to simulate a simple digital banking system. It allows users to perform core banking operations such as account creation, deposits, withdrawals, fund transfers, and transaction tracking. Additionally, it includes features for account security.
 ________________________________________
🔐 Authentication & Account Management
1.	@PostMapping("/CreateAccount")
Creates a new user bank account with required details like name, initial deposit, and PIN.
2.	@PostMapping("/enableAccount/{id}")
Enables a previously disabled account by ID, making it active again for transactions.
3.	@PostMapping("/disableAccount/{id}")
Disables an account by ID, temporarily preventing any transactions or activities.
________________________________________
💰 Transaction APIs
4.	@PostMapping("/Deposit/{id}")
Deposits a specified amount into the account identified by the given ID.
5.	@PostMapping("/Withdraw/{id}")
Withdraws a specified amount from the account, with PIN and balance checks.
6.	@PostMapping("/transferAmount")
Transfers money from one account to another with validations for balance and status.
7.	@GetMapping("/checkBalance/{id}")
Returns the current balance of the account with the given ID.
________________________________________
📜 Transaction History & Reporting
8.	@GetMapping("/getTransactionHistory/{id}")
Fetches the complete transaction history for the specified account.
9.	@GetMapping("/getTransactionofId/{id}")
Retrieves a specific transaction or a detailed list by transaction ID.
10.	@GetMapping("/getTransactionByDate/{id}")
Returns all transactions of an account made on a specific date.
11.	@GetMapping("/getTotalTransactionByDate")
Provides the total number and amount of all transactions made on a certain date (across all accounts).
12.	@GetMapping("/totalTransactionofaccount/{id}")
Calculates the total number and sum of all transactions performed by a specific account.
13.	@GetMapping("/getTransactionReverse/{id}")
Reverses a transaction by ID, possibly used for refund or error correction.
________________________________________
📊 Account Queries & Filters
14.	@GetMapping("/getaccountdetail_by_min_amount")
Retrieves account details for all users who have a balance greater than or equal to a specified minimum amount.
15.	@GetMapping("/getaccountbyname")
Fetches account details by searching user names or partial matches.
16.	@GetMapping("/getNoTransactionAccount")
Lists all accounts that have had no transactions since creation.
________________________________________
🔐 Security
17.	@GetMapping("/validatepin/{id}")
Validates the PIN for the account with the specified ID, typically before sensitive operations like withdrawal or transfer.
________________________________________

