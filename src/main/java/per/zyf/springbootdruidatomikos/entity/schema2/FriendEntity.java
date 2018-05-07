package per.zyf.springbootdruidatomikos.entity.schema2;

/**
 * @author Kyle
 * @create 2018/5/6 14:24
 */
public class FriendEntity {
    private Integer id;
    private Integer userId;
    private Integer friendId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
