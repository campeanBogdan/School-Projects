package Service.Implementation;

import Model.Client;
import Model.User;
import Repository.Implementation.ClientRepositoryImplementation;
import Repository.Implementation.UserRepositoryImplementation;
import Service.UserService;
import sun.nio.cs.StandardCharsets;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.*;
import java.nio.*;

import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImplementation implements UserService {

    private final UserRepositoryImplementation uri;
    private final Security security;


    public UserServiceImplementation(UserRepositoryImplementation uri) {
        this.uri = uri;
        this.security = new Security();
    }

    public User findByUsername(String username) {
        User user = uri.findByUsername(username);
        return user;
    }

    // return 1 => ADMIN
    // return 2 => USER INEXISTENT
    public int login(String username, String password) {
        User user = uri.findByUsername(username);

        if (this.validate(user) == 1)
            return 2;

        int valid;

        String hashPassword = security.getMd5(password);

        if (user.getPassword().equals(hashPassword))
            valid = 0;
        else
            return 2;

        if (user.isAdmin() && valid == 0)
            return 1;
        else
            return 0;
    }

    // return 2 => MAI EXISTA ACEST USER
    // return 1 => USERNAME-UL NU ESTE ACCEPTAT
    public int save(User user) {
        List<User> usersList = uri.findAll();

        String password = user.getPassword();
        String hashPassword = security.getMd5(password);
        user.setPassword(hashPassword);

        for (int i = 0; i < usersList.size(); i++) {
            User u = usersList.get(i);
            if (u.getUsername().equals(user.getUsername()))
                return 2;
        }
        if (this.validate(user) == 0)
            uri.create(user);
        else
            return 1;
        return 0;
    }

    // return 1 => USERNAME-UL NU ESTE ACCEPTAT
    // return 2 => USER-UL NU EXISTA
    // return 3 => USERNAME-UL EXISTA DEJA
    public int update(String username, User newUser) {
        if (this.validate(newUser) == 1)
            return 1;
        List<User> userList = uri.findAll();
        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i).getUsername().equals(newUser.getUsername()))
                return 3;

        User user = uri.findByUsername(username);

        if (user == null)
            return 2;

        if (newUser.getUsername().equals(""))
            newUser.setUsername(user.getUsername());
        if (newUser.getPassword().equals("")) {
            newUser.setPassword(user.getPassword());
        }

        String password = newUser.getPassword();
        String hashPassword = security.getMd5(password);
        newUser.setPassword(hashPassword);
        System.out.println(newUser.getPassword());

        if (this.validate(newUser) == 0)
            uri.update(username, newUser);
        else
            return 1;
        return 0;
    }

    // return 1 => USERNAME-UL INTRODUS NU EXISTA
    public int delete(String username) {
        User user = uri.findByUsername(username);
        if (user != null)
            uri.deleteByUsername(username);
        else
            return 1;
        return 0;
    }

    public List<User> getAll() {
        List<User> usersList = uri.findAll();
        return usersList;
    }

    private int validate(User user) {
        // username invalid => return 1
        if (user == null)
            return 1;
        String username = user.getUsername();
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == ' ' ||
                    username.charAt(i) == '|' ||
                    username.charAt(i) == '?' ||
                    username.charAt(i) == '!')
                return 1;
        }
        return 0;
    }
}
