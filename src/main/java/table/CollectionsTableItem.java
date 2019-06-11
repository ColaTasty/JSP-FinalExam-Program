/*
 * CREATE TIME 2019/6/9 20:22
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package table;

import abstracts.TableItem;
import bean.PostBean;
import bean.PostListBean;
import global.ResponseToClient;
import global.config.DBConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 黎江
 */
public class CollectionsTableItem extends TableItem {
    public CollectionsTableItem(DBConnecter dbConnecter) {
        super("collections");
        this.dbConnecter = dbConnecter;
    }

    public static int countMyCollections(int user_id){
        try {
            String sql = "SELECT COUNT(user_id) AS total FROM collections";
            Statement statement = DBConnecter.connecter.getStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new SQLException("没有收藏");
            int callback = 0;
            resultSet.beforeFirst();
            while (resultSet.next()){
                callback = resultSet.getInt("total");
            }
            return callback;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public boolean collectPost(int post_id, int user_id) {
        try {
            if (isCollected(post_id, user_id))
                throw new SQLException("已被收藏过了！");
            this.sql = "INSERT INTO " + this.getTableName() + "(post_id,user_id) VALUES(?,?)";
            this.preparedStatement = this.dbConnecter.getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1, post_id);
            this.preparedStatement.setInt(2, user_id);
            return this.preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelCollectPost(int post_id, int user_id) {
        try {
            if (!isCollected(post_id, user_id))
                throw new SQLException("还没收藏过！");
            this.sql = "DELETE FROM " + this.getTableName() + " WHERE post_id=? AND user_id=?";
            this.preparedStatement = this.dbConnecter.getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1, post_id);
            this.preparedStatement.setInt(2, user_id);
            return this.preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PostListBean getMyCollectedPost(int user_id, int page) {
        try {
            page -= 1;
            if (page < 0)
                throw new SQLException("不是正确格式的页码");
            this.sql = "SELECT post_id FORM " + this.getTableName() + " WHERE user_id=" + user_id + " ORDER BY collection_id DESC LIMIT " + (page * 10) + ",10";
            this.statement = this.dbConnecter.getStatement();
            this.resultSet = this.statement.executeQuery(this.sql);
            if (!this.resultSet.next())
                throw new SQLException("你还没有收藏帖子");
            this.resultSet.beforeFirst();
            List<PostBean> postBeanList = new ArrayList<PostBean>();
            PostListBean callback = new PostListBean();
            PostTableItem postTableItem = new PostTableItem(this.dbConnecter);
            while (this.resultSet.next()) {
                postBeanList.add(postTableItem.getSpecifyPost(this.resultSet.getInt("post_id")));
            }
            callback.setPosts(postBeanList);
            callback.setPosts_total(countMyCollections(user_id));
            callback.setPage(page);
            return callback;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isCollected(int post_id, int user_id) {
        try {
            String sql = "SELECT * FROM collections WHERE post_id=" + post_id + " AND user_id=" + user_id;
            Statement statement = DBConnecter.connecter.getStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.beforeFirst();
            boolean callback = resultSet.next();
            resultSet.close();
            return callback;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
