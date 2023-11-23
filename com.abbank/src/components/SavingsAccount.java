package components; // 1.2.2 Creation of the CurrentAccount and SavingsAccount

//Inherits from Account class
public class SavingsAccount extends Account {
	//Constructor
	public SavingsAccount(String label, Client client) {
		super(label, client);
		this.balance = 0.0; //set the initial balance to zero
	}
}
