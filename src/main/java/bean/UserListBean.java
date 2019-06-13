/*
 * CREATE TIME 2019/6/10 0:17
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package bean;

import java.util.List;

/**
 * @author 吴裕彬、尹颂涛
 */
public class UserListBean {
    private int user_count = 0;
    private List<UserBean> usersBean;
    private int page = 1;

    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }

    public List<UserBean> getUsersBean() {
        return usersBean;
    }

    public void setUsersBean(List<UserBean> usersBean) {
        this.usersBean = usersBean;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
