package Service;

import Model.Client;

import java.util.List;

public interface ClientService {

    int save(Client client);
    int update(String cnp, Client client);
    int delete(String cnp);
    List<Client> getAll();
    Client findClientByCNP(String cnp);
}
