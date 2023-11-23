package components; // 1.2.2 Creation of the CurrentAccount and SavingsAccount

//Inherits from Account class
public class CurrentAccount extends Account {
	//Constructor
	public CurrentAccount(String label, Client client) {
		super(label, client);
		this.balance = 0.0; //set the initial balance to zero
	}
	//only attributes from Account class
    
	// Implementation of the abstract method from the Account class
    @Override
    public void setBalance(double amount) {
    	this.balance = amount;
    }
}
