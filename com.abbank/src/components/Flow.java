package components; // 1.3.2 Creation of the Flow class

import java.time.LocalDateTime;

import com.google.gson.Gson;

public abstract class Flow {
	private String comment;
	private int identifier;
	private double amount;
	private int targetAccountNumber;
	private boolean effect;
	private LocalDateTime dateOfFlow;
	
	

	//Constructor
	protected Flow(String comment, int identifier, double amount, 
			int targetAccountNumber, boolean effect, LocalDateTime dateOfFlow) {
		this.comment = comment;
		this.identifier = identifier;
		this.amount = amount;
		this.targetAccountNumber = targetAccountNumber;
		this.effect = effect;
		this.dateOfFlow = dateOfFlow;
	}
	
	//Accessors and Mutators
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}

	public boolean isEffect() {
		return effect;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public LocalDateTime getDateOfFlow() {
		return dateOfFlow;
	}

	public void setDateOfFlow(LocalDateTime dateOfFlow) {
		this.dateOfFlow = dateOfFlow;
	}
}
