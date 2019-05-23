package Controller;

import Model.Model.Book;
import Model.Model.Client;
import Model.Model.User;
import Model.Repostiroy.RepositoryImplementation.*;
import Model.Service.ServiceImplementation.AdminServiceImplementation;
import Model.Service.ServiceImplementation.ClientServiceImplementation;
import Model.Service.ServiceImplementation.Report.Report;
import Model.Service.ServiceImplementation.Report.ReportGenerator;
import Model.Service.ServiceImplementation.ShoppingBasketServiceImplementation;
import View.Presentation.Admin.AdminBookGUI;
import View.Presentation.Admin.SendChanges;
import View.Presentation.LoginGUI;
import View.Presentation.UserGUI;
import View.TableClass;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Main implements Observer  {

    public static LoginGUI loginGUI = new LoginGUI();
    public static AdminBookGUI adminBookGUI = new AdminBookGUI();
    public static UserGUI userGUI = new UserGUI();
    public static TableClass tableClass = new TableClass();
    public static Integer login = 0;
    public static String loginUsername;

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("String null"))
            JOptionPane.showMessageDialog(null, arg, "Observer: Admin Service", JOptionPane.INFORMATION_MESSAGE);
        if (arg.equals("Book null"))
            JOptionPane.showMessageDialog(null, arg, "Observer: Admin Service", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        final JDBConnection conn = new JDBConnection();
        final ClientRepositoryImplementation clientRepositoryImplementation = new ClientRepositoryImplementation(conn);
        final BookRepositoryImplementation bookRepositoryImplementation = new BookRepositoryImplementation(conn);
        final UserRepositoryImplementation userRepositoryImplementation = new UserRepositoryImplementation(conn);
        final ShoppingBasketRepositoryImplementation shoppingBasketRepositoryImplementation = new ShoppingBasketRepositoryImplementation(conn);
        final ClientBookRepositoryImplementation clientBookRepositoryImplementation = new ClientBookRepositoryImplementation(conn);
        final ClientServiceImplementation clientServiceImplementation = new ClientServiceImplementation(clientRepositoryImplementation, shoppingBasketRepositoryImplementation, bookRepositoryImplementation, clientBookRepositoryImplementation);
        final AdminServiceImplementation adminServiceImplementation = new AdminServiceImplementation(userRepositoryImplementation, bookRepositoryImplementation);
        final ShoppingBasketServiceImplementation shoppingBasketServiceImplementation = new ShoppingBasketServiceImplementation(shoppingBasketRepositoryImplementation);
        final SendChanges sendChanges = new SendChanges();
        final ReportGenerator reportGenerator = new ReportGenerator();
        final Main main = new Main();


        sendChanges.addObserver(adminBookGUI);
        adminServiceImplementation.addObserver(adminBookGUI);



// LOGIN
        loginGUI.addLoginActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginGUI.getTextField1().getText();
                String password = loginGUI.getPasswordField1().getText();
                User user = adminServiceImplementation.find(username);
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    login = 1;
                    if (user.isAdmin() == 1) {
                        loginGUI.close();
                        tableClass.initializeUserBookTable();
                        List<Book> books = clientServiceImplementation.getAllBooks("");
                        tableClass.displayBooks(books);
                        adminBookGUI.open();
                        sendChanges.loginAdminButton();
                        loginUsername = user.getUsername();
                    } else {
                        loginGUI.close();
                        tableClass.initializeUserClientTable();
                        tableClass.initializeUserBookTable();
                        List<Book> books = clientServiceImplementation.getAllBooks("");
                        tableClass.displayBooks(books);
                        List<Client> clients = clientServiceImplementation.getAllClients();
                        tableClass.displayClients(clients);
                        userGUI.open();
                        sendChanges.loginUserButton();
                    }
                }

// listener pt. randul selectat din tabelul Client
                tableClass.getUserClientTable().addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String[] w = tableClass.getSelectedRowClient();
                        userGUI.getTextField6().setText(w[0] + " " + w[1]);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //String[] s = tableClass.getSelectedRowBook();
                        //System.out.println(s[0]);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

// listener pt. reandul selectat din tabelul Book
// si completez automat pt. buy
                tableClass.getUserBookTable().addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String[] w = tableClass.getSelectedRowBook();
                        userGUI.getTextField5().setText(w[0]);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        });

// ADMIN


    // Search books by: title, genre, authors
        // csi
        adminBookGUI.getTextField1().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = adminBookGUI.getTextField1().getText();
                List<Book> books = clientServiceImplementation.getAllBooks(text);
                tableClass.displayBooks(books);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = adminBookGUI.getTextField1().getText();
                List<Book> books = clientServiceImplementation.getAllBooks(text);
                tableClass.displayBooks(books);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

// Insert Book
        adminBookGUI.addInsertActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = adminBookGUI.getTextField2().getText();
                String genre = adminBookGUI.getTextField3().getText();
                String author = adminBookGUI.getTextField4().getText();
                Integer stock = null;
                if (!adminBookGUI.getTextField5().getText().equals(""))
                    stock = Integer.parseInt(adminBookGUI.getTextField5().getText());
                if (stock == null)
                    stock = 0;
                String description = adminBookGUI.getTextField6().getText();

                Book book = new Book(title, genre, description, stock, author);
                adminServiceImplementation.insertBook(book);
                sendChanges.sendInsertBookButton();
                List<Book> books = adminServiceImplementation.getAllBooks();
                tableClass.displayBooks(books);
            }
        });

