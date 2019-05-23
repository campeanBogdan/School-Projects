package Service;

import Model.Client;
import Model.User;

import java.util.List;

public interface UserService {

    int login(String username, String password);
    User findByUsername(String username);
    int save(User user);
    int update(String username, User user);
    int delete(String username);
    List<User> getAll();
}
