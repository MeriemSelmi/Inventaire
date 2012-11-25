package inventaire.repository;

import inventaire.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcUserDao extends SimpleJdbcDaoSupport implements UserDao {

    protected final Log logger = LogFactory.getLog(getClass());
    private static final String SQL_AUTHENTICATE="SELECT * FROM USERS WHERE \"login\"=? and \"password\"=?";
    private static final String SQL_GETUSERS="SELECT * FROM USERS";
    
    private static final String SQL_UPDATENAME="UPDATE USERS SET \"name\"=? WHERE \"id\" =?";
    private static final String SQL_UPDATEFIRSTNAME="UPDATE USERS SET \"firstname\"=? WHERE \"id\" =?";
    private static final String SQL_UPDATEMAIL="UPDATE USERS SET \"mail\"=? WHERE \"id\" =?";
    private static final String SQL_UPDATETELEPHONE="UPDATE USERS SET \"telephone\"=? WHERE \"id\" =?";
    private static final String SQL_UPDATEADDRESS="UPDATE USERS SET \"address\"=? WHERE \"id\" =?";
    private static final String SQL_UPDATELOGIN="UPDATE USERS SET \"login\"=? WHERE \"id\" =?";
    private static final String SQL_UPDATEPASS="UPDATE USERS SET \"password\"=? WHERE \"id\" =?";
    private static final String SQL_UPDATETYPE="UPDATE USERS SET \"type\"=? WHERE \"id\" =?";
    
    @Override
    public User authenticate(String login,String pass) {
        logger.info("JdbcUserDao: Authenticating...");
        List<User> users =getSimpleJdbcTemplate().query( SQL_AUTHENTICATE, new UserMapper(), login, pass);
        for (User user : users) 
            return user;
        return null;
    }

    @Override
    public List<User> getUsers() {
        logger.info("JdbcUserDao: Getting list of all users");
        List<User> users = getSimpleJdbcTemplate().query(SQL_GETUSERS, new JdbcUserDao.UserMapper());
        return users;
    }

    @Override
    public void update(int id, String name, String firstName, String mail, String telephone, String address, String login, String pass, String type) {
        logger.info("JdbcUserDao: Updating user...");
        if(isNotEmptyParameter(name))
            updateName(id,  name);
        if(isNotEmptyParameter(firstName))
            updateFirstName(id,  firstName);
        if(isNotEmptyParameter(mail))
            updateMail(id,  mail);
        if(isNotEmptyParameter(telephone))
            updateTelephone(id,  telephone);
        if(isNotEmptyParameter(address))
            updateAddress(id,  address);
        if(isNotEmptyParameter(login))
            updateLogin(id,  login);
        if(isNotEmptyParameter(pass))
            updatePass(id,  pass);
        if(isNotEmptyParameter(type))
            updateType(id,  type);
        
    }
   
    private boolean isNotEmptyParameter(String param){
        return !(param == null || param.trim().length() == 0 );
    }
    
    private void updateName(int id,  String name){
        getSimpleJdbcTemplate().update(SQL_UPDATENAME, name, id);
    }
    
    private void updateFirstName(int id, String firstName){
        getSimpleJdbcTemplate().update(SQL_UPDATEFIRSTNAME, firstName, id);
    }
    
    private void updateMail(int id, String mail){
        getSimpleJdbcTemplate().update(SQL_UPDATEMAIL, mail, id);
    }
    
    private void updateTelephone(int id, String telephone){
        getSimpleJdbcTemplate().update(SQL_UPDATETELEPHONE, telephone, id);
    }
    
    private void updateAddress(int id, String address){
        getSimpleJdbcTemplate().update(SQL_UPDATEADDRESS, address, id);
    }
    
    private void updateLogin(int id, String login){
        getSimpleJdbcTemplate().update(SQL_UPDATELOGIN, login, id);
    }
    
    private void updatePass(int id, String pass){
        getSimpleJdbcTemplate().update(SQL_UPDATEPASS, pass, id);
    }

    private void updateType(int id, String type){
        getSimpleJdbcTemplate().update(SQL_UPDATETYPE, type, id);
    }
    
    
    
    
    
    private static class UserMapper implements ParameterizedRowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setFirstName(rs.getString("firstname"));
            user.setAddress(rs.getString("address"));
            user.setLogin(rs.getString("login"));
            user.setPass(rs.getString("password"));
            user.setType(rs.getString("type"));
            user.setTelephone(rs.getString("telephone"));
            user.setMail(rs.getString("mail"));

            return user;
        }
    }
}
