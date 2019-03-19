package Service.Implementation;

import Model.Client;
import Repository.Implementation.ClientRepositoryImplementation;
import Service.ClientService;

import java.util.List;

public class ClientServiceImplementation implements ClientService {

    private final ClientRepositoryImplementation cri;


    public ClientServiceImplementation(ClientRepositoryImplementation cri) {
        this.cri = cri;
    }

    // return 1 => CARDID EXISTA DEJA IN BAZA DE DATE
    // return 2 => CNP EXISTA DEJA IN BAZA DE DATE
    // return > 5 => VALIDATE()
    public int save(Client client) {
        List<Client> list = cri.findAll();

        for (int i = 0; i <list.size(); i++) {
            Client c = list.get(i);
            if (client.getCardId().equals(c.getCardId()))
                return 1;
            if (client.getCNP().equals(c.getCNP()))
                return 2;
        }

        int valid = this.validate(client);
        if (valid != 0)
            return valid;
        else
            cri.create(client);
        return 0;
    }

    // return 1 => CARDID EXISTA DEJA IN BAZA DE DATE
    // return 2 => CNP EXISTA DEJA IN BAZA DE DATE
    // return > 5 => VALDIATE
    public int update(String cnp, Client newClient) {
        Client client = cri.findByCNP(cnp);
        List<Client> list = cri.findAll();

        for (int i = 0; i <list.size(); i++) {
            Client c = list.get(i);
            if (newClient.getCardId().equals(c.getCardId()))
                return 1;
        }

        if (newClient.getFirstName().equals(""))
            newClient.setFirstName(client.getFirstName());
        if (newClient.getLastName().equals(""))
            newClient.setLastName(client.getLastName());
        if (newClient.getCNP().equals(""))
            newClient.setCNP(client.getCNP());
        if (newClient.getCardId().equals(""))
            newClient.setCardId(client.getCardId());
        if (newClient.getAddress().equals(""))
            newClient.setAddress(client.getAddress());

        int valid = this.validate(newClient);
        if (valid == 0)
            cri.update(newClient);
        else
            return valid;

        return 0;
    }

    // return 1 => CLIENTUL NU A FOST GASIT
    public int delete(String cnp) {
        Client client = cri.findByCNP(cnp);

        if (client == null)
            return 1;
        else
            cri.deleteByCNP(cnp);
        return 0;
    }

    // return null => NU EXISTA INCA CLIENTI IN BAZA DE DATE
    public List<Client> getAll() {
        List<Client> clientsList = cri.findAll();
        return clientsList;
    }

    public Client findClientById(Integer id) {
        Client client = cri.findById(id);
        return client;
    }

    public Client findClientByCNP(String cnp) {
        if (cnp.length() != 13)
            return null;
        for (int i = 0; i < cnp.length(); i++)
            if (cnp.charAt(i) < '0' || cnp.charAt(i) > '9')
                return null;

        Client client = cri.findByCNP(cnp);
        return client;
    }

    // return 5 => FIRST NAME INCORECT
    // return 6 => LAST NAME INCORECT
    // return 7 => CARD ID INCORECT
    // return 8 => CNP INCORECT
    private int validate(Client client) {
        // firstName => 5
        String firstName = client.getFirstName();
        if (firstName.equals(""))
            return 5;
        if (firstName.charAt(0) < 'A' || firstName.charAt(0) > 'Z' || firstName.length() < 3)
            return 5;
        for (int i = 1; i < firstName.length(); i++)
            if (firstName.charAt(i) < 'a' || firstName.charAt(i) > 'z')
                return 5;

        // lastName => 6
        String lastName = client.getLastName();
        if (lastName.equals(""))
            return 6;
        if (lastName.charAt(0) < 'A' || lastName.charAt(0) > 'Z' || lastName.length() < 3)
            return 6;
        for (int i = 1; i < lastName.length(); i++)
            if (lastName.charAt(i) < 'a' || lastName.charAt(i) > 'z')
                return 6;

        // cardId => 7
        String cardId = client.getCardId();
        if (cardId.equals(""))
            return 7;
        if (cardId.length() != 6)
            return 7;
        for (int i = 0; i < cardId.length(); i++)
            if (cardId.charAt(i) < '0' || cardId.charAt(i) > '9')
                return 7;

        // CNP => 8
        String cnp = client.getCNP();
        if (cnp.equals(""))
            return 8;
        if (cnp.length() != 13)
            return 8;
        for (int i = 0; i < cnp.length(); i++)
            if (cnp.charAt(i) < '0' || cnp.charAt(i) > '9')
                return 8;

        return 0;
    }

}
