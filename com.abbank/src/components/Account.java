package components; // 1.1.1 - Creation of the Account class

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

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
	// 1.3.5 Updating accounts
	public void setBalance(Flow flow) {
		double amount = flow.getAmount();
		boolean effect = flow.isEffect();

		
		//Process the flow based on its type
		if (flow instanceof Credit && effect) {
			this.balance += amount; //adds amount to the balance for credit
		} else if (flow instanceof Debit) {
			this.balance -=amount; //subtracts amount from the balance for debit
		} else if (flow instanceof Transfer){
			Transfer transfer = (Transfer) flow;
			int targetAccountNumber = transfer.getTargetAccountNumber();
			int issuingAccountNumber = transfer.getIssuingAccountNumber();
			double transferAmount = transfer.getAmount();
			
		    // Check if the current account is the target or issuing account
		    if (this.accountNumber == targetAccountNumber && effect) {
		        // Receive funds as target account
		        this.balance += transferAmount;
		    } else if (this.accountNumber == issuingAccountNumber && !effect) {
		        // Deduct funds as issuing account
		        this.balance -= transferAmount;
		    }
		}
	}

	//Method to update account balances based on an array of Flows and a Map of accounts
	public static void updateAccountBalances(Flow[] flows, Map<Integer, Account> accounts, Account[] accountsArray) {
	//Process each flow and update corresponding account balances
		for(Flow flow : flows) {
			if(flow != null) {
				Account account = accounts.get(flow.getTargetAccountNumber());
				if (account != null) {
					account.setBalance(flow);
				}
			}
		}
		checkForNegativeBalances(accounts);			
	}
	
	//Check for accounts with negative balances using Optional, Predicate and Stream
	public static void checkForNegativeBalances(Map<Integer, Account> accounts) {
		Predicate<Account> isNegativeBalance = x -> x.getBalance() < 0;
		Optional<Account> negativeBalanceAccount = accounts.values().stream()
				.filter(isNegativeBalance)
				.findFirst();
		
		//Display message for accounts with negative balance
		negativeBalanceAccount.ifPresent(x -> System.out.println("Account " + x.getAccountNumber() + " has negative balance!"));
		
	    if (!negativeBalanceAccount.isPresent()) {
	        System.out.println("All accounts have non-negative balance.");
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
