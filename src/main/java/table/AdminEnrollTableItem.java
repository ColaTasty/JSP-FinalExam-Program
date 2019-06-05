/**
 * CREATE TIME 2019/6/5 23:08
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package table;

import abstracts.TableItem;
import global.config.DBConnecter;

import java.sql.SQLException;

public class AdminEnrollTableItem extends TableItem {

    public AdminEnrollTableItem(DBConnecter dbConnecter) {
        super("admin_enroll");
        this.dbConnecter = dbConnecter;
    }

    public int getAdminId(int user_id) {
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE user_id=" + user_id;
            this.statement = this.dbConnecter.getStatement();
            this.resultSet = this.statement.executeQuery(this.sql);
            if (this.resultSet.next())
                return this.resultSet.getInt("admin_id");
            throw new SQLException("未找到管理员");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
