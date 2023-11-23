package bankmanagement; //1.3.1 Adaptation of the table of accounts

import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

import components.Account;

public class AccountTable {
	//Method to create a Hashtable of accounts based on their account number
	public static Hashtable<Integer, Account> createAccountHashtable(Account[]accounts) {
		Hashtable<Integer, Account> accountHashtable = new Hashtable<>();
		for (Account account : accounts) {
			accountHashtable.put(account.getAccountNumber(), account);
		}
		
		return accountHashtable;
	}
	
	//Method to display the Hashtable in ascending order based on balance
	public static void displayHashtableAscendingByBalance(Hashtable<Integer, Account> accountHashtable) {
		Map<Integer, Account> sortedMap = new TreeMap<>(accountHashtable);
		sortedMap.entrySet().stream()
			.sorted(Map.Entry.comparingByValue(Comparator.comparingDouble(Account::getBalance)))
			.forEach(entry -> System.out.println("Account Number: " + entry.getKey() + ", Balance: " + entry.getValue().getBalance()));
	}
}
