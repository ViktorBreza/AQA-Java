package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DataBase {
    private static DataBase instance;
    private Connection connection;

    // Private constructor to prevent instantiation from other classes
    private DataBase() {
        connect();
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void connect() {
        Properties properties = new Properties();
        try {
            String filePath = "src/main/java/database/config.properties";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String url = String.format("jdbc:postgresql://%s:%s/%s", properties.getProperty("dbServer"), properties.getProperty("dbPort"), properties.getProperty("dbName"));
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String executeQuery(String sql) {
        StringBuilder result = new StringBuilder();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    result.append(resultSet.getString(i)).append("\t");
                }
                result.append("\n");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public List<Map<String, Object>> executeQueryWithResultList(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    row.put(columnName, columnValue);
                }
                resultList.add(row);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
