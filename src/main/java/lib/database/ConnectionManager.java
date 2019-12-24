package lib.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class ConnectionManager {

	private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

	private static Map<String,Connection> connections = null;

	private ConnectionManager() {
		if (connections == null) {
			connections = new HashMap<String,Connection>();
		}
	}

	public static ConnectionManager getInstance() {
		return new ConnectionManager();
	}

	public void addConnection(String connectionName, String url,String user,String password) {
		Properties props = new Properties();
		props.setProperty("user",user);
		props.setProperty("password",password);
		addConnection(connectionName,url,props);
	}

	public void addConnection(String connectionName, String url,Properties prop) {
		// 登録および初回接続
		try {
			Connection connection = DriverManager.getConnection(url,prop);
			connections.put(connectionName, connection);
		} catch (SQLException sqle) {
			sqle.getMessage();
		}
	}

	public void removeConnection(String connectionName) {
		if (connections == null) {
			logger.severe("connections == null");
			return;
		} else {
			// 切断および削除
			Connection connection = connections.get(connectionName);
			if (connection != null) {
				logger.severe("connection != null");
				try {
					if (!connection.isClosed()) {
						logger.severe("connection not close");
						connection.close();
					}
					while (connection.isValid(0)) {
						logger.severe("connection is alive");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					connections.remove(connectionName);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Connection getConnection(String connectionName) {
		Connection connection = connections.get(connectionName);
		if (connection != null) {
//			// 切断やタイムアウトされている場合、再接続を行う。
//			if (connection.isClosed()) {
//				if (connection.isValid(0)) {
//					logger.severe("connection is alive");
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				addConnection(connectionName, url, user, password);
//			}
			return connection;
		} else {
			return null;
		}
	}

	public void destroy() {
		if (connections == null) {
			logger.severe("connections == null");
			return;
		} else {
			// 切断および削除
			Set<String> keyset = connections.keySet();
			for (String key : keyset) {
				removeConnection(key);
			}
		}
	}

	public static void main(String[] args) {
		ConnectionManager manager = ConnectionManager.getInstance();
		manager.addConnection("empdb","jdbc:postgresql://localhost:5432/test_db", "test_user","password");
		manager.destroy();
	}
}
