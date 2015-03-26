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
		/*
		Procedimiento utilizando cursor
		create or replace PROCEDURE procursor(cursorParam OUT SYS_REFCURSOR)
			IS
			BEGIN
 
  			OPEN cursorParam FOR
  			SELECT * FROM APARCAMIENTO_ACCESOS;
 
			END;
			/
	*/
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

	}
	

}
