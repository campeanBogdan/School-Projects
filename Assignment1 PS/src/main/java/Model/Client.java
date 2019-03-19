package Model;

public class Client {

    private Integer id;
    private String firstName;
    private String lastName;
    private String cardId;
    private String CNP;
    private String address;


    public Client() {}

    public Client(String firstName, String lastName, String cardId, String CNP, String address) {
        this.id = new Integer(0);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardId = cardId;
        this.CNP = CNP;
        this.address = address;
    }

    public void setId(Integer id) { this.id = id; }

    public Integer getId() { return this.id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCardId() { return cardId; }

    public void setCardId(String cardId) { this.cardId = cardId; }

    public String getCNP() { return CNP; }

    public void setCNP(String CNP) { this.CNP = CNP; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }
}
