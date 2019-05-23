package Model.Repostiroy.RepositoryImplementation;

import Model.Model.User;
import Model.Repostiroy.UserRepository;

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

    public User findById(Integer id) {
        Connection conn = jdbConnection.getConnection();
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`user` WHERE user.id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setAdmin(rs.getInt("isadmin"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(User user) {
        Connection conn = jdbConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ass2.user(username, password, isadmin) VALUE ('" + user.getUsername() +
                    "', '" + user.getPassword() + "', '" + user.isAdmin() + "')", Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String username) {
        Connection conn = jdbConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ass2.user WHERE username=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findByUsername(String username) {
        Connection conn = jdbConnection.getConnection();
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`user` WHERE username like '" + username + "%'" , Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getInt("isadmin"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> findAll() {
        Connection conn = jdbConnection.getConnection();
        List<User> userList = new LinkedList<User>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`user`" , Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getInt("isadmin"));
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
