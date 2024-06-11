package db;

import java.sql.*;

public class Database {
    private static String filePath = "/Users/tnifl/Desktop/NationOfBeadal/nation_of_baedal.db";
    private static java.sql.Connection connection = null;

    public static Connection conn() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static ResultSet query(String sql) throws SQLException {
        PreparedStatement statement = conn().prepareStatement(sql);
        return statement.executeQuery();
    }
}
