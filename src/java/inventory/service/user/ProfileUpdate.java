package inventory.service.user;

import inventory.domain.User;
import javax.validation.constraints.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class ProfileUpdate {
    protected final Log logger = LogFactory.getLog(getClass());
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String firstName;
    @Email
    private String email;
    @Pattern(regexp="(^[+]?[0-9]{8,}$)|(^$)")
    private String telephone;
    private String address;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    private String error;
    
    public User getUser(){
        return new User(-1, lastName, firstName, email, telephone, address, login, password, "");
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        logger.info("ProfileUpdate: name set to "+ lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        logger.info("ProfileUpdate: first name set to "+ firstName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        logger.info("ProfileUpdate: email set to "+ email);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
        logger.info("ProfileUpdate: telephone set to "+ telephone);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        logger.info("ProfileUpdate: address set to "+ address);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        logger.info("ProfileUpdate: login set to "+ login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        logger.info("ProfileUpdate: password set to "+ password);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
