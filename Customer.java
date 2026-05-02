package project;

public class Customer {

    private int id;
    private String name;
    private String phone;

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name.isEmpty() ? "Bilinmiyor" : name;
        this.phone = phone.isEmpty() ? "Yok" : phone;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | İsim: " + name + " | Tel: " + phone;
    }
}
