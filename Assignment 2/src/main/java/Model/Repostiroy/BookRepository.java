package Model.Repostiroy;

import Model.Model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findByGenreLike(String genre);
    Book findByTitle(String title);
    List<Book> findByTitleLike(String title);
    List<Book> findByAuthorLike(String name);
    Book findById(Integer id);
    void update(String oldTitle, Book newBook);
    void deleteByTitle(String title);
    void insert(Book book);
    List<Book> findAll();
}
