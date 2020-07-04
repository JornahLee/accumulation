package com.jornah.util;

import com.google.common.base.CaseFormat;
import com.jornah.pojo.DbTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author licong
 * @date 2020/6/29 下午3:45
 * <p>
 * 将数据库字段  转化为 mybatis <sql id="">`id`,....</sql>
 * 可以从双列文件导入
 */
public class DbTableExtractUtil {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Map<String, List<String>> allTable = parseTableInfoFromFile();
        String dbName = "test";
        allTable.entrySet().stream().forEach(entry -> {
            System.out.println("tableName = " + entry.getKey());
            System.out.println("tableColCount = " + entry.getValue().size());
        });
        // 通过lambda把集合转对象，将数据库表 映射 为对象
        List<DbTable> tableList = allTable.entrySet().stream()
                .map(entry -> new DbTable("test", entry.getKey(), new LinkedHashSet<String>(entry.getValue())))
                .collect(Collectors.toList());
        // 下面代码 可以替换为 遍历tableList

        File output = createFile("/Users/licong/allPandaTableColumn.txt");
        try (FileWriter fw = new FileWriter(output, true)) {
            allTable.entrySet().stream().forEach(entry -> {
                try {
                    fw.append(entry.getKey()).append('\n');
                    fw.append("<sql id=\"").append(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getKey()))
                            .append("Columns\">");
                    StringBuilder sb = new StringBuilder();
                    entry.getValue().forEach(item -> {
                        sb.append("`").append(item).append("`").append(",");
                    });
                    if (sb.length() > 0) {
                        sb.setLength(sb.length() - 1);
                    }
                    fw.append(sb.toString());
                    fw.append("</sql>\n");

                    sb.setLength(0);
                    entry.getValue().forEach(item -> {
                        sb.append("${alias}.").append(item).append(",");
                    });
                    if (sb.length() > 0) {
                        sb.setLength(sb.length() - 1);
                    }
                    fw.append(sb.toString()).append("</sql>");
                    fw.append("\n\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.flush();
        }
    }

    private static File createFile(String fileName) throws IOException {
        File output = new File(fileName);
        if (output.exists()) {
            output.delete();
        }
        output.createNewFile();
        return output;
    }

    private static Map<String, List<String>> parseTableInfoFromFile() throws IOException, ClassNotFoundException {

        Map<String, List<String>> allTable = new TreeMap<>();
        File file = new File("/Users/licong/Downloads/panda_table_cols.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            String[] s = line.split("\t");
            allTable.putIfAbsent(s[0], new ArrayList<>());
            allTable.get(s[0]).add(s[1]);
            line = br.readLine();
        }
        return allTable;
    }

    private static Map<String, List<String>> parseTableInfoFromDB() throws IOException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/panda_test_dev?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
        String name = "root";
        String pwd = "${MYSQL_PWD}";
        String sql = "SELECT table_name, column_name FROM information_schema.COLUMNS " +
                "WHERE table_schema = 'panda_test_dev';";
        Map<String, List<String>> allTable = new TreeMap<>();
        try (Connection connection = DriverManager.getConnection(url, name, pwd)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tableName = resultSet.getString("table_name");
                String columnName = resultSet.getString("column_name");
                allTable.putIfAbsent(tableName, new ArrayList<>());
                allTable.get(tableName).add(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTable;
    }
}
