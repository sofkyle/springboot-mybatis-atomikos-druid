
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.zyf.springbootdruidatomikos.SpringBootDruidAtomikosApplication;
import per.zyf.springbootdruidatomikos.entity.schema2.FriendEntity;
import per.zyf.springbootdruidatomikos.entity.schema1.UserEntity;
import per.zyf.springbootdruidatomikos.service.UserFriendService;

/**
 * @author Kyle
 * @create 2018/5/5 21:53
 */
@SpringBootTest(classes = SpringBootDruidAtomikosApplication.class)
@RunWith(SpringRunner.class)
/**
 * @Transactional
 * 加上此注解事务会直接回滚
  */
public class TestAtomikos {

    @Autowired
    private UserFriendService userFriendService;

    @Test
    public void test() {
        System.out.println("begin.....");

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Jack");
        userEntity.setAge(20);

        FriendEntity friendEntity = new FriendEntity();
        friendEntity.setUserId(1);
        friendEntity.setFriendId(2);

        try {
            userFriendService.insertUserFriend(userEntity, friendEntity);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        System.out.println("end.....");
    }
}
