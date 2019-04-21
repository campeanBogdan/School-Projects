package Model.Repostiroy.RepositoryImplementation;

import Model.Model.Client;
import Model.Repostiroy.ClientRepository;

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

    public Client findById(Integer id) {
        Connection conn = jdbConnection.getConnection();
        Client client = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`client` WHERE Client.id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastName"));
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
        List<Client> clientList = new LinkedList<Client>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`client`", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastName"));
                client.setAddress(rs.getString("address"));
                clientList.add(client);
            }
            return clientList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client findByName(String firstName, String lastName) {
        Connection conn = jdbConnection.getConnection();
        Client client = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`client` WHERE firstname=? and lastname=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastName"));
                client.setAddress(rs.getString("address"));
                return client;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> findByNameAndAddress(String firstName, String lastName, String address) {
        Connection conn = jdbConnection.getConnection();
        List<Client> clients = new LinkedList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`client` WHERE firstname like '" + firstName + "%' or lastname like '" + lastName + "%' or address like '%" + address + "%'" , Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setLastName(rs.getString("lastname"));
                client.setFirstName(rs.getString("firstname"));
                client.setAddress(rs.getString("address"));
                clients.add(client);
            }
            return clients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(String oldFirstName, String oldLastName, Client newClient) {
        Connection conn = jdbConnection.getConnection();
        Client client = null;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE ass2.`Client` SET" +
                                                                " firstname='" + newClient.getFirstName() +
                                                                "', lastname='" + newClient.getLastName() +
                                                                "', address='" + newClient.getAddress() +
                                                                "' WHERE firstname=? and lastname=?");
            System.out.println(ps);
            ps.setString(1, oldFirstName);
            ps.setString(2, oldLastName);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByName(String firstName, String lastName) {
        Connection conn = jdbConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ass2.`client` WHERE firstname=? and lastname=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(Client client) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "INSERT INTO ass2.`client`(firstname, lastname, address) " +
                    " VALUES ('" +
                    client.getFirstName() + "', '" +
                    client.getLastName() + "', '" +
                    client.getAddress() + "')";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
