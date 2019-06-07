package table;

import abstracts.TableItem;
import bean.AccountBean;
import global.config.DBConnecter;

import java.sql.SQLException;

/**
 * @author 杨子豪，温剑
 */
public class PostTableItem extends TableItem {

    public PostTableItem(DBConnecter dbConnecter) {
        super("posts");
        this.dbConnecter = dbConnecter;
    }

    /**
     * @return boolean|null
     */
    public boolean isQuery() {
        try {
            this.sql = "SELECT * FROM " + this.getTableName();
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param uid     int
     * @param content String
     * @param image   json
     * @param time    long
     * @param status  int
     * @return boolean
     */
    public boolean isRelease(int uid, String content, String image, long time, int like_count, int status) {
        try {
            //   long nowTime = System.currentTimeMillis() / 1000;
            this.sql = "INSERT INTO " + this.getTableName() + "(user_id,content,images_path,time,like_count,status) VALUES(?,?,?,?,?,?)";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1, uid);
            this.preparedStatement.setString(2, content);
            this.preparedStatement.setString(3, image);
            this.preparedStatement.setLong(4, time);
            this.preparedStatement.setInt(5, like_count);
            this.preparedStatement.setLong(6, status);
            return this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
