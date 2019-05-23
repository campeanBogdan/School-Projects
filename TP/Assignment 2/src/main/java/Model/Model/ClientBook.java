package Model.Model;

public class ClientBook {

    private Integer id;
    private Integer idClient;
    private Integer idBook;
    private Integer stock;


    public ClientBook() { }

    public ClientBook(Integer idClient, Integer idBook, Integer stock) {
        this.idClient = idClient;
        this.idBook = idBook;
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }
}
