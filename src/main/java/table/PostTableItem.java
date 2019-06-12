package table;

import abstracts.TableItem;
import bean.AccountBean;
import bean.PostBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.jndi.toolkit.ctx.StringHeadTail;
import global.config.DBConnecter;
import javafx.geometry.Pos;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import sun.nio.cs.US_ASCII;

import java.awt.font.TextHitInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 杨子豪、温剑、黎江
 */
public class PostTableItem extends TableItem {

    public PostTableItem(DBConnecter dbConnecter) {
        super("post_enroll");
        this.dbConnecter = dbConnecter;
    }

    public static int countPosts() {
        return countMyPosts(-1);
    }

    public static int countMyPosts(int user_id) {
        try {
            String sql;
            if (user_id > 0)
                sql = "SELECT COUNT(post_id) AS total FROM post_enroll WHERE user_id=" + user_id;
            else
                sql = "SELECT COUNT(post_id) AS total FROM post_enroll WHERE status=1";
            Statement statement = DBConnecter.connecter.getStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("total");
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param user_id int
     * @param page    int
     * @return List<PostBean>
     * @author 黎江
     */
    public List<PostBean> getPostWriteByMyself(int user_id, int page) {
        return qList(user_id, page);
    }

    /**
     * @param page int
     * @return List<PostBean>
     * @author 黎江
     */
    public List<PostBean> getSquarePostList(int page) {
        return qList(-1, page);
    }

    private List<PostBean> qList(int user_id, int page) {
        try {
            page -= 1;
            if (page < 0)
                throw new SQLException("不是正确格式的页码");
            String DB_DSN = "jdbc:mysql://localhost:3306/jsp_datingcommunity?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false&useUnicode=true";
            String user = "root";
            String password = "mysqlmm233";
            String sql;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_DSN, user,password);
            PreparedStatement preparedStatement;
            if (user_id > 0) {
                sql = "SELECT post_id FROM " + this.getTableName() + " WHERE status=1 AND user_id=? ORDER BY post_id DESC LIMIT " + (page * 10) + ",10";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,user_id);
            } else {
                sql = "SELECT post_id FROM " + this.getTableName() + " WHERE status=1 ORDER BY post_id DESC LIMIT " + (page * 10) + ",10";
                preparedStatement = connection.prepareStatement(sql);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PostBean> posts = new ArrayList<>();
            while (resultSet.next()) {
                PostBean pb = this.getSpecifyPost(resultSet.getInt("post_id"));
                posts.add(pb);
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    /**
     * @param uid     int
     * @param content String
     * @param image   json
     * @param time    long
     * @return boolean
     */
    public boolean isRelease(int uid, String content, String image, long time) {
        try {
            //   long nowTime = System.currentTimeMillis() / 1000;
            this.sql = "INSERT INTO " + this.getTableName() + "(user_id,content,images_path,time) VALUES(?,?,?,?)";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1, uid);
            this.preparedStatement.setString(2, content);
            this.preparedStatement.setString(3, image);
            this.preparedStatement.setLong(4, time);
            return this.preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param post_id int
     * @return PostBean|null
     * @author 黎江
     */
    public PostBean getSpecifyPost(int post_id) {
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE post_id=?";
            this.preparedStatement = this.dbConnecter.getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1, post_id);
            this.resultSet = this.preparedStatement.executeQuery();
            if (!this.resultSet.next())
                throw new SQLException("查不到帖子！");
            this.resultSet.beforeFirst();
            PostBean callback = new PostBean();
            while (this.resultSet.next()) {
                callback.setPost_id(post_id);
                int user_id = this.resultSet.getInt("user_id") > 0 ? this.resultSet.getInt("user_id") : 0;
                callback.setUser_id(user_id);
                String content = this.resultSet.getString("content") != null ? this.resultSet.getString("content") : "";
                callback.setContent(content);
                int like_count = this.resultSet.getInt("like_count");
                callback.setLike_count(like_count);
                String images_path = this.resultSet.getString("images_path");
                callback.setImages_path(images_path);
                long time = this.resultSet.getLong("time");
                callback.setTime(time);
                callback.setStatus(this.resultSet.getInt("status"));
            }
            System.out.println(callback);
            return callback;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param post_id int
     * @param user_id int
     * @return boolean
     * @author 黎江
     */
    private boolean isDelete(int post_id, int user_id) {
        try {
            if (user_id > 0) {
                this.sql = "UPDATE " + this.getTableName() + " SET status=0 WHERE post_id=? AND user_id=?";
                this.preparedStatement.setInt(1, post_id);
                this.preparedStatement.setInt(2, user_id);
            } else {
                this.sql = "DELETE FROM " + this.getTableName() + " WHERE post_id=?";
                this.preparedStatement.setInt(1, post_id);
            }
            this.preparedStatement = this.dbConnecter.getPreparedStatement(this.sql);
            return this.preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param post_id int
     * @return boolean
     * @author 黎江
     */
    private boolean isDelete(int post_id) {
        return isDelete(post_id, -1);
    }

    /**
     * @param post_id int
     * @param user_id int
     * @return boolean
     * @author 黎江
     */
    public boolean deletePostByMyself(int post_id, int user_id) {
        return isDelete(post_id, user_id);
    }

    /**
     * @param post_id int
     * @return boolean
     * @author 黎江
     */
    public boolean deletePost(int post_id) {
        return isDelete(post_id);
    }
}
