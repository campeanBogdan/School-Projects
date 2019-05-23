package Main;

import Model.Account;
import Model.Activity;
import Model.Client;
import Model.User;
import Presentation.PresentationImplementation.*;
import Repository.Implementation.*;
import Service.Implementation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        final JDBConnection conn = new JDBConnection();
        final ClientRepositoryImplementation cri = new ClientRepositoryImplementation(conn);
        final UserRepositoryImplementation uri = new UserRepositoryImplementation(conn);
        final AccountRepositoryImplementation ari = new AccountRepositoryImplementation(conn);
        final ActivityRepositoryImplementation acri = new ActivityRepositoryImplementation(conn);
        final ClientServiceImplementation csi = new ClientServiceImplementation(cri);
        final UserServiceImplementation usi = new UserServiceImplementation(uri);
        final AccountServiceImplementation asi = new AccountServiceImplementation(ari);
        final ActivityServiceImplementation acsi = new ActivityServiceImplementation(acri);

        final InitGUI initGUI = new InitGUI();
        final AdminGUI adminGUI = new AdminGUI();
        final UserGUI userGUI = new UserGUI();
        final AccountGUI accountGUI = new AccountGUI();
        final UsersTable usersTable = new UsersTable();




        // la asi.update(account) => trebuie convertit din String de pe interfata
        // in Integer => verificare oldAccount.money == account.money
        //               verificare oldAccount.clientId == account.clientId

//        Account acc = new Account("Economii", 150, "", 1);
//        acc.setId(2);
//        System.out.println(asi.update(acc));


// INITGUI

// Login Button
        initGUI.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = initGUI.getUsername();
                String password = initGUI.getPassword();
                int login = usi.login(username, password);
                User user = usi.findByUsername(username);

                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);

                switch (login) {
                    case 0:
                        acsi.save(new Activity("Login User: " + username, s));
                        JOptionPane.showMessageDialog(null, "Logat ca si User", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        acsi.save(new Activity("Login Admin: " + username, s));
                        JOptionPane.showMessageDialog(null, "Logat ca si Admin", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Username si parola inexistente!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }

                if (login == 1) {
                    initGUI.close();
                    adminGUI.setVisible();
                }
                if (login == 0) {
                    initGUI.close();
                    userGUI.setVisible();
                }
            }
        });

// Exit Button
        initGUI.getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { initGUI.close(); }
        });


// ADMIN

// Save Button
        adminGUI.getSaveUserButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = adminGUI.getUsername1();
                String password = adminGUI.getPassword1();
                boolean isAdmin = adminGUI.getIsAdmin1();
                int valid = usi.save(new User(username, password, isAdmin));
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);

                switch (valid) {
                    case 0:
                        acsi.save(new Activity("User nou: " + username, s));
                        JOptionPane.showMessageDialog(null, "S-a creeat un user cu Username: " + username + ", Password: " + password, "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Username sau Parola nu sunt acceptate!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Acest username exista deja!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

// Update Button
        adminGUI.getUpdateUserButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = adminGUI.getUsername2();
                String newUsername = adminGUI.getUsername3();
                String newPassword = adminGUI.getPassword3();
                boolean isAdmin = adminGUI.getIsAdmin2();

                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);

                User newUser = new User(newUsername, newPassword, isAdmin);
                int valid = usi.update(username, newUser);

                switch (valid) {
                    case 0:
                        acsi.save(new Activity("User update: " + newUser.getUsername(), s));
                        JOptionPane.showMessageDialog(null, "Userul a fost modificat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Usernameul nu este acceptat!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Userul nu exista!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Username existent!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }

            }
        });

// Delete Button
        adminGUI.getDeleteUserButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = adminGUI.getUsername5();
                int valid = usi.delete(username);
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);

                switch (valid) {
                    case 0:
                        acsi.save(new Activity("User sters: " + username, s));
                        JOptionPane.showMessageDialog(null, "Userul a fost sters cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Userul introdus nu exista!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

// View All Users
        adminGUI.getViewAllUsersButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<User> userList = usi.getAll();
                usersTable.users(userList);
            }
        });

// View Activities
        adminGUI.getViewActivitiesButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Activity> activityList = acsi.getAll();
                usersTable.activities(activityList);
            }
        });

// Exist Button
        adminGUI.getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = initGUI.getUsername();
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);
                acsi.save(new Activity("Logout admin: " + username, s));
                initGUI.setVisible(true);
                adminGUI.close();
            }
        });


// USER

