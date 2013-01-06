package inventaire.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.*;

@Entity
@Table(name = "USERS", schema = "ROOT", uniqueConstraints =
@UniqueConstraint(columnNames = "LOGIN"))
public class User implements Serializable {

    public static final String USER_ROLE_ADMINISTRATOR = "Administrator";
    public static final String USER_ROLE_STOREKEEPER = "Storekeeper";
    public static final Map<String, String> USER_ROLES = new LinkedHashMap<String, String>() {{
            put("", "");
            put("Administrator", "Administrator");
            put("Storekeeper", "Storekeeper");
    }};
    
    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String telephone;
    private String address;
    private String login;
    private String password;
    private String role;

    public User() {
    }

    public User(int id, String lastName, String firstName, String login, String password, String role) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(int id, String lastName, String firstName, String email, String telephone, String address, String login, String password, String role) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "LASTNAME", nullable = false, length = 25)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "FIRSTNAME", nullable = false, length = 25)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "EMAIL", length = 25)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "TELEPHONE", length = 25)
    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "ADDRESS", length = 25)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "LOGIN", unique = true, nullable = false, length = 25)
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "PASSWORD", nullable = false, length = 25)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ROLE", nullable = false, length = 25)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID: " + id + "\n");
        buffer.append("Name: " + lastName + "\n");
        buffer.append("Firstname: " + firstName + "\n");
        buffer.append("Mail: " + email + "\n");
        buffer.append("Telephone: " + telephone + "\n");
        buffer.append("Adress: " + address + "\n");
        buffer.append("Login: " + login + "\n");
        buffer.append("Password: " + password + "\n");
        buffer.append("Role: " + role + "\n");
        return buffer.toString();
    }
}