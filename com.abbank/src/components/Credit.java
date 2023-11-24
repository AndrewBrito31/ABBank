package components;

import java.time.LocalDateTime;

public class Credit extends Flow {
	
	public Credit (String comment, int identifier, double amount, 
			int targetAccountNumber, boolean effect, LocalDateTime dateOfFlow) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
	}
	
	@Override
	public String toString() {
	    return "Credit: " + getComment() + ", Amount: " + getAmount() + ", Target Account: " + getTargetAccountNumber() + ", Date: " + getDateOfFlow();
	}
}
