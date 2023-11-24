package bankmanagement; // 1.1.2 - Creation of main class for tests

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

		clientsArray = generateClients(3); //generating 3 clients

		displayClients(clientsArray);

		// 1.2.3 Creation of the tablea account
		//Loading accounts for the generated clients  
		Account[] accountsArray = loadAccounts(clientsArray);
		displayAccounts(accountsArray); // Displaying the loaded accounts

		//Create a map of accounts with account numbers as keys
		Map<Integer, Account> accountsMap = mapAccounts(accountsArray);
		//1.3.4 Creation of the flow array
		//Load the flow array
		Flow[] flows = createFlows(accountsArray);

		// 1.3.5 Updating accounts
		//Update account balances based on the flows
		Account.updateAccountBalances(flows, accountsMap, accountsArray);
		displayAccounts(accountsArray);

		//2 - Use of the java.nio.file.Path interface and try with resources
		// Path for the file
		Path filePath = Paths.get("C:\\Users\\licen\\git\\abbankrepository\\com.abbank\\src\\account_data.txt");

		try {
			// Writing account information to the file
			writeAccountInformation(filePath, accountsArray);
			System.out.println("Account information written to file successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//2.1 JSON file of flows
		Path jsonFilePath = Paths.get("C:\\Users\\licen\\git\\abbankrepository\\com.abbank\\src\\flows_data.json");

		try {
			List<String> lines = Files.readAllLines(jsonFilePath);
			String jsonContent = String.join("\n", lines);

			// Print the JSON content to verify if it has been read
			System.out.println("JSON Content:");
			System.out.println(jsonContent);

			// Gson code to deserialize JSON content into Flow objects
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Flow.class, new FlowDeserializer());
			Gson gson = gsonBuilder.create();

			Flow[] flowsFromJson = gson.fromJson(jsonContent, Flow[].class);

			if (flowsFromJson != null) {
				// Handle the obtained flows as needed
				for (Flow flow : flowsFromJson) {
					if (flow.getComment() != null) {
						System.out.println(flow.getComment());
					} 
				}
			} else {
				System.out.println("Failed to load flows from JSON file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
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

	// 1.3.5 Updating accounts
	// Method to create a map of accounts with account numbers as keys
	public static Map<Integer, Account> mapAccounts(Account[] accounts) {
		Map<Integer, Account> accountsMap = new HashMap<>();
		for (Account account : accounts) {
			accountsMap.put(account.getAccountNumber(), account);
		}
		return accountsMap;
	}

	// Creation of the flow array
	public static Flow[] createFlows(Account[] accounts) {
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime adjustedDate = currentDate.plusDays(2); // Adding 2 days to the current date

		Flow[] flows = new Flow[accounts.length * 2 + 2];

		int flowIndex = 0;

		// Generate Debit flow for the first account
		flows[flowIndex++] = new Debit("Debit of 50€ from account n°" + accounts[0].getAccountNumber(),
				accounts[0].getAccountNumber(), 50.0, accounts[0].getAccountNumber(), false, adjustedDate);

		// Generate Credit flows for all current accounts
		for (Account account : accounts) {
			if (account instanceof CurrentAccount) {
				flows[flowIndex++] = new Credit("Credit of 100.50€ on current account " + account.getAccountNumber(),
						account.getAccountNumber(), 100.50, account.getAccountNumber(), true, adjustedDate);
			}
		}

		// Generate Credit flows for all savings accounts
		for (Account account : accounts) {
			if (account instanceof SavingsAccount) {
				flows[flowIndex++] = new Credit("Credit of 1500€ on savings account " + account.getAccountNumber(),
						account.getAccountNumber(), 1500.0, account.getAccountNumber(), true, adjustedDate);
			}
		}


		// Generate Transfer flow between first and second accounts
		flows[flowIndex++] = new Transfer("Transfer of 50€ from account n°" + accounts[0].getAccountNumber() +
				" to account n°" + accounts[1].getAccountNumber(), accounts[0].getAccountNumber(),
				50.0, accounts[1].getAccountNumber(), true, accounts[0].getAccountNumber(), adjustedDate);

		// Displaying information about the created flows
		for (Flow flow : flows) {
			if (flow != null) {
				System.out.println(flow);
			}
		}


		return flows;
	}

	// Method to update account balances based on an array of Flows and a Map of accounts
	public static void updateAccountBalances(Flow[] flows, Map<Integer, Account> accounts) {
		// Process each flow and update corresponding account balances
		for (Flow flow : flows) {
			if (flow != null) {
				Account account = accounts.get(flow.getTargetAccountNumber());
				if (account != null) {
					account.setBalance(flow);
					// Update the account in the map with the modified balance
					accounts.put(account.getAccountNumber(), account);
				}
			}
		}
		checkForNegativeBalances(accounts);
	}

	// Method to check for accounts with negative balances
	public static void checkForNegativeBalances(Map<Integer, Account> accounts) {
		boolean foundNegativeBalance = false;

		for (Account account : accounts.values()) {
			if (account.getBalance() < 0) {
				System.out.println("Account " + account.getAccountNumber() + " has a negative balance: " + account.getBalance());
				foundNegativeBalance = true;
			}
		}

		if (!foundNegativeBalance) {
			System.out.println("All accounts have non-negative balances.");
		}
	}

	//2 - Use of the java.nio.file.Path interface and try with resources
	// Method to write account information to the file using try-with-resources
	public static void writeAccountInformation(Path filePath, Account[] accounts) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
			for (Account account : accounts) {
				writer.write(account.toString());
				writer.newLine();
			}
		}
	}

	//2.1 JSON file of flows    
	public static Flow[] loadFlowsFromJson(Gson gson, String filePath) {
		Flow[] flows = null;

		try {
			FileReader reader = new FileReader(filePath);
			flows = gson.fromJson(reader, Flow[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return flows;
	}

}
