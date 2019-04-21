package Model.Service.ServiceImplementation;

import Model.Model.Book;
import Model.Model.Client;
import Model.Model.User;
import Model.Repostiroy.RepositoryImplementation.BookRepositoryImplementation;
import Model.Repostiroy.RepositoryImplementation.ClientRepositoryImplementation;
import Model.Repostiroy.RepositoryImplementation.UserRepositoryImplementation;
import Model.Service.UserService;

import javax.swing.text.Document;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class AdminServiceImplementation extends Observable implements UserService {

    private final BookRepositoryImplementation bookRepositoryImplementation;
    private final UserRepositoryImplementation userRepositoryImplementation;


    public AdminServiceImplementation(UserRepositoryImplementation userRepositoryImplementation, BookRepositoryImplementation bookRepositoryImplementation) {
        this.bookRepositoryImplementation = bookRepositoryImplementation;
        this.userRepositoryImplementation = userRepositoryImplementation;
    }

    // find user by username
    public User find(String username) {
        validation(username);
        User user = userRepositoryImplementation.findByUsername(username);
        return user;
    }

    // insert new user
    public void save(User user) {
        userRepositoryImplementation.insert(user);
    }

    // delete user by username
    public void delete(String username) {
        userRepositoryImplementation.delete(username);
    }

    // return all users sorted -> lambda
    public List<User> getAllUsers() {
        List<User> userList = userRepositoryImplementation.findAll();
        userList.sort((u1, u2) -> u1.getUsername().compareTo(u2.getUsername()));
        return userList;
    }

    // return all books sorted -> lambda
    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepositoryImplementation.findAll();
        bookList.sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
        return bookList;
    }

    // insert book
    // -1 = book exists already
    public int insertBook(Book book) {
        validation(book);
        Book b = bookRepositoryImplementation.findByTitle(book.getTitle());
        if (b == null)
            bookRepositoryImplementation.insert(book);
        else
            return -1;
        return 0;
    }

    // update book
    // -1 = book doesn't exist
    public int updateBook(String title, Book book) {
        Book b = bookRepositoryImplementation.findByTitle(book.getTitle());
        validation(title);
        if (b == null)
            bookRepositoryImplementation.update(title, book);
        else
            return -1;
        return 0;
    }

    // delete book
    // -1 = book doesn't exist
    public int deleteBook(String title) {
        validation(title);
        Book b = bookRepositoryImplementation.findByTitle(title);
        validation(title);
        bookRepositoryImplementation.deleteByTitle(title);
        return 0;
    }

    private void validation(Object obj) {
        if (obj instanceof String) {
            if (obj.equals("")) {
                setChanged();
                notifyObservers("String null");
            }
        }
        else {
                if (obj instanceof Book)
                    if (obj == null) {
                        setChanged();
                        notifyObservers("Book null");
                    }
            }
    }

    // get out of stock books
    public List<Book> getOutOfStockBooks() {
        List<Book> books = bookRepositoryImplementation.findAll();
        List<Book> outOfStockBooks = books.stream()
                .filter(book -> book.getStock().equals(0))
                .collect(Collectors.toList());
        return outOfStockBooks;
    }

}
