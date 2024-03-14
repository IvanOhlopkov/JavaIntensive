package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {

    public static Connection connect() {

        try {
            var url = DatabaseConfig.getDbUrl();
            var user = DatabaseConfig.getDbUsername();
            var pass = DatabaseConfig.getDbPassword();
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
