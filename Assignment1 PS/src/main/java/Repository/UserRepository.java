package Repository;

import Model.User;

import java.util.List;

public interface UserRepository {

// Admin
    User findByUsername(String username);
    User findById(Integer id);
    List<User> findAll();
    void update(String username, User user);
    void deleteByUsername(String username);
    void create(User user);
}
