package components; //1.3.3 Creation of the Transfert, Credit, Debit classes

import java.time.LocalDateTime;

public class Transfer extends Flow {
	private int issuingAccountNumber; //additional attribute for transfer
	
	public Transfer (String comment, int identifier, double amount, 
			int targetAccountNumber, boolean effect, LocalDateTime dateOfFlow) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
		this.issuingAccountNumber = issuingAccountNumber;
	}
}
