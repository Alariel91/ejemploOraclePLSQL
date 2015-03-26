package es.open4job.model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.internal.OracleTypes;

public class Principal {
	static CallableStatement cs;
	static SentenciasSQL conexion =new SentenciasSQL();
	static ResultSet rs ;
	public static void main(String[] args) {
		
		conexion.conectarBBDD();
		String procursor= "{call procursor(?)}";
		
		try {
		
			cs= conexion.getConn().prepareCall(procursor);
			
			cs.registerOutParameter(1,OracleTypes.CURSOR);
			
			cs.executeUpdate();
			
			rs = (ResultSet) cs.getObject(1);
			
			while (rs.next()) {
				System.out.println("Id: " + rs.getInt("ID"));
				System.out.println("Latitud: " + rs.getFloat("LATITUD"));
				System.out.println("Longitud: " + rs.getFloat("LONGITUD"));
				System.out.println("Titulo: " + rs.getString("TITULO"));
				System.out.println("Icono: " + rs.getString("ICONO"));
				System.out.println("Tipo: " + rs.getString("TIPO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
