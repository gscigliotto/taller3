package tc3.tp3.utils.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
	private static final String ERR_CONFIG_CANNOT_BE_NULL = "Config no puede ser null";

	private void setDbconfig(DBConfig dbconfig) {
		this.dbconfig = dbconfig;
	}

	private DBConfig dbconfig;

	public DBManager(DBConfig dbconfig) throws IllegalArgumentException {
		if (dbconfig == null)
			throw new IllegalArgumentException("Argumentos invalidos dbconfig null");
		setDbconfig(dbconfig);

	}

	public Connection getNewConnection() {
		return null;
		
	}

	public Connection getNewConnection(String environment) throws SQLException {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch (ClassNotFoundException e) {

		}  
		Connection conn = DriverManager.getConnection(environment);
		return conn;

	}
}
