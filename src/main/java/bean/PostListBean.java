/*
 * CREATE TIME 2019/6/9 14:54
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 洪颖森、郑枫超
 */
public class PostListBean {
    private List<PostBean> posts;
    private int page = 1;
    private int posts_total = 0;

    public List<PostBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostBean> posts) {
        this.posts = posts;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPosts_total() {
        return posts_total;
    }

    public void setPosts_total(int posts_total) {
        this.posts_total = posts_total;
    }
}
