/*
 * CREATE TIME 2019/6/8 12:15
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PostBean {
    private int response_id;
    private int post_id;
    private int user_id;
    private String content;
    private String images_path;
    private long time;
    private int like_count;
    private int status;

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages_path() {
        return images_path;
    }

    public void setImages_path(String images_path) {
        this.images_path = images_path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
