package Model.Service.ServiceImplementation;

import Model.Model.Book;
import Model.Model.Client;
import Model.Model.ClientBook;
import Model.Repostiroy.RepositoryImplementation.BookRepositoryImplementation;
import Model.Repostiroy.RepositoryImplementation.ClientBookRepositoryImplementation;
import Model.Repostiroy.RepositoryImplementation.ClientRepositoryImplementation;
import Model.Repostiroy.RepositoryImplementation.ShoppingBasketRepositoryImplementation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImplementation {

    private final ClientRepositoryImplementation clientRepositoryImplementation;
    private final ShoppingBasketRepositoryImplementation shoppingBasketRepositoryImplementation;
    private  final BookRepositoryImplementation bookRepositoryImplementation;
    private final ClientBookRepositoryImplementation clientBookRepositoryImplementation;


    public ClientServiceImplementation(ClientRepositoryImplementation clientRepositoryImplementation, ShoppingBasketRepositoryImplementation shoppingBasketRepositoryImplementation, BookRepositoryImplementation bookRepositoryImplementation, ClientBookRepositoryImplementation clientBookRepositoryImplementation) {
        this.clientRepositoryImplementation = clientRepositoryImplementation;
        this.shoppingBasketRepositoryImplementation = shoppingBasketRepositoryImplementation;
        this.bookRepositoryImplementation = bookRepositoryImplementation;
        this.clientBookRepositoryImplementation = clientBookRepositoryImplementation;
    }

    // get client
    public Client getClient(String firstName, String lastName) {
        Client client = clientRepositoryImplementation.findByName(firstName, lastName);
        return client;
    }

    // insert new client
    public void save(Client client) {
        clientRepositoryImplementation.insert(client);
    }

    // delete client
    public void delete(String firstName, String lastName) {
        Client client = clientRepositoryImplementation.findByName(firstName, lastName);
        shoppingBasketRepositoryImplementation.deleteByIdClient(client.getId());
        clientRepositoryImplementation.deleteByName(firstName, lastName);
    }

    // update clientbook
    private int updateClientBook(String firstName, String lastName, String title) {
        Book book = bookRepositoryImplementation.findByTitle(title);
        Client client = clientRepositoryImplementation.findByName(firstName, lastName);
        //ClientBook cb = shoppingBasketRepositoryImplementation.findByIdClient(client.getId());
        ClientBook cb = shoppingBasketRepositoryImplementation.findByIdClientIdBook(client.getId(), book.getId());
        if (cb == null)
            return 0;
        else
            return -1;
    }

    // buy book
    // -1 = out of stock
    public int buyBook(String firstName, String lastName, String title) {
        if (updateClientBook(firstName, lastName, title) == 0) {
            Book book = bookRepositoryImplementation.findByTitle(title);
            if (book.getStock() == 0)
                return -1;
            Client client = clientRepositoryImplementation.findByName(firstName, lastName);
            ClientBook cb = new ClientBook(client.getId(), book.getId(), 1);
            shoppingBasketRepositoryImplementation.insert(cb);
            book.setStock(book.getStock() - 1);
            bookRepositoryImplementation.update(book.getTitle(), book);
        }
        else {
            Book book = bookRepositoryImplementation.findByTitle(title);
            if (book.getStock() == 0)
                return -1;
            Client client = clientRepositoryImplementation.findByName(firstName, lastName);
            ClientBook cb = shoppingBasketRepositoryImplementation.findByIdClientIdBook(client.getId(), book.getId());
            cb.setStock(cb.getStock() + 1);
            book.setStock(book.getStock() - 1);
            bookRepositoryImplementation.update(book.getTitle(), book);
            shoppingBasketRepositoryImplementation.update(cb);
        }
        return 0;
    }

    // return all available books by: title, genre, author
    public List<Book> getAllBooks(String str) {
        List<Book> books = bookRepositoryImplementation.findByTitleLike(str);
        books.addAll(bookRepositoryImplementation.findByGenreLike(str));
        books.addAll(bookRepositoryImplementation.findByAuthorLike(str));

        return books;
    }

    // delete book from client
    public void returnBook(String firstName, String lastName, String title) {
        Book book = bookRepositoryImplementation.findByTitle(title);
        Client client = clientRepositoryImplementation.findByName(firstName, lastName);
        //Integer stock = shoppingBasketRepositoryImplementation.findByIdClient(client.getId()).getStock();
        Integer stock = shoppingBasketRepositoryImplementation.findByIdClientIdBook(client.getId(), book.getId()).getStock();
        shoppingBasketRepositoryImplementation.deleteByIdBook(book.getId());
        book.setStock(book.getStock() + stock);
        bookRepositoryImplementation.update(title, book);
    }

    // return sorted books from client -> lambda
    static int i;
    public List<Book> getClientBooks(String firstName, String lastName) {
        Client client = clientRepositoryImplementation.findByName(firstName, lastName);
        List<ClientBook> clientBookList = shoppingBasketRepositoryImplementation.findAll().stream()
                .filter(clientBook -> clientBook.getIdClient().equals(client.getId()))
                .collect(Collectors.toList());

        List<Book> books = bookRepositoryImplementation.findAll();
        List<Book> bookList = new LinkedList<>();

        for (int i = 0; i < books.size(); i++)
            for (int j = 0; j < clientBookList.size(); j++) {
                //System.out.println(books.get(i).getId() + " " + clientBookList.get(j).getIdBook());
                if (books.get(i).getId().equals(clientBookList.get(j).getIdBook ()))
                    bookList.add(books.get(i));
            }

        return bookList;
    }

    // return all clients sorted
    public List<Client> getAllClients() {
        List<Client> clientList = clientRepositoryImplementation.findAll().stream()
                .collect(Collectors.toList());

        clientList.sort((c1, c2) -> c1.getFirstName().compareTo(c2.getFirstName()));

        return clientList;
    }

    // update client
    public void update(String firstName, String lastName, Client newClient) {
        clientRepositoryImplementation.update(firstName, lastName, newClient);
    }

    // return all clients by: firstname, lastname, address
    public List<Client> getAllClientsLike(String text) {
        List<Client> clients = clientRepositoryImplementation.findByNameAndAddress(text, text, text);
        return clients;
    }
}
