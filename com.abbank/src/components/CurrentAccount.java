package components; // 1.2.2 Creation of the CurrentAccount and SavingsAccount

import javax.xml.bind.annotation.XmlRootElement;

//Inherits from Account class
@XmlRootElement(name = "currentAccount")
public class CurrentAccount extends Account {
	//Constructor
	public CurrentAccount(String label, Client client) {
		super(label, client);
		this.balance = 0.0; //set the initial balance to zero
	}
	//only attributes from Account class
}
