package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			
			// statement é um comando SQL
			// instanciar um objeto do tipo statement com o método createStatement()
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from department");
			
			while (rs.next()) {
	// se existir próximo, pois next() pula para o próximo se se for último retorna false	
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
