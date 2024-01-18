package com.example.freshthreads;




import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.sql.Statement;

public class DatabaseConnection {
    private Connection Db;
    private Statement statement;

    public Connection getConnection() {
        String DataBaseName = "freshthreads";
        String DataBaseUser = "root";
        String DataBasePassword = "tirtho117";
        String url = "jdbc:mysql://localhost:3306/";

        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Db = DriverManager.getConnection(url + DataBaseName, DataBaseUser, DataBasePassword);
                statement = Db.createStatement();
                } catch (SQLException e) {
                    throw new RuntimeException("Error connecting to the database", e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("JDBC driver not found", e);
        }

        return Db;
    }

    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (Db != null) {
                Db.close();
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        }
    }
}
