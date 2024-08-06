package ultis;

import java.sql.*;

public class JdbcHelper {

    private static final String HOST_NAME = "localhost:1433";
    private static final String DB_NAME = "duan1_v2";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";
    private static final String URL = "jdbc:sqlserver://" + HOST_NAME + ";DatabaseName=" + DB_NAME + ";encrypt=true;trustServerCertificate=true";

    public static PreparedStatement preparedStatement(String sql, Object... args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps;
            if (sql.trim().startsWith("{")) {
                ps = conn.prepareCall(sql);
            } else {
                ps = conn.prepareStatement(sql);
            }
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement ps = preparedStatement(sql, args);
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String sql, Object... args) {
        try (PreparedStatement ps = preparedStatement(sql, args)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
