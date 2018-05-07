package per.zyf.springbootdruidatomikos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.zyf.springbootdruidatomikos.dao.schema1.UserDAO;
import per.zyf.springbootdruidatomikos.dao.schema2.FriendDAO;
import per.zyf.springbootdruidatomikos.entity.schema2.FriendEntity;
import per.zyf.springbootdruidatomikos.entity.schema1.UserEntity;
import per.zyf.springbootdruidatomikos.service.UserFriendService;


/**
 * @author Kyle
 * @create 2018/5/6 11:36
 */
@Service
public class UserFriendServiceImpl implements UserFriendService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FriendDAO friendDAO;

    @Override
    public void insertUserFriend(UserEntity userEntity, FriendEntity friendEntity) throws Exception {
        userDAO.insert(userEntity);
        // 模拟异常，事物回滚
        // int a = 20 / 0;
        friendDAO.insert(friendEntity);
    }
}
