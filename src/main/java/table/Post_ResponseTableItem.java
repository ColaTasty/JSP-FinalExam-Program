package table;

import abstracts.TableItem;
import bean.PostBean;
import com.alibaba.fastjson.JSONArray;
import global.config.DBConnecter;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨子豪，温剑
 */
public class Post_ResponseTableItem extends TableItem {

    public Post_ResponseTableItem(DBConnecter dbConnecter) {
        super("post_responses");
        this.dbConnecter = dbConnecter;
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
     *
     * @param post_id int
     * @param page int
     * @return List<PostBean>
     */
    public List<PostBean> isQuery(int post_id, int page) {
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE status=1 AND post_id=" + post_id + " LIMIT " + page + ",10";
            this.statement = this.dbConnecter.getStatement();
            this.resultSet = this.statement.executeQuery(this.sql);
            if (!this.resultSet.next())
                throw new SQLException("未找到更多回复");
            this.resultSet.beforeFirst();
            List<PostBean> response_posts = new ArrayList<PostBean>();
            while (this.resultSet.next()){
                PostBean pb = new PostBean();
                int response_id = this.resultSet.getInt("response_id") > 0 ? this.resultSet.getInt("post_id") : 0;
                pb.setPost_id(response_id);
                int user_id = this.resultSet.getInt("user_id") > 0 ? this.resultSet.getInt("user_id") : 0;
                pb.setUser_id(user_id);
                String content = this.resultSet.getString("content") != null ? this.resultSet.getString("content") : "";
                pb.setContent(content);
                long time = this.resultSet.getLong("time");
                pb.setTime(time);
                pb.setStatus(this.resultSet.getInt("status"));
                response_posts.add(pb);
            }
            return response_posts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return boolean
     */
    public boolean isRelease(int pid, int uid, String content, long time) {
        try {
            this.sql = "INSERT INTO " + this.getTableName() + "(post_id,user_id,content,time) VALUES(?,?,?,?)";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1, pid);
            this.preparedStatement.setInt(2, uid);
            this.preparedStatement.setString(3, content);
            this.preparedStatement.setLong(4, time);
            return this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
