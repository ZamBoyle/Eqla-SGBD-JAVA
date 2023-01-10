package ConnectionPool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
    private Stack<Connection> availableConnections = new Stack<>();
    private int maxConnections = 10; //nombre maximum de connexions

    public ConnectionPool(String url, String user, String password) throws SQLException {
        for (int i = 0; i < maxConnections; i++) {
            availableConnections.push(DriverManager.getConnection(url, user, password));
        }
    }

    public Connection getConnection() {
        if (availableConnections.empty()) {
            throw new IllegalStateException("Sorry, no available connections");
        }
        return availableConnections.pop();
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            availableConnections.push(connection);
        }
    }
}
