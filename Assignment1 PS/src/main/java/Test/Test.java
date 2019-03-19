package Test;

import Model.Account;
import Model.Client;
import Model.User;
import Repository.Implementation.AccountRepositoryImplementation;
import Repository.Implementation.ClientRepositoryImplementation;
import Repository.Implementation.JDBConnection;
import Repository.Implementation.UserRepositoryImplementation;
import Service.Implementation.AccountServiceImplementation;
import Service.Implementation.ClientServiceImplementation;
import Service.Implementation.UserServiceImplementation;

public class Test {

    public static Test test = new Test();


//    public void testUser() {
//        JDBConnection conn = new JDBConnection();
//        UserRepositoryImplementation uri = new UserRepositoryImplementation(conn);
//        UserServiceImplementation usi = new UserServiceImplementation(uri);
//
//        User user = new User("testuser", "testuser", false);
//        user.setId(1);
//        assert usi.save(user) == 0;
//        assert usi.login(user.getUsername(), user.getPassword()) == 0;
//        assert usi.delete(user.getUsername()) == 0;
//    }
//
//    public void testClient() {
//        JDBConnection conn = new JDBConnection();
//        ClientRepositoryImplementation cri = new ClientRepositoryImplementation(conn);
//        ClientServiceImplementation csi = new ClientServiceImplementation(cri);
//
//        Client client = new Client("test", "test", "123456", "1234567890123", "test");
//        client.setId(1);
//        assert csi.save(client) != 0;
//        assert csi.delete("1234567890123") == 0;
//    }
//
//    public void testAccount() {
//        JDBConnection conn = new JDBConnection();
//        AccountRepositoryImplementation ari = new AccountRepositoryImplementation(conn);
//        AccountServiceImplementation asi = new AccountServiceImplementation(ari);
//
//        Account account1 = new Account("test", 100, "12.23.2019 13:00:23");
//        Account account2 = new Account("test", 50, "12.23.2019 13:00:23");
//        account1.setId(1);
//        account2.setId(2);
//        assert asi.save(account1) == 0;
//        assert asi.save(account2) == 0;
//        assert asi.updateSum(200, 1, 2) == 0;
//    }

    //public static void main(String[] args) {
//        test.testUser();
//        test.testClient();
//        test.testAccount();
    //}
}
