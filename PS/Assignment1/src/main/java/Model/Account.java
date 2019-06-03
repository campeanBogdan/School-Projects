package Model;

public class Account {

    private Integer id;
    private String type;
    private Integer money;
    private String creationDate;
    private Integer clientId;


    public Account() {}

    public Account(String type, Integer money, String creationDate) {
        this.type = type;
        this.money = money;
        this.creationDate = creationDate;

    }

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Integer getMoney() { return money; }

    public void setMoney(Integer money) { this.money = money; }

    public String getCreationDate() { return creationDate; }

    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }

    public Integer getClientId() { return clientId; }

    public void setClientId(Integer clientId) { this.clientId = clientId; }
}
