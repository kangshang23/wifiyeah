package kang.zero.wifiyeah.config;

import java.sql.*;

public class MySqlConfig {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/wifiyeah";
    String userName = "root";
    String password = "1234";


    public Connection getConn() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("[JDBC Connector Driver 오류 : " + e.getMessage() + "]");
        } finally {
            Connection conn = DriverManager.getConnection(this.url, this.userName, this.password);
            return conn;
        }
    }


    // 디비 연결 테스트
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        final MySqlConfig dbConn = new MySqlConfig();
        Connection conn = dbConn.getConn();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from test2");

        resultSet.next();
        String name = resultSet.getString("name");
        System.out.println(name);

        resultSet.close();
        statement.close();
        conn.close();
    }
}

