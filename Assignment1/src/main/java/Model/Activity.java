package Model;

public class Activity {

    private String name;
    private String date;


    public Activity() { }

    public Activity(String name, String date) {
        this.name = new String(name);
        this.date = new String(date);
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return this.name; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
