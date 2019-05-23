package Model.Service.ServiceImplementation.Report;

import Model.Model.Book;

import java.util.List;

public interface Report {

    void exportReport(String loginAdmin, List<Book> booksOutOfStock);
}
