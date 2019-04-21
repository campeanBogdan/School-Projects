package Model.Service;

import Model.Model.Book;
import Model.Model.Client;

import java.util.List;

public interface ClientService {

    void save(Client client);
    void delete(String firstName, String lastName);
    List<Book> getClientBooks(String firstName, String lastName);
    void deleteShoppingBasket(String firstName, String lastName);
    List<Client> getAllClients();
    void update(String firstName, String lastName, Client newClient);

}
