package per.zyf.springbootdruidatomikos.dao.schema1;

import org.springframework.stereotype.Repository;
import per.zyf.springbootdruidatomikos.entity.schema1.UserEntity;

/**
 * @author glorychou
 * @create 2018/5/6 14:21
 */
@Repository
public interface UserDAO {
    void insert(UserEntity userEntity);
}
