package Model.Service;

import Model.Model.Book;
import Model.Model.User;

import java.util.List;

public interface AdminService {

    void save(User user);
    void delete(String username);
    List<User> getAllUsers();
    List<Book> getAllBooks();
    int innsertBook(Book book);
    int updateBook(String title, Book book);
}
