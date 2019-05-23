package Model.Repostiroy.RepositoryImplementation;

import Model.Model.Book;
import Model.Repostiroy.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BookRepositoryImplementation implements BookRepository {

    private final JDBConnection jdbConnection;


    public BookRepositoryImplementation(JDBConnection jdbConnection) {
        this.jdbConnection = jdbConnection;
    }

    public Book findById(Integer id) {
        Connection conn = jdbConnection.getConnection();
        Book book = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`book` WHERE book.id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                book.setDescription(rs.getString("description"));
                book.setStock(rs.getInt("stock"));
                book.setAuthorName(rs.getString("authorname"));
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book findByTitle(String title) {
        Connection conn = jdbConnection.getConnection();
        Book book = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`book` WHERE title =?" , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                book = new Book();
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setGenre(rs.getString("genre"));
                book.setStock(rs.getInt("stock"));
                book.setAuthorName(rs.getString("authorname"));
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> findByTitleLike(String title) {
        Connection conn = jdbConnection.getConnection();
        List<Book> books = new LinkedList<Book>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`book` WHERE title like '" + title + "%'" , Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setGenre(rs.getString("genre"));
                book.setStock(rs.getInt("stock"));
                book.setAuthorName(rs.getString("authorname"));
                books.add(book);
            }

                return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> findByGenreLike(String genre) {
        Connection conn = jdbConnection.getConnection();
        List<Book> books = new LinkedList<Book>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`book` WHERE genre like '" + genre + "%'" , Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setGenre(rs.getString("genre"));
                book.setStock(rs.getInt("stock"));
                book.setAuthorName(rs.getString("authorname"));
                books.add(book);
            }
                return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> findByAuthorLike(String name) {
        Connection conn = jdbConnection.getConnection();
        List<Book> books = new LinkedList<Book>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.`book` WHERE authorname like '" + name + "%'" , Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setGenre(rs.getString("genre"));
                book.setStock(rs.getInt("stock"));
                book.setAuthorName(rs.getString("authorname"));
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> findAll() {
        Connection conn = jdbConnection.getConnection();
        List<Book> bookList = new LinkedList<Book>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ass2.book", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                book.setDescription(rs.getString("description"));
                book.setStock(rs.getInt("stock"));
                book.setAuthorName(rs.getString("authorname"));
                bookList.add(book);
            }
            return bookList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(String oldTitle, Book newBook) {
        Connection conn = jdbConnection.getConnection();
        Book book = null;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE ass2.`book` SET" +
                    " title='" + newBook.getTitle() +
                    "', genre='" + newBook.getGenre() +
                    "', description='" + newBook.getDescription() +
                    "', stock='" + newBook.getStock() +
                    "', authorname='" + newBook.getAuthorName() +
                    "' WHERE title=?");
            ps.setString(1, oldTitle);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByTitle(String title) {
        Connection conn = jdbConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ass2.`book` WHERE book.title=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(Book book) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "INSERT INTO ass2.`book`(title, genre, description, stock, authorname) " +
                    " VALUES ('" + book.getTitle() + "','" +
                    book.getGenre() + "','" +
                    book.getDescription() + "','" +
                    book.getStock() + "','" +
                    book.getAuthorName() + "')";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
