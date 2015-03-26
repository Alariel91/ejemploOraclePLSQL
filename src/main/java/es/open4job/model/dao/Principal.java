package es.open4job.model.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class Principal {
	static CallableStatement cs;
	static SentenciasSQL conexion =new SentenciasSQL();
	public static void main(String[] args) {
		
		
		conexion.conectarBBDD();
		String getTituloById= "{call getTitulo(?,?)}";
		try {
			cs= conexion.getConn().prepareCall(getTituloById);
			cs.setInt(1, 1);
			cs.registerOutParameter(2,java.sql.Types.VARCHAR);
			cs.execute();
			
			String titulo = cs.getString(2);
			System.out.println(titulo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.closeBBDD();
		/*
		String mensaje= "{call mensajeBienvenida(?)}";
		try {
			cs = conexion.getConn().prepareCall(mensaje);
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.execute();
			
			String mensajerecogido= cs.getString(1);
			System.out.println(mensajerecogido);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	*/
		
		

	}
	

}
