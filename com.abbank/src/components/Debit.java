package components;

import java.time.LocalDateTime;

public class Debit extends Flow {

	public Debit (String comment, int identifier, double amount, 
			int targetAccountNumber, boolean effect, LocalDateTime dateOfFlow) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
	}
}
