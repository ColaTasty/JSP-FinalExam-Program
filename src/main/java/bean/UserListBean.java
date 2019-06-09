/*
 * CREATE TIME 2019/6/10 0:17
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package bean;

public class UserListBean {
    private int user_count = 0;
    private UserBean userBean;
    private int page = 0;

    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
