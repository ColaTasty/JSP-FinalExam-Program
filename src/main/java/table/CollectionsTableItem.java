/*
 * CREATE TIME 2019/6/9 20:22
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package table;

import abstracts.TableItem;
import global.config.DBConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 黎江
 */
public class CollectionsTableItem extends TableItem {
    public CollectionsTableItem(DBConnecter dbConnecter){
        super("collections");
        this.dbConnecter = dbConnecter;
    }

    public boolean collectPost(int post_id,int user_id){
        try {
            if (isCollected(post_id,user_id))
                throw new SQLException("已被收藏过了！");
            this.sql = "INSERT INTO "+this.getTableName()+"(post_id,user_id) VALUES(?,?)";
            this.preparedStatement = this.dbConnecter.getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1,post_id);
            this.preparedStatement.setInt(2,user_id);
            return this.preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelCollectPost(int post_id,int user_id){
        try {
            if (!isCollected(post_id,user_id))
                throw new SQLException("还没收藏过！");
            this.sql = "DELETE FROM "+this.getTableName()+" WHERE post_id=? AND user_id=?";
            this.preparedStatement = this.dbConnecter.getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1,post_id);
            this.preparedStatement.setInt(2,user_id);
            return this.preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isCollected(int post_id,int user_id){
        try {
            String sql = "SELECT * FROM collections WHERE post_id="+post_id+" AND user_id="+user_id;
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
