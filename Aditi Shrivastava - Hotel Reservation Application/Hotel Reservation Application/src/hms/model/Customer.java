package hms.model;
import java.util.regex.Pattern;
public class Customer {
	String firstName;
	String lastName;
	String email;
	public Customer(String fName, String lName, String mail)
	{
		firstName = fName;
		lastName = lName;
		email = mail;
		boolean e = emailCheck(email);
		System.out.println(e);
		if (e==false)
		{throw new IllegalArgumentException();}
	}
	
	public boolean emailCheck(String email)
	{
		  String emailRegex = "^(.+)@(.+).(.+)$";
	      Pattern pattern = Pattern.compile(emailRegex);
	      return pattern.matcher(email).matches();
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getEmail()
	{
		return email;
	}
	@Override
	public String toString()
	{
		return "First Name: "+firstName+"  Last Name: "+lastName+"  Email: "+email;
	}
}
