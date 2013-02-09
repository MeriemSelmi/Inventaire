package inventory.service.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserFind {
    
    protected final Log logger = LogFactory.getLog(getClass());
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        logger.info("UserFind: keyword set to "+ keyword);
    }
    
    
}
