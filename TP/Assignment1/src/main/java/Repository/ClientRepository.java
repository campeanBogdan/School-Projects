package Repository;

import Model.Client;

import java.util.List;

public interface ClientRepository {

    //  Normal user
    Client findByName(String firstName, String lastName);
    Client findById(Integer id);
    List<Client> findAll();
    void update(Client client);
    void deleteByCNP(String cnp);
    void create(Client client);
    Client findByCNP(String CNP);
}
