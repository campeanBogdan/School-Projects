package Model.Repostiroy.RepositoryImplementation;

import Model.Model.ClientBook;
import Model.Repostiroy.ShoppingBasket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ShoppingBasketRepositoryImplementation {

    private final JDBConnection jdbConnection;


    public ShoppingBasketRepositoryImplementation(JDBConnection jdbConnection) {
        this.jdbConnection = jdbConnection;
    }

    public void insert(ClientBook clientBook) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "INSERT INTO ass2.clientbook(idclient, idbook, stock) VALUE ('" +
                    clientBook.getIdClient() + "', '" + clientBook.getIdBook() + "', '" + clientBook.getStock() + "')";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ClientBook clientBook) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "UPDATE clientbook SET stock=" + clientBook.getStock() + " WHERE id=" + clientBook.getId();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClientBook findByIdClient(Integer idClient) {
        Connection conn = jdbConnection.getConnection();
        ClientBook clientBook = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`clientbook` WHERE idclient=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                clientBook = new ClientBook();
                clientBook.setId(rs.getInt("id"));
                clientBook.setIdBook(rs.getInt("idbook"));
                clientBook.setIdClient(rs.getInt("idclient"));
                clientBook.setStock(rs.getInt("stock"));
                return clientBook;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ClientBook findByIdClientIdBook(Integer idClient, Integer idBook) {
        Connection conn = jdbConnection.getConnection();
        ClientBook clientBook = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`clientbook` WHERE idclient=? AND idbook=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idClient);
            ps.setInt(2, idBook);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                clientBook = new ClientBook();
                clientBook.setId(rs.getInt("id"));
                clientBook.setIdBook(rs.getInt("idbook"));
                clientBook.setIdClient(rs.getInt("idclient"));
                clientBook.setStock(rs.getInt("stock"));
                return clientBook;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ClientBook findByIdBook(Integer idBook) {
        Connection conn = jdbConnection.getConnection();
        ClientBook clientBook = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`clientbook` WHERE idbook=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idBook);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                clientBook = new ClientBook();
                clientBook.setId(rs.getInt("id"));
                clientBook.setIdBook(rs.getInt("idbook"));
                clientBook.setIdClient(rs.getInt("idclient"));
                clientBook.setStock(rs.getInt("stock"));
                return clientBook;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ClientBook deleteByIdClient(Integer idClient) {
        Connection conn = jdbConnection.getConnection();
        ClientBook clientBook = null;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM clientbook WHERE idclient=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idClient);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ClientBook deleteByIdBook(Integer idBook) {
        Connection conn = jdbConnection.getConnection();
        ClientBook clientBook = null;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM clientbook WHERE idbook=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idBook);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ClientBook> findAll() {
        Connection conn = jdbConnection.getConnection();
        List<ClientBook> clientBookList = new LinkedList<ClientBook>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.clientbook", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClientBook clientBook = new ClientBook();
                clientBook.setId(rs.getInt("id"));
                clientBook.setIdClient(rs.getInt("idclient"));
                clientBook.setIdBook(rs.getInt("idbook"));
                clientBook.setStock(rs.getInt("stock"));
                clientBookList.add(clientBook);
            }
            return clientBookList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
