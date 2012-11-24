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

    @Override
    public User authenticate(String login,String pass) {
        logger.info("JdbcUserDao: Authenticating...");
        List<User> users = getSimpleJdbcTemplate().query("select * FROM USERS where \"login\"='"+login+ "' and \"password\"='"+pass+"'", new UserMapper());
    
        for (User user : users) 
            return user;
        return null;
    }

    @Override
    public List<User> getUsers() {
        logger.info("JdbcUserDao: getting list of all users");
        List<User> users = getSimpleJdbcTemplate().query(
                "select * from USERS",
                new JdbcUserDao.UserMapper());
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
