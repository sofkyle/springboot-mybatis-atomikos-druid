package per.zyf.springbootdruidatomikos.service;

import per.zyf.springbootdruidatomikos.entity.schema2.FriendEntity;
import per.zyf.springbootdruidatomikos.entity.schema1.UserEntity;

/**
 * @author Kyle
 * @create 2018/5/6 11:35
 */
public interface UserFriendService {
    void insertUserFriend(UserEntity userEntity, FriendEntity friendEntity) throws Exception;
}
