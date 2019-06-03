package Repository;

import Model.Account;
import Model.User;

import java.util.List;

public interface AccountRepository {

    // logica pt service
//    List<Account> findByUsername(String username);

    Account findById(Integer id);
    List<Account> findByClientId(Integer clientId);
    List<Account> findAll();
    void update(Account account);
    void deleteById(Integer id);
    void create(Account account);
}
