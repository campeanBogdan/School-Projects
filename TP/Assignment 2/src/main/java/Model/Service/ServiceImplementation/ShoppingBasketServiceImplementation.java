package Model.Service.ServiceImplementation;

import Model.Model.Book;
import Model.Model.Client;
import Model.Model.ClientBook;
import Model.Repostiroy.RepositoryImplementation.ClientBookRepositoryImplementation;
import Model.Repostiroy.RepositoryImplementation.ShoppingBasketRepositoryImplementation;

import java.util.List;

public class ShoppingBasketServiceImplementation {

    private final ShoppingBasketRepositoryImplementation shoppingBasketRepositoryImplementation;


    public ShoppingBasketServiceImplementation(ShoppingBasketRepositoryImplementation shoppingBasketRepositoryImplementation) {
        this.shoppingBasketRepositoryImplementation = shoppingBasketRepositoryImplementation;
    }

    // return stock from client's books
    public void setStock(Client client, List<Book> books) {
        books.forEach(book -> {
            ClientBook cb = shoppingBasketRepositoryImplementation.findByIdClientIdBook(client.getId(), book.getId());
            book.setStock(cb.getStock());
        });
    }
}
