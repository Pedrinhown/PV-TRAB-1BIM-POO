package db;

import java.sql.Connection;

public class Conectar {

	public static void main(String[] args) {
		
		ConexaoMySql connect = new ConexaoMySql();
		
		Connection connection = connect.conectar();
		
		System.out.println(connection);
	}

}
