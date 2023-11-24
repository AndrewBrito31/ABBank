package bankmanagement; // 1.1.2 - Creation of main class for tests

import java.time.LocalDateTime;
import java.util.Arrays;

import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfer;
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
		
		//1.3.4 Creation of the flow array
		//Load the flow array
		Flow[] flows = new Flow[4];
		
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime adjustedDate = currentDate.plusDays(2); // Adding 2 days to the current date

		flows[0] = new Debit("Debit of 50€ from account n°1", 1, 50.0, 1, false, adjustedDate);
		flows[1] = new Credit("Credit of 100.50€ on all current accounts", 2, 100.50, -1, true, adjustedDate);
		flows[2] = new Credit("Credit of 1500€ on all savings accounts", 3, 1500.0, -1, true, adjustedDate);
		flows[3] = new Transfer("Transfer of 50€ from account n°1 to account n°2", 4, 50.0, 2, true, adjustedDate);

	    // Displaying information about the created flows
	    for (Flow flow : flows) {
	        System.out.println(flow);
	    }
		
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
