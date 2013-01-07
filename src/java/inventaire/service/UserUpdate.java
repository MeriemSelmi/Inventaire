package inventaire.service;

import inventaire.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UserUpdate {
    protected final Log logger = LogFactory.getLog(getClass());
    private String lastName;
    private String firstName;
    private String email;
    private String telephone;
    private String address;
    private String login;
    private String password;
    private String role;

    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lname) {
        this.lastName = lname;
        logger.info("UserUpdate: name set to "+ lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        logger.info("UserUpdate: first name set to "+ firstName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        logger.info("UserUpdate: email set to "+ email);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
        logger.info("UserUpdate: telephone set to "+ telephone);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        logger.info("UserUpdate: address set to "+ address);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        logger.info("UserUpdate: login set to "+ login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        logger.info("UserUpdate: password set to "+ password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        logger.info("UserUpdate: role set to "+ role);
    }
    
    public User getUser(){
        return new User(-1, lastName, firstName, email, telephone, address, login, password, role);
    }
}
