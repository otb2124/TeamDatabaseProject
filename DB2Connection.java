package finalExam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB2Connection {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/facresearchdb?useSSL=false";
    private static final String USER = "bf4680";
    private static final String PASS = "rit2023";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
