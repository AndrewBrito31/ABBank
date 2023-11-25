package bankmanagement; //2.2 XML file of account

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import components.Account;

@XmlRootElement(name = "accounts")
public class AccountWrapper {
    private List<Account> accounts;

    @XmlElement(name = "account")
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
