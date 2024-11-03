package repository;

import constants.myConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/OnlineQuizWebApp";
    private final String user = "postgres";
    private final String password = myConstants.MY_DB_PASSWORD;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            if (this.connection == null) {
                throw new SQLException("Failed to establish a connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}

