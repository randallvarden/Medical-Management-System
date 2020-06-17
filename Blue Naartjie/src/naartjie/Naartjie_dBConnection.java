package naartjie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Naartjie_dBConnection {
	
	private static Connection con;
	private static String jdbcUrl = "jdbc:mysql://localhost:3307/Naartjie?useSSL=false";
	private static String driver = "com.mysql.jdbc.Driver";
	
	protected static Connection createConnection() {
		try {
			Class.forName(driver);
		    con = DriverManager.getConnection(jdbcUrl, "root", "");
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return con;
	}
	
	
	protected void setUrl(String url) {
		Naartjie_dBConnection.jdbcUrl = url; 
	}
	
	protected String getUrl() {
		return Naartjie_dBConnection.jdbcUrl;
	}

	protected void setDriver(String driver) {
		Naartjie_dBConnection.driver = driver; 
	}
	
	protected String getDriver() {
		return Naartjie_dBConnection.driver;
	}
}
