package table;

import abstracts.TableItem;
import bean.AccountBean;
import bean.PostBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import global.config.DBConnecter;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 杨子豪，温剑
 */
public class PostTableItem extends TableItem {

    public PostTableItem(DBConnecter dbConnecter) {
        super("posts");
        this.dbConnecter = dbConnecter;
    }

    public static int countPosts() {
        try {
            String sql = "SELECT COUNT(post_id) AS total FROM posts WHERE status=1";
            Statement statement = DBConnecter.connecter.getStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;
            while (resultSet.next()){
                count = resultSet.getInt("total");
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @return boolean|null
     */
    public String[][] isQuery() {
        try {
            this.sql = "SELECT * FROM " + this.getTableName();
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            ResultSetMetaData metaData = this.resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowNumber = this.resultSet.getRow();
            String[][] tableResult = new String[rowNumber][columnCount];
            this.resultSet.beforeFirst();
            int i = 0;
            while (this.resultSet.next()) {
                for (int k = 0; k < columnCount; k++)
                    tableResult[i][k] = this.resultSet.getString(k + 1);
                i++;
            }
            return tableResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author 黎江
     * @param page int
     * @return List|null
     */
    public List<PostBean> isQuery(int page) {
        try {
            page -= 1;
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE status=1 LIMIT " + (page*10) + ",10";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
            if (!this.resultSet.next())
                throw new SQLException("主贴列表查询错误");
            List<PostBean> posts = new ArrayList<PostBean>();
            this.resultSet.beforeFirst();
            while (this.resultSet.next()) {
                PostBean pb = new PostBean();
                int post_id = this.resultSet.getInt("post_id") > 0 ? this.resultSet.getInt("post_id") : 0;
                pb.setPost_id(post_id);
                int user_id = this.resultSet.getInt("user_id") > 0 ? this.resultSet.getInt("user_id") : 0;
                pb.setUser_id(user_id);
                String content = this.resultSet.getString("content") != null ? this.resultSet.getString("content") : "";
                pb.setContent(content);
                int like_count = this.resultSet.getInt("like_count");
                pb.setLike_count(like_count);
                JSONArray images_path = this.resultSet.getString("images_path") != null ? JSONArray.parseArray(this.resultSet.getString("images_path")) : null;
                pb.setImages_path(images_path);
                long time = this.resultSet.getLong("time");
                pb.setTime(time);
                pb.setStatus(this.resultSet.getInt("status"));
                posts.add(pb);
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
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
}
