package Repository.Implementation;

import Model.Client;
import Repository.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ClientRepositoryImplementation implements ClientRepository {

    private final JDBConnection jdbConnection;


    public ClientRepositoryImplementation(JDBConnection jdbConnection) {
        this.jdbConnection = jdbConnection;
    }

    public Client findByCNP(String CNP) {
        Connection conn = jdbConnection.getConnection();
        Client client = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`client` WHERE Client.CNP=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, CNP);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setCardId(rs.getString("cardId"));
                client.setCNP(rs.getString("CNP"));
                client.setAddress(rs.getString("address"));

                return client;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client findByName(String firstName, String lastName) {
        Connection conn = jdbConnection.getConnection();
        Client client = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`client` WHERE Client.firstName=? AND Client.lastName=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setCardId(rs.getString("cardId"));
                client.setCNP(rs.getString("CNP"));
                client.setAddress(rs.getString("address"));

                return client;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client findById(Integer id) {
        Connection conn = jdbConnection.getConnection();
        Client client = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`client` WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setCardId(rs.getString("cardId"));
                client.setCNP(rs.getString("CNP"));
                client.setAddress(rs.getString("address"));

                return client;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> findAll() {
        Connection conn = jdbConnection.getConnection();
        List<Client> listClient = new LinkedList<Client>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`client`", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setCardId(rs.getString("cardId"));
                client.setCNP(rs.getString("CNP"));
                client.setAddress(rs.getString("address"));

                listClient.add(client);
            }

            return listClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Client client) {
        Connection conn = jdbConnection.getConnection();
        try {
            //PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`client` WHERE Client.id=?", Statement.RETURN_GENERATED_KEYS);
            String sql = "UPDATE Assignment1.`client` SET " +
                        "Client.firstName = '" + client.getFirstName() + "', " +
                        "Client.lastName = '" + client.getLastName() + "', " +
                        "Client.cardId = '" + client.getCardId() + "', " +
                        "Client.CNP = '" + client.getCNP() + "', " +
                        "Client.address = '" + client.getAddress() +
                        "' WHERE cnp=?";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            String cnp = client.getCNP();
            ps.setString(1,cnp);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByCNP(String cnp) {
        Connection conn = jdbConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Assignment1.`client` WHERE Client.cnp=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cnp);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Client client) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "INSERT INTO Assignment1.`client`(firstName, " +
                            "lastName, cardId, CNP, address) " +
                            " VALUES ('" +
                            client.getFirstName() + "', '" +
                            client.getLastName() + "', '" +
                            client.getCardId() + "', '" +
                            client.getCNP() + "', '" +
                            client.getAddress() + "')";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
