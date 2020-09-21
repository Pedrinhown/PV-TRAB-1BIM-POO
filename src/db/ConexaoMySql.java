package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql {

	private String url = "jdbc:mysql://localhost:3306/trabalhoprova?useTimezone=true&serverTimezone=UTC";
	private String root = "usuario";
	private String key = "senha@123";
		
	
	public Connection conectar() {
		try {
			return DriverManager.getConnection(url, root, key);
		} catch (SQLException e) {
			throw new Error("SQL Connection Exception");
		}
	}
	
	
}
