package components; // 1.1.1 - Creation of the Account class

public abstract class Account {
	 // Attributes (protected)
	protected String label;
	protected Double balance;
	protected int accountNumber;
	protected Client client; 
	
	private static int lastAssignedAccountNumber = 0; // Static variable to track the last assigned account number
	
    // Constructor for the Account class
	protected Account(String label, Client client) {
		this.label = label;
		this.client = client;
		this.accountNumber = ++lastAssignedAccountNumber;
	}
	
	// Accessors and Mutators
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public double getBalance() {
		return balance;
	}
	
	// Abstract method to set the balance
	public abstract void setBalance(double amount);
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	//toString() method to format the complete account information
	@Override
	public String toString() {
		return "Account number: " + accountNumber + ", Label: " + label + ", Balance: " + balance + ", Client: " + client; 
	}
}