// Save button
        userGUI.getSaveClientButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = userGUI.getTextField1();
                String lastName = userGUI.getTextField2();
                String cardId = userGUI.getTextField3();
                String cnp = userGUI.getTextField4();
                String address = userGUI.getTextField5();
                Client client = new Client(firstName, lastName, cardId, cnp, address);
                int valid = csi.save(client);
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);

                switch (valid) {
                    case 0:
                        acsi.save(new Activity("Client nou: " + client.getFirstName() + " " + client.getLastName() + ". CNP: " + client.getCNP(), s));
                        JOptionPane.showMessageDialog(null, "Client introdus cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Cardul exista deja in baza de date!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "CNP-ul exista deja in baza de date!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "First name invalid!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Last name invalid!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Card number invalid!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 8:
                        JOptionPane.showMessageDialog(null, "CNP invalid!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

// Update button
        userGUI.getUpdateClientButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cnp = userGUI.getTextField8();
                String firstName = userGUI.getTextField9();
                String lastName = userGUI.getTextField6();
                String cardId = userGUI.getTextField7();
                String address = userGUI.getTextField10();
                Client newClient = new Client(firstName, lastName, cardId, cnp, address);
                int valid = csi.update(cnp, newClient);
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);

                switch (valid) {
                    case 0:
                        acsi.save(new Activity("Client update. CNP: " + newClient.getCNP(), s));
                        JOptionPane.showMessageDialog(null, "Client actualizat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Cardul exista deja in baza de date!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "CNP-ul exista deja in baza de date!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "First name invalid!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Last name invalid!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Card number invalid!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 8:
                        JOptionPane.showMessageDialog(null, "CNP invalid!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

// Delete button
        userGUI.getDeleteClientButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cnp = userGUI.getTextField11();
                Client client = csi.findClientByCNP(cnp);
                int valid = csi.delete(cnp);
                asi.deleteByClientId(client.getId());
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);

                switch(valid) {
                    case 0:
                        acsi.save(new Activity("Client sters. CNP: " + client.getCNP(), s));
                        JOptionPane.showMessageDialog(null, "Clientul a fost sters cu succes! Conturile sale au fost sterse!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Clientul cu CNP-ul: " + cnp + " nu a fost gasit!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

// View All Clients button
        userGUI.getViewAllClientsButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Client> clientList = csi.getAll();
                usersTable.clients(clientList);
            }
        });

// View All Accounts
        userGUI.getGoToAccountsButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accountGUI.setVisible();
            }
        });

// Exit button
        userGUI.getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = initGUI.getUsername();
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);
                acsi.save(new Activity("Logout user: " + username, s));
                userGUI.setVisible(false);
                initGUI.setVisible(true);
            }
        });


// ACCOUNT

// Save Account button
        accountGUI.getSaveAccountButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cnp = accountGUI.getTextField1();
                String type = accountGUI.getTextField2();
                String sum = accountGUI.getTextField3();

                Client client = csi.findClientByCNP(cnp);
                Integer clientId = client.getId();
                if (client == null)
                    JOptionPane.showMessageDialog(null, "CNP invalid", "Info", JOptionPane.ERROR_MESSAGE);
                Integer sumToString = Integer.parseInt(sum);

                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);
                Account account = new Account(type, sumToString, s);
                account.setClientId(clientId);
                int valid = asi.save(account);

                switch (valid) {
                    case 0:
                        Activity activity = new Activity("Cont nou la CNP:" + client.getCNP(), s);
                        acsi.save(activity);
                        JOptionPane.showMessageDialog(null, "S-a creat cont nou la CNP " + client.getCNP(), "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Suma negativa!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }

            }
        });

// Delete account button
        accountGUI.getDeleteAccountButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountGUI.getTextField5();
                Integer accNr = Integer.parseInt(accountNumber);
                Account account = asi.findById(accNr);
                account.setId(accNr);

                Client client = csi.findClientById(account.getClientId());
                int valid = asi.delete(accNr);

                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);

                switch (valid) {
                    case 0:
                        acsi.save(new Activity("Cont id:" + accountNumber + " sters de la CNP:" + client.getCNP(), s));
                        JOptionPane.showMessageDialog(null, "S-a sters contul cu nr: " + accountNumber, "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Suma negativa!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }

            }
        });

// Update account button
        accountGUI.getUpdateAccountButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountGUI.getTextField6();
                String sum = accountGUI.getTextField7();
                Integer ssum = Integer.parseInt(sum);

                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);
                Account newAccount = new Account("", ssum, s);
                newAccount.setId(Integer.parseInt(accountNumber));
                System.out.println("C " + newAccount.getClientId());
                int valid = asi.update(newAccount);
                Client client = csi.findClientById(newAccount.getClientId());

                System.out.println(client.getId());
                System.out.println(newAccount.getClientId());

                switch (valid) {
                    case 0:
                        Activity activity = new Activity("Cont update. Id: " + accountNumber + " CNP: " + client.getCNP() + " suma noua: " + sum, s);
                        acsi.save(activity);
                        JOptionPane.showMessageDialog(null, "Suma s-a actualizat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Nu exista contul ales!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Suma negativa!", "Info", JOptionPane.ERROR_MESSAGE);
                        break;
                }

            }
        });

// Transfer from -> to
        accountGUI.getTransferButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer id1 = Integer.parseInt(accountGUI.getTextField8());
                Integer id2 = Integer.parseInt(accountGUI.getTextField9());
                Integer sum = Integer.parseInt(accountGUI.getTextField10());
                int valid = -1;

                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date();
                String s = df.format(date);
                Account a1 = asi.findById(id1);
                a1.setId(id1);
                Account a2 = asi.findById(id2);
                a2.setId(id2);

                if (id1.equals(id2))
                    JOptionPane.showMessageDialog(null, "Acelasi numar de cont!", "Info", JOptionPane.ERROR_MESSAGE);
                else {
                    valid = asi.updateSum(sum, id1, id2);

                    switch (valid) {
                        case 0:
                            acsi.save(new Activity("Transfer suma: " + sum + ". From: " + a1.getId() + ". To: " + a2.getId(), s));
                            JOptionPane.showMessageDialog(null, "S-a transferat cu succes suma " + sum + " From: " + id1 + " To: " + id2, "Info", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(null, "Suma < 0 !", "Info", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "Suma insuficienta in contul " + id1, "Info", JOptionPane.WARNING_MESSAGE);
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Nu exista contul " + id1, "Infor", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, "Nu exista contul " + id2, "Info", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                }
            }
        });

// View all accounts
        accountGUI.getViewAllAccountsButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Account> accountList = asi.getAll();
                List<Client> clientList = csi.getAll();
                usersTable.accounts(accountList, clientList);
            }
        });

// Exit account
        accountGUI.getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accountGUI.setVisible(false);
            }
        });
    }
}
