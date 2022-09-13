package hms.service;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;
import hms.model.Customer;

public class CustomerService {
public static final Map<String, Customer> customerList = new HashMap<String,Customer>();

	public static void addCustomer (String email, String fName, String lName)
	{
		Customer c = new Customer(fName,lName,email);
		customerList.put(email,c);
	}

	public static Customer getCustomer(String cEmail)
	{
		return customerList.get(cEmail);
	}

	public static Collection<Customer> getAllCustomers()
	{
		return customerList.values();
	}
}
