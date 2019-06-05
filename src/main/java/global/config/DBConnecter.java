package global.config;


import interfaces.config.DBConfig;

import java.sql.*;

/**
 * @author 黎江
 */
public class DBConnecter implements DBConfig {
    public static DBConnecter connecter = new DBConnecter();
    private Connection conn;
    private String ConnectionString;
    private String user;
    private String password;

    /**
     * @param connectionString String
     * @param user             String
     * @param password         String
     */
    public DBConnecter(String connectionString, String user, String password) {
        String driver = "com.mysql.cj.jdbc.Driver";
        this.ConnectionString = connectionString;
        this.user = user;
        this.password = password;
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.ConnectionString, this.user, this.password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private DBConnecter() {
        String driver = "com.mysql.cj.jdbc.Driver";
        this.ConnectionString = DB_DSN;
        this.user = DB_USER;
        this.password = DB_PASSWORD;
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.ConnectionString, this.user, this.password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param sql String
     * @return PreparedStatement|null
     */
    public PreparedStatement getPreparedStatement(String sql) {
        try {
            return this.conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return Statement|null
     */
    public Statement getStatement() {
        try {
            return this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConn() {
        return conn;
    }

    public String getConnectionString() {
        return ConnectionString;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
