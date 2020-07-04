package com.jornah.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EasyDbClient {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        mysqlJdbc();
    }

    private static void ojdbc() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url="jdbc:oracle:thin:@192.168.206.105:1521:orcl";
        String name="sdpsgt";
        String pwd="sdpsgt1";
        String sql="select * from op_acct where rownum <10";

        try (Connection connection = DriverManager.getConnection(url, name, pwd)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("--licg---   : " +resultSet.getString("acct_id")  + "-----");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void mysqlJdbc() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://118.25.187.1:3306/sql_practice";
        String name="root";
        String pwd="${MYSQL_PWD}";
        String sql="select avg(degree) avgDegree from score where sno='123'";

        try (Connection connection = DriverManager.getConnection(url, name, pwd)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("--licg---   : " +resultSet.getString("avgDegree")  + "-----");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
