package components; // Exercise 1.1.1 - Creation of the Client class

public class Client {
	//Attributes (private)
	private String firstName;
	private String lastName;
	private int clientNumber;
	private static int lastAssignedClientNumber = 1; // Static variable to track the last assigned client number

	
	// Constructor for the Client class
	public Client(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.clientNumber = ++lastAssignedClientNumber;
	}

	// Accessors and Mutators
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}
	
	//toString() method to format the complete client information
	@Override
	public String toString() {
		return "Client Number: " + clientNumber + ", Name: " + firstName + " " + lastName;
	}
}
