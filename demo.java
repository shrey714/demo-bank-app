1. Modify the Customers Model to Handle Multiple Bank Accounts
We will introduce a new BankAccount class and modify the Customers model to store a list of BankAccount objects.

Create a BankAccount Model:
java
Copy code
package com.bootcat.BootCat.models;

public class BankAccount {
    private String accountNumber;
    private int balance;

    // Constructor
    public BankAccount(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
Modify the Customers Model:
Add a list of BankAccount objects to the Customers model to represent multiple bank accounts:

java
Copy code
package com.bootcat.BootCat.models;

import java.util.List;

public class Customers {
    private int id;
    private String name;
    private String mobile;
    private String password;
    private int balance; // General balance or can be removed
    private List<BankAccount> accounts;  // New field for multiple accounts

    // Constructor, getters, setters
    public Customers() {
        // Initialize the accounts list if not already done
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    // Add other getters, setters, constructors...
}
2. API to Add a Bank Account for a User
Now, we'll create a new endpoint to add a bank account for a user based on their mobile number. It will take the account number and balance as input.

Update BootCatController to Add a Bank Account:
java
Copy code
import java.util.ArrayList;
import java.util.List;

@PostMapping("/addAccount/{mobile}")
public String addBankAccount(@PathVariable String mobile, @RequestBody BankAccount newAccount) {
    // Find customer by mobile
    Customers customer = mongorepo.findByMobile(mobile);

    if (customer == null) {
        return "Customer with mobile number not found";
    }

    // Get existing accounts, if null initialize it
    List<BankAccount> accounts = customer.getAccounts();
    if (accounts == null) {
        accounts = new ArrayList<>();
    }

    // Add the new bank account
    accounts.add(newAccount);
    customer.setAccounts(accounts); // Update customer with new list of accounts

    // Save updated customer to the database
    mongorepo.save(customer);

    return "Bank account added successfully for mobile: " + mobile;
}
3. Ensure the MongoRepo Interface Has findByMobile
We already have this in place in your MongoRepo:

java
Copy code
@Repository
public interface MongoRepo extends MongoRepository<Customers, Integer> {
    Customers findByMobile(String mobile);  // To find the customer by mobile
}
4. Testing the API
To test this functionality:

First, make sure you have registered a user with a mobile number.
Use the /addAccount/{mobile} API, providing the accountNumber and balance as the BankAccount payload in the request body.
Example Request:
http
Copy code
POST /addAccount/9876543210
Content-Type: application/json

{
    "accountNumber": "1234567890",
    "balance": 5000
}
Response:
rust
Copy code
Bank account added successfully for mobile: 9876543210
This structure allows a user to have multiple bank accounts, which can be added dynamically through this API.

Let me know if you'd like to further refine this or add additional features!
