package components; // 1.2.2 Creation of the CurrentAccount and SavingsAccount

//Inherits from Account class
public class SavingsAccount extends Account {
	//Constructor
	public SavingsAccount(String label, Client client) {
		super(label, client);
	}
	//only attributes from Account class
  
	// Implementation of the abstract method from the Account class
    @Override
    public void setBalance(double amount) {
    	this.balance = amount;
    }
}
