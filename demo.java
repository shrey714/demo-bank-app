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
