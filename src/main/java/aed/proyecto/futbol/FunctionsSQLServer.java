package aed.proyecto.futbol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Ayoze Amaro
 *
 */
public class FunctionsSQLServer {

	private static String url = "jdbc:sqlserver://DESKTOP-7OFJ0P6;DataBaseName=dbFutbol";
	private static String usr = "sa_cliente";
	private static String pswd = "2020informatica";
	private static String tipoInternacional;
	
	public static void visualizarEquipos() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			String sql = "SELECT * FROM equipos INNER JOIN ligas ON ligas.codLiga = equipos.codLiga ORDER BY codEquipo ASC";
			PreparedStatement consulta = con.prepareStatement(sql);
			ResultSet resultado = consulta.executeQuery();
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("LISTADO DE LOS EQUIPOS");

			while (resultado.next()) {
				String codEquipo = resultado.getString("codEquipo");
				String nomEquipo = resultado.getString("nomEquipo");
				String nombreLiga = resultado.getString("nomLiga");
				String localidad = resultado.getString("localidad");
				int internacional = resultado.getInt("internacional");
				if (internacional == 1) { tipoInternacional = "SÍ"; }
				else { tipoInternacional = "NO"; }
				
				System.out.println("{" + codEquipo + "}:" + " " + nomEquipo + " | " + nombreLiga + " | " + localidad + " | " + tipoInternacional);
			}
			System.out.println("-------------------------");
			System.out.println("");
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
