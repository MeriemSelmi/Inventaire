package inventaire.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class User implements Serializable{

    private Integer id;
    private String name;
    private String firstName;
    private String mail;
    private String telephone;
    private String address;
    private String login;
    private String pass;
    private String type;
    static private Map<String,String> userTypes;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String lname) {
        this.name = lname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Map <String,String> getUserTypes(){
        if(userTypes == null) {
            userTypes = new LinkedHashMap<String,String>();
            userTypes.put("", "");
            userTypes.put("Administrator", "Administrator");
            userTypes.put("Storekeeper", "Storekeeper");
        }
        return userTypes;
    }
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID: " + id+ "\n");
        buffer.append("Name: " + name + "\n");
        buffer.append("Firstname: " + firstName+ "\n");
        buffer.append("Mail: " + mail+ "\n");
        buffer.append("Telephone: " + telephone+ "\n");
        buffer.append("Adress: " + address+ "\n");
        buffer.append("Login: " + login+ "\n");
        buffer.append("Password: " + pass+ "\n");
        buffer.append("Type: " + type+ "\n");
        return buffer.toString();
    }
    
}