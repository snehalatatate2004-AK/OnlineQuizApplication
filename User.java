public class User {

    // Instance variables

    private String username;

    private String password;

    // Constructor

    public User(
            String username,
            String password) {

        this.username = username;

        this.password = password;
    }

    // Getter methods

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }
}
