package servlet.test;
/*
 * CREATE TIME 2019/6/12 14:44
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import table.test.TestTableItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Map;

import com.mysql.cj.jdbc.*;

/**
 * @author 黎江
 */
public class TestDBServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        TestTableItem testTableItem = new TestTableItem();
//        Map<String,String> tmp = testTableItem.startTest();
//        PrintWriter out = response.getWriter();
//        out.println(tmp.get("id"));
//        out.println(tmp.get("name"));
//        out.println(tmp.get("watermark"));
        try {
            Connection connection;
            Statement statement;
            ResultSet resultSet;
            String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
            String user = "root";
            String password = "mysqlmm233";
            Class.forName("com.mysql.cj.jdbc");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM test_table");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
