/*
 * CREATE TIME 2019/6/12 14:33
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package table.test;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTableItem {
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;
    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private String user = "root";
    private String password = "mysqlmm233";

    public TestTableItem() {
        try {
            Class.forName("com.mysql.cj.jdbc");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String,String> startTest() {
        try {
            String sql = "SELECT * FROM test_table";
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);
            System.out.println(this.statement.isClosed());
            System.out.println(this.resultSet.isClosed());
            System.out.println(this.connection.isClosed());
            Map<String,String> callback = new HashMap<String, String>();
            while (this.resultSet.next()) {
                int id = this.resultSet.getInt("id");
                System.out.println("id : " + this.resultSet.getInt("id"));
                callback.put("id",""+id+"");
                String name = this.resultSet.getString("name");
                System.out.println("name : " + this.resultSet.getString("name"));
                callback.put("name",name);
                String watermark = this.resultSet.getString("water_mark");
                System.out.println("watermark : " + this.resultSet.getString("water_mark"));
                callback.put("watermark",watermark);
            }
            return callback;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
