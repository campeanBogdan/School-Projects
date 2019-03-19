package Repository.Implementation;

import Model.Account;
import Model.User;
import Repository.AccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AccountRepositoryImplementation implements AccountRepository {

    private final JDBConnection jdbConnection;


    public AccountRepositoryImplementation(JDBConnection jdbConnection) {
        this.jdbConnection = jdbConnection;
    }

    public Account findById(Integer id) {
        Connection conn = jdbConnection.getConnection();
        Account account = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`account` WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setType(rs.getString("type"));
                account.setMoney(rs.getInt("money"));
                account.setClientId(rs.getInt("clientId"));
                account.setCreationDate(rs.getString("creationDate"));
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> findByClientId(Integer clientId) {
        Connection conn = jdbConnection.getConnection();
        List<Account> accountsList = new LinkedList<Account>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`account` WHERE clientId=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setType(rs.getString("type"));
                account.setMoney(rs.getInt("money"));
                account.setClientId(rs.getInt("clientId"));
                account.setCreationDate(rs.getString("creationDate"));
                accountsList.add(account);

            }

            return accountsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> findAll() {
        Connection conn = jdbConnection.getConnection();
        List<Account> accountsList = new LinkedList<Account>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`account`", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setType(rs.getString("type"));
                account.setMoney(rs.getInt("money"));
                account.setClientId(rs.getInt("clientId"));
                account.setCreationDate(rs.getString("creationDate"));
                accountsList.add(account);
            }
            return accountsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Account account) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "UPDATE Assignment1.`account` SET " +
                    "Account.type = '" + account.getType() + "', " +
                    "Account.money = '" + account.getMoney() + "', " +
                    "Account.creationDate = '" + account.getCreationDate() + "', " +
                    "Account.clientId = '" + account.getClientId() + "' " +
                    " WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Integer id = account.getId();
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllByClientId(Integer clientId) {
        Connection conn = jdbConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Assignment1.`account` WHERE Account.clientId=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, clientId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Integer id) {
        Connection conn = jdbConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Assignment1.`account` WHERE Account.id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Account account) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "INSERT INTO Assignment1.`account`(type, " +
                        "money, creationDate, clientId) VALUES ('" +
                    account.getType() + "', '" +
                    account.getMoney() + "', '" +
                    account.getCreationDate() + "', '" +
                    account.getClientId() + "')";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
