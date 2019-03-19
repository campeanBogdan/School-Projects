package Service;

import Model.Account;
import Model.Client;

import java.util.List;

public interface AccountService {

    int save(Account account);
    int update(Account account);
    int delete(Integer id);
    List<Account> getAll();
    List<Account> getAll(Integer id);
}
