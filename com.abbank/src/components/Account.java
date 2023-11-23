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

	// Method to update balance based on a Flow object 
	// 1.3.2 Creation of the Flow class(updates an account balance to accept a Flow object)
	public void setBalance(Flow flow) {
		double amount = flow.getAmount();
		boolean effect = flow.isEffect();

		//Determine how to update the balance based on the Flow object
		if (effect) {
			this.balance += amount; //For credit or transfer
		} else {
			this.balance -= amount; //For debit
		}
	}

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
