package Model.Repostiroy;

import Model.Model.Client;

import java.util.List;

public interface ClientRepository {

    Client findById(Integer id);
    List<Client> findByNameAndAddress(String firstName, String lastName, String address);
    void update(String oldFirstName, String oldLastName, Client newClient);
    void deleteByName(String firstName, String lastName);
    void insert(Client client);
    List<Client> findAll();
}
