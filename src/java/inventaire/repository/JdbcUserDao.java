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
    private static final String SQL_AUTHENTICATE = "SELECT * FROM USERS WHERE \"login\"=? and \"password\"=?";
    private static final String SQL_LIST = "SELECT * FROM USERS";
    private static final String SQL_UPDATE1 = "UPDATE USERS SET \"";
    private static final String SQL_UPDATE2 = "\"=? WHERE \"id\" =?";
    private static final String SQL_ADD = "INSERT INTO ROOT.USERS (\"name\", \"firstname\", \"mail\", \"telephone\", \"address\", \"login\", \"password\", \"type\") VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM USERS WHERE \"id\" = ?";
    private static final String SQL_FIND = "SELECT * FROM USERS WHERE \"name\" LIKE ? OR \"firstname\" LIKE ? OR \"mail\" LIKE ? OR  \"telephone\" LIKE ? OR  \"address\" LIKE ? OR \"login\" LIKE ? OR \"password\" LIKE ? OR  \"type\" LIKE ?";
 
    @Override
    public User authenticate(String login, String pass) {
        logger.info("JdbcUserDao: Authenticating...");
        List<User> users = getSimpleJdbcTemplate().query(SQL_AUTHENTICATE, new UserMapper(), login, pass);
        for (User user : users) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        logger.info("JdbcUserDao: Getting list of all users");
        List<User> users = getSimpleJdbcTemplate().query(SQL_LIST, new JdbcUserDao.UserMapper());
        return users;
    }

    @Override
    public void update(int id, String name, String firstName, String mail, String telephone, String address, String login, String pass, String type) {
        logger.info("JdbcUserDao: Updating user...");
        updateColumnIfNotEmpty(id, "name", name);
        updateColumnIfNotEmpty(id, "firstname", firstName);
        updateColumnIfNotEmpty(id, "mail", mail);
        updateColumnIfNotEmpty(id, "telephone", telephone);
        updateColumnIfNotEmpty(id, "address", address);
        updateColumnIfNotEmpty(id, "login", login);
        updateColumnIfNotEmpty(id, "password", pass);
        updateColumnIfNotEmpty(id, "type", type);
    }

    private void updateColumnIfNotEmpty(int id, String columnName, String columnValue) {
        if (isNotEmptyParameter(columnValue)) {
            String sqlUpdateFull = SQL_UPDATE1 + columnName + SQL_UPDATE2;
            getSimpleJdbcTemplate().update(sqlUpdateFull, columnValue, id);
        }
    }
    
    private boolean isNotEmptyParameter(String param) {
        return !(param == null || param.trim().length() == 0);
    }

    @Override
    public void add(String name, String firstName, String mail, String telephone, String address, String login, String pass, String type) {
        getSimpleJdbcTemplate().update(SQL_ADD, name, firstName, mail, telephone, address, login, pass, type);
    }

    @Override
    public void delete(int id) {
        getSimpleJdbcTemplate().update(SQL_DELETE, id);
    }

    @Override
    public List<User> findUsers(String keyword) {
        logger.info("JdbcUserDao: Getting list of all users corresponding to " + keyword);
        List<User> users = getSimpleJdbcTemplate().query(SQL_FIND, new JdbcUserDao.UserMapper(), "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%");
        return users;
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
