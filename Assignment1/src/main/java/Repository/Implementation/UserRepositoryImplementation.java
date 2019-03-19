package Repository.Implementation;

import Model.Client;
import Model.User;
import Repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryImplementation implements UserRepository {

    private final JDBConnection jdbConnection;


    public UserRepositoryImplementation(JDBConnection jdbConnection) {
        this.jdbConnection = jdbConnection;
    }

    public User findByUsername(String username) {
        Connection conn = jdbConnection.getConnection();
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`user` WHERE User.username=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("admin"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findById(Integer id) {
        Connection conn = jdbConnection.getConnection();
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`user` WHERE User.id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("admin"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> findAll() {
        Connection conn = jdbConnection.getConnection();
        List<User> usersList = new LinkedList<User>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`user`", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("admin"));
                usersList.add(user);
            }
            return usersList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

// LA PARAMETRUL user II SETEZ ID-UL SELECTAT DIN INTERFATA INAINTE SA
// IL TRIMIT CA PARAMETRU
    public void update(String username, User user) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "UPDATE Assignment1.`user` SET " +
                    "User.username = '" + user.getUsername() + "', " +
                    "User.password = '" + user.getPassword() + "', " +
                    "User.admin = " + user.isAdmin() +
                    " WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByUsername(String username) {
        Connection conn = jdbConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Assignment1.`user` WHERE User.username=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(User user) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "INSERT INTO Assignment1.`user`(username, " +
                    "password, admin) " +
                    " VALUES ('" +
                    user.getUsername() + "', '" +
                    user.getPassword() + "', " +
                    user.isAdmin() + ")";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
