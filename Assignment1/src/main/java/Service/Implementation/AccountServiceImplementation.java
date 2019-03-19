package Service.Implementation;

import Model.Account;
import Model.Client;
import Repository.Implementation.AccountRepositoryImplementation;
import Repository.Implementation.ClientRepositoryImplementation;
import Service.AccountService;

import java.util.List;

public class AccountServiceImplementation implements AccountService {

    private final AccountRepositoryImplementation ari;


    public AccountServiceImplementation(AccountRepositoryImplementation ari) {
        this.ari = ari;
    }

    public int save(Account account) {
        if (validate(account) == 0)
            ari.create(account);
        else
            return 1;
        return 0;
    }

    public Account findById(Integer id) {
        Account account = ari.findById(id);
        return account;
    }

    public int deleteByClientId(Integer clientId) {
        ari.deleteAllByClientId(clientId);
        return 0;
    }

    // return 1 => NU EXISTA CONTUL ALES
    // return > 6 => VALIDATE()
    public int update(Account account) {
        Account oldAccount = ari.findById(account.getId());

        if (oldAccount == null)
            return 1;

        if (account.getType().equals(""))
            account.setType(oldAccount.getType());
        if (account.getMoney().equals(oldAccount.getMoney()))
            account.setMoney(oldAccount.getMoney());
        if (account.getCreationDate().equals(""))
            account.setCreationDate(oldAccount.getCreationDate());
        account.setClientId(oldAccount.getClientId());

        int valid = this.validate(account);
        if (valid != 0)
            return valid;
        else
            ari.update(account);
        return 0;
    }

    // return 1 => SUMA INCORECTA
    // return 2 => SUMA INSUFICIENTA
    // return 3 => NU EXISTA CONTUL 1
    // return 4 => NU EXISTA CONRUL 2
    public int updateSum(Integer sum, Integer accountId1, Integer accountId2) {
        Account a1 = ari.findById(accountId1);
        Account a2 = ari.findById(accountId2);

        if (a1 == null)
            return 3;
        if (a2 == null)
            return 4;

        if (sum < 0)
            return 1;

        if (a1.getMoney() < sum)
            return 2;

        a1.setMoney(a1.getMoney() - sum);
        ari.update(a1);
        a2.setMoney(a2.getMoney() + sum);
        ari.update(a2);
        return 0;
    }

    // return 1 => NU EXISTA CONTUL
    public int delete(Integer id) {
        Account account = ari.findById(id);

        if (account != null)
            ari.deleteById(id);
        else
            return 1;

        return 0;
    }

    // clientId => Client.id
    public List<Account> getAll(Integer clientId) {
        List<Account> accountsList = ari.findByClientId(clientId);
        return accountsList;
    }

    public List<Account> getAll() {
        List<Account> accountsList = ari.findAll();
        return accountsList;
    }

    // return 5 => SUMA NEGATIVA
    private int validate(Account account) {
        if (account.getMoney() < 0)
            return 5;

        return 0;
    }
}
