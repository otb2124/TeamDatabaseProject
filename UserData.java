package finalExam;

public class UserData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String choice;

    public UserData(String firstName, String lastName, String email, String password, String choice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.choice = choice;
    }

    // Getters for all fields
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getChoice() {
        return choice;
    }
}
