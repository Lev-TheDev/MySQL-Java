package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	// método para conectar com o banco de dados
	// declarar um Objeto do tipo Connection do import java.sql.Connection
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				// pegamos as propriedades
				String url = props.getProperty("dburl");
				// pegamos a url do banco de dados
				conn = DriverManager.getConnection(url, props);
				// estabelecemos a conexão, que é instanciar um objeto tipo Connection
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//método para fechar a conexão
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// método para carregar as propriedades que estão no arquivo db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			// arquivo está na pasta raiz do projeto, então só colocar o nome funciona
			Properties props = new Properties();
			props.load(fs);
			// load faz a leitura e guarda em props
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}