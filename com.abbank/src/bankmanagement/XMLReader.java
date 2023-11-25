package bankmanagement; //2.2 XML file of account

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import components.Account;

public class XMLReader {
    public static void main(String[] args) {
        try {
            File file = new File("path/to/your/accounts.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(AccountWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            AccountWrapper accountWrapper = (AccountWrapper) jaxbUnmarshaller.unmarshal(file);

            // Access the list of Account objects inside AccountWrapper
            if (accountWrapper != null) {
                // Assuming getAccounts() returns a list of Account objects
                List<Account> accountList = accountWrapper.getAccounts();
                for (Account account : accountList) {
                    // Process each account
                    System.out.println(account);
                }
            } else {
                System.out.println("AccountWrapper object is null.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}


