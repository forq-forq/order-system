package domain;

public class Customer {
    private final String id;       // customer id
    private final String email;    // email

    public Customer(String id, String email) {
        this.id = id;
        this.email = email;
    }
    public String getId() { return id; }
    public String getEmail() { return email; }
}
