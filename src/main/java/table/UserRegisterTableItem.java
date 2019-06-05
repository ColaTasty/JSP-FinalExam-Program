package table;

import abstracts.TableItem;
import bean.AccountBean;
import global.config.DBConnecter;

import java.sql.SQLException;

/**
 * @author 黎江
 */
public class UserRegisterTableItem extends TableItem {

    public UserRegisterTableItem(DBConnecter dbConnecter) {
        super("user_register");
        this.dbConnecter = dbConnecter;
    }

    /**
     * @param account  String
     * @param password String
     * @return boolean|null
     */
    public boolean isVaildAccount(String account, String password) {
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE servlet.account=? AND password=?";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1, account);
            this.preparedStatement.setString(2, password);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param accountBean AccountBean
     * @return boolean
     */
    public boolean register(AccountBean accountBean) {
        try {
            long nowTime = System.currentTimeMillis() / 1000;
            this.sql = "INSERT INTO " + this.getTableName() + "(servlet.account,password,email,mobile,register_time,alter_time) VALUES(?,?,?,?,?,?)";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1, accountBean.getAccount());
            this.preparedStatement.setString(2, accountBean.getPassword());
            this.preparedStatement.setString(3, accountBean.getEmail());
            this.preparedStatement.setString(4, accountBean.getMobile());
            this.preparedStatement.setLong(5, accountBean.getRegisterTime());
            this.preparedStatement.setLong(6, nowTime);
            return this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param account String
     * @return boolean
     */
    public boolean isExistAccount(String account) {
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE servlet.account=?";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1, account);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param email String
     * @return boolean
     */
    public boolean isExistEmail(String email) {
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE email=?";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1, email);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param mobile String
     * @return boolean
     */
    public boolean isExistMobile(String mobile){
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE mobile=?";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1, mobile);
            this.resultSet = this.preparedStatement.executeQuery();
            return this.resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param account String
     * @param oldPass String
     * @param newPass String
     * @return boolean
     */
    public boolean changePassword(String account,String oldPass,String newPass){
        try {
            this.sql = "UPDATE "+this.getTableName()+" SET password=? WHERE servlet.account=? AND password=?";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1,newPass);
            this.preparedStatement.setString(2,account);
            this.preparedStatement.setString(3,oldPass);
            int row = this.preparedStatement.executeUpdate();
            if (row <= 0)
                throw new SQLException("Error SQL Execution, SQL="+this.sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close(){
        try {
            if (this.resultSet != null)
                this.resultSet.close();
            if (this.preparedStatement != null)
                this.preparedStatement.close();
            if (this.statement != null)
                this.statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
