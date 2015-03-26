package es.open4job.model.dao;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SentenciasSQL {

	private Connection conn;

	public SentenciasSQL() {

	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void conectarBBDD() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = java.sql.DriverManager.getConnection(
					"jdbc:oracle:thin:@54.154.192.80:1521:xe", "open4job","open4job");
			System.out.println("Conexion realizada");
			conn.setAutoCommit(false);//No haga commit automaticamente.
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Conexion rechazada: " + e.getMessage());			
		}
		
	}

	public void closeBBDD() {
		try {
			conn.close();
			System.out.println("Conexión cerrada");
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Fallo al cerrar la conexión: " + e.getMessage());		
		}
	}
}
