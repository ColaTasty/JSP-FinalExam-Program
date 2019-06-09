package table;

import abstracts.TableItem;
import bean.AccountBean;
import bean.UserBean;
import global.config.DBConnecter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author 黎江
 */
public class UserRegisterTableItem extends TableItem {

    public static List<String> integerColumn = new ArrayList<String>();

    public UserRegisterTableItem(DBConnecter dbConnecter) {
        super("user_register");
        this.dbConnecter = dbConnecter;
        integerColumn.add("user_id");
        integerColumn.add("gender");
        integerColumn.add("birthday");
        integerColumn.add("status");
        integerColumn.add("register_time");
        integerColumn.add("alter_time");
    }

    public static boolean isIntegerColumn(String column_key){
        return integerColumn.contains(column_key);
    }

    public static UserBean refreshUserBean(int user_id){
        try {
            String sql = "SELECT * FROM user_register WHERE user_id=?";
            PreparedStatement preparedStatement = DBConnecter.connecter.getPreparedStatement(sql);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                throw new Exception("未找到用户");
            UserBean userBean = new UserBean();
            userBean.setAccount(resultSet.getString("account"));
            userBean.setBirthday(resultSet.getLong("birthday"));
            userBean.setEmail(resultSet.getString("email"));
            userBean.setGender(resultSet.getInt("gender"));
            userBean.setMobile(resultSet.getString("mobile"));
            userBean.setStatus(resultSet.getInt("status"));
            userBean.setUser_id(resultSet.getInt("user_id"));
            userBean.setUser_name(resultSet.getString("user_name"));
            int admin_id = new AdminEnrollTableItem(DBConnecter.connecter).getAdminId(userBean.getUser_id());
            if (admin_id > 0) {
                userBean.setAdmin(true);
                userBean.setAdmin_id(admin_id);
            }
            return userBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserBean getUserBean(int user_id){
        return refreshUserBean(user_id);
    }

    public UserBean getUserBean(String account, String password) {
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE account=? AND password=?";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1, account);
            this.preparedStatement.setString(2, password);
            this.resultSet = this.preparedStatement.executeQuery();
            if (!this.resultSet.next())
                throw new Exception("未找到用户");
            UserBean userBean = new UserBean();
            userBean.setAccount(this.resultSet.getString("account"));
            userBean.setBirthday(this.resultSet.getLong("birthday"));
            userBean.setEmail(this.resultSet.getString("email"));
            userBean.setGender(this.resultSet.getInt("gender"));
            userBean.setMobile(this.resultSet.getString("mobile"));
            userBean.setStatus(this.resultSet.getInt("status"));
            userBean.setUser_id(this.resultSet.getInt("user_id"));
            userBean.setUser_name(this.resultSet.getString("user_name"));
            int admin_id = new AdminEnrollTableItem(DBConnecter.connecter).getAdminId(userBean.getUser_id());
            if (admin_id > 0) {
                userBean.setAdmin(true);
                userBean.setAdmin_id(admin_id);
            }
            return userBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param account  String
     * @param password String
     * @return boolean|null
     */
    public boolean isVaildAccount(String account, String password) {
        try {
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE account=? AND password=?";
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
            this.sql = "INSERT INTO " + this.getTableName() + "(account,user_name,password,email,mobile,register_time,alter_time) VALUES(?,?,?,?,?,?,?)";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1, accountBean.getAccount());
            this.preparedStatement.setString(2, accountBean.getUser_name());
            this.preparedStatement.setString(3, accountBean.getPassword());
            this.preparedStatement.setString(4, accountBean.getEmail());
            this.preparedStatement.setString(5, accountBean.getMobile());
            this.preparedStatement.setLong(6, accountBean.getRegisterTime());
            this.preparedStatement.setLong(7, nowTime);
            return this.preparedStatement.executeUpdate() > 0;
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
            this.sql = "SELECT * FROM " + this.getTableName() + " WHERE account=?";
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
     * @param mobile String
     * @return boolean
     */
    public boolean isExistMobile(String mobile) {
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
     * @param account String
     * @param oldPass String
     * @param newPass String
     * @return boolean
     */
    public boolean changePassword(String account, String oldPass, String newPass) {
        try {
            this.sql = "UPDATE " + this.getTableName() + " SET password=? WHERE account=? AND password=?";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setString(1, newPass);
            this.preparedStatement.setString(2, account);
            this.preparedStatement.setString(3, oldPass);
            int row = this.preparedStatement.executeUpdate();
            if (row <= 0)
                throw new SQLException("Error SQL Execution, SQL=" + this.sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param user_id int
     * @param keys HashMap<>
     * @return boolean
     */
    public boolean alterInformation(int user_id, HashMap<String, String> keys) {
        try {
            this.sql = "UPDATE " + this.getTableName() + " SET ";
            for (Map.Entry<String, String> item :
                    keys.entrySet()) {
                this.sql = this.sql  + item.getKey() + "=?,";
            }
            this.sql += "alter_time=?";
            this.sql += " WHERE user_id=?";
            System.out.println("修改用户信息SQL = " + this.sql);
            this.preparedStatement = this.dbConnecter.getPreparedStatement(this.sql);
            int paramer_idx = 1;
            for (Map.Entry<String, String> item :
                    keys.entrySet()) {
                if (isIntegerColumn(item.getKey()))
                    this.preparedStatement.setInt(paramer_idx,Integer.parseInt(item.getValue()));
                else
                    this.preparedStatement.setString(paramer_idx,item.getValue());
                paramer_idx++;
            }
            this.preparedStatement.setLong(paramer_idx,System.currentTimeMillis()/1000);
            this.preparedStatement.setInt(paramer_idx+1,user_id);
            return this.preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 杨子豪
     *
     * @param uid int
     * @return String|null
     */
    public static String getUserName(int uid) {
        try {
            String callback;
            String sql = "SELECT * FROM user_register WHERE user_id=?";
            PreparedStatement preparedStatement = DBConnecter.connecter.getPreparedStatement(sql);
            preparedStatement.setInt(1, uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            if (resultSet.next()) {
                callback = resultSet.getString("user_name");
                resultSet.close();
                return callback;
            } else {
                resultSet.close();
                throw new SQLException("未找到对应user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
