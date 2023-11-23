package components; // 1.1.1 - Creation of the Client class

public class Client {
	//Attributes (private)
	private String firstName;
	private String name;
	private int clientNumber;
	private static int lastAssignedClientNumber = 0; // Static variable to track the last assigned client number

	
	// Constructor for the Client class
	public Client(String firstName, String name) {
		this.firstName = firstName;
		this.name = name;
		this.clientNumber = ++lastAssignedClientNumber;
	}

	// Accessors and Mutators
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setLastName(String name) {
		this.name = name;
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
		return "Client Number: " + clientNumber + ", Name: " + firstName + " " + name;
	}
}
