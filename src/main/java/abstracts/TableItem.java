package abstracts;

import global.config.DBConnecter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 黎江
 */
public abstract class TableItem {
    private String tableName;
    protected String sql;
    protected PreparedStatement preparedStatement;
    protected Statement statement;
    protected ResultSet resultSet;
    protected DBConnecter dbConnecter;

    public TableItem(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getSql() {
        return sql;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public DBConnecter getDbConnecter() {
        return dbConnecter;
    }

    public void close() {
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
