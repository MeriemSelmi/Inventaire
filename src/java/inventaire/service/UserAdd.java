package inventaire.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UserAdd {
    protected final Log logger = LogFactory.getLog(getClass());
    private String name;
    private String firstName;
    private String mail;
    private String telephone;
    private String address;
    private String login;
    private String pass;
    private String type;

    
    public String getName() {
        return name;
    }

    public void setName(String lname) {
        this.name = lname;
        logger.info("UserAdd: name set to "+ name);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        logger.info("UserAdd: first name set to "+ firstName);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
        logger.info("UserAdd: mail set to "+ mail);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
        logger.info("UserAdd: telephone set to "+ telephone);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        logger.info("UserAdd: address set to "+ address);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        logger.info("UserAdd: login set to "+ login);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
        logger.info("UserAdd: pass set to "+ pass);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        logger.info("UserAdd: type set to "+ type);
    }
}