// Update Book
        adminBookGUI.addUpdateActionListener(listener -> {
            String title = adminBookGUI.getTextField2().getText();
            String genre = adminBookGUI.getTextField3().getText();
            String author = adminBookGUI.getTextField4().getText();
            Integer stock = Integer.parseInt(adminBookGUI.getTextField5().getText());
            String description = adminBookGUI.getTextField6().getText();

            Book book = new Book(title, genre, description, stock, author);
            adminServiceImplementation.updateBook(title, book);
            sendChanges.sendUpdateBookButton();
        });

// Delete Book
        adminBookGUI.addDeleteActionListener(listener -> {
            String title = tableClass.getSelectedRowBook()[0];
            adminServiceImplementation.deleteBook(title);
            List<Book> books = adminServiceImplementation.getAllBooks();
            sendChanges.sendDeleteBookButton();
            tableClass.displayBooks(books);
        });

// Insert user
        adminBookGUI.addInsertUserActionListener(listener -> {
            String username = adminBookGUI.getTextField7().getText();
            String password = adminBookGUI.getTextField8().getText();
            boolean admin = adminBookGUI.getCheckBox1();
            int v;
            if (admin)
                v = 1;
            else
                v = 0;
            User user = new User(username, password, v);
            adminServiceImplementation.save(user);
            sendChanges.sendInsertUser();
        });

// Delete user
        adminBookGUI.addDeleteUserActionListener(listener -> {
            String username = adminBookGUI.getTextField7().getText();
            adminServiceImplementation.delete(username);
            sendChanges.sendDeleteUser();
        });

// Export report
        adminBookGUI.addExportReportListener(listener -> {
            String text = adminBookGUI.getComboValue();
            Report report = reportGenerator.getReport(text);
            List<Book> outOfStockBooks = adminServiceImplementation.getOutOfStockBooks();
            report.exportReport(loginUsername, outOfStockBooks);
        });


// CLIENT

// Insert Client
        userGUI.addInsertClientActionListener(listener -> {
            String firstName = userGUI.getTextField3().getText();
            String lastName = userGUI.getTextField4().getText();
            String address = userGUI.getTextField7().getText();
            Client client = new Client(firstName, lastName, address);
            clientServiceImplementation.save(client);
            List<Client> clients = clientServiceImplementation.getAllClients();
            tableClass.displayClients(clients);
        });

// Delete Client
        userGUI.addDeleteClientActionListener(listener -> {
            String s = userGUI.getTextField6().getText();
            String[] str = s.split(" ");
            String firstName = str[0];
            String lastName = str[1];
            clientServiceImplementation.delete(firstName, lastName);
            List<Client> clients = clientServiceImplementation.getAllClients();
            tableClass.displayClients(clients);
        });

// Afisez toti utilizatorii dupa: nume, prenume, adresa
        userGUI.getTextField1().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = userGUI.getTextField1().getText();
                List<Client> clients = clientServiceImplementation.getAllClientsLike(text);
                tableClass.displayClients(clients);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = userGUI.getTextField1().getText();
                List<Client> clients = clientServiceImplementation.getAllClientsLike(text);
                tableClass.displayClients(clients);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

// Afisez toate cartile intr-un tabel dupa: title, genre, authorname
        userGUI.getTextField2().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = userGUI.getTextField2().getText();
                List<Book> books = clientServiceImplementation.getAllBooks(text);
                tableClass.displayBooks(books);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = userGUI.getTextField2().getText();
                List<Book> books = clientServiceImplementation.getAllBooks(text);
                tableClass.displayBooks(books);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

// Buy Book
        userGUI.getBuyBookButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = userGUI.getTextField6().getText();
                String[] name = text.split(" ");
                String title = userGUI.getTextField5().getText();
                System.out.println();
                clientServiceImplementation.buyBook(name[0], name[1], title);
                String s = userGUI.getTextField2().getText();
                List<Book> books = clientServiceImplementation.getAllBooks(s);
                tableClass.displayBooks(books);
            }
        });

// View All Client's Books
        userGUI.addUpdateClientActionListener(listener -> {
            String firstName = tableClass.getSelectedRowClient()[0];
            String lastName = tableClass.getSelectedRowClient()[1];
            List<Book> books = clientServiceImplementation.getClientBooks(firstName, lastName);
            Client client = clientServiceImplementation.getClient(firstName, lastName);
            shoppingBasketServiceImplementation.setStock(client, books);
            tableClass.displayBooksFromClient(books);
        });

// Delete books from client
        userGUI.addDeleteBookFromClientActionListener(listener -> {
            String s = userGUI.getTextField6().getText();
            String firstName = s.split(" ")[0];
            String lastName = s.split(" ")[1];
            String title = userGUI.getTextField5().getText();
            clientServiceImplementation.returnBook(firstName, lastName, title);
            List<Book> books = clientServiceImplementation.getAllBooks("");
            tableClass.displayBooks(books);
        });

    }
}
