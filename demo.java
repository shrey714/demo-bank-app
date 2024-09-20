@PostMapping("/register")
public String registerUser(@RequestBody Customers customer) {
    // Check if mobile already exists (Optional step to prevent duplicate registrations)
    Customers existingCustomer = mongorepo.findByMobile(customer.getMobile());
    if (existingCustomer != null) {
        return "Mobile number already registered";
    }
    
    // Save the customer with name, mobile, and password
    mongorepo.save(customer);
    
    // Return the mobile number after successful registration
    return "Registered successfully with mobile: " + customer.getMobile();
}


@PostMapping("/login")
public String loginUser(@RequestBody Customers customer) {
    // Check if customer exists by mobile
    Customers existingCustomer = mongorepo.findByMobile(customer.getMobile());

    if (existingCustomer == null) {
        return "User with mobile number not found";
    }

    // Check if the provided password matches
    if (!existingCustomer.getPassword().equals(customer.getPassword())) {
        return "Incorrect password";
    }

    // Return mobile number on successful login
    return "Login successful, mobile: " + existingCustomer.getMobile();
}
