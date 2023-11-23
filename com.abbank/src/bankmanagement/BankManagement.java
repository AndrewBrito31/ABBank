package bankmanagement; // 1.1.2 - Creation of main class for tests

import java.util.Arrays;

import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;
import components.Account;

public class BankManagement {
	public static void main(String[] args) {
		//Declare and array of clients
		Client[] clientsArray;
		
		clientsArray = generateClients(10); //generating 3 clients
		
		displayClients(clientsArray);
		
		// 1.2.3 Creation of the tablea account
		//Loading accounts for the generated clients  
		Account[] accountsArray = loadAccounts(clientsArray);
		displayAccounts(accountsArray); // Displaying the loaded accounts
	}
	
	//Method to generate clients based on the desired number
	public static Client[] generateClients(int numberOfClients) {
		Client[] clients = new Client[numberOfClients];
		for(int i = 0; i < numberOfClients; i++) {
			String firstName = "firstName" + (i + 1);
			String name = "name" + (i + 1);
			clients[i] = new Client(firstName, name);
		}
		
		return clients;
	}
	
	//Method to display the contents of the clients array
	public static void displayClients(Client [] clients) {
		for (Client client : clients) {
			System.out.println(client); //Invokes the toString() method of the client
		}
		
	}
	
	// 1.2.3 Creation of the tablea account
	//Method to load accounts for clients
	public static Account[] loadAccounts(Client[] clients) {
		return Arrays.stream(clients)
				.flatMap(client -> Arrays.stream(new Account[] {new SavingsAccount("Savings", client),
																new CurrentAccount("Current", client)}))
				.toArray(Account[]::new);
	}
	
	//Method to display the contents of the table
	public static void displayAccounts(Account[] accounts) {
		Arrays.stream(accounts).forEach(System.out::println);
	}
}
