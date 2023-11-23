package clienttests; // 1.1.2 - Creation of main class for tests

import components.Client;

public class ClientTests {
	public static void main(String[] args) {
		//Declare and array of clients
		Client[] clientsArray;
		
		clientsArray = generateClients(10); //generating 3 clients
		
		displayClients(clientsArray);
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
}
