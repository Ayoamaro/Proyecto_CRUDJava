package aed.proyecto.futbol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @author Ayoze Amaro
 *
 */
public class FunctionsMySQL {
	
	private static String url = "jdbc:mysql://localhost:3306/dbfutbol";
	private static String usr = "root";
	private static String pswd = "";
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
	
	public static void insertarEquipo() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			String sql = "INSERT INTO equipos (nomEquipo, codLiga, localidad, internacional) VALUES (?,?,?,?)";
			PreparedStatement consulta = con.prepareStatement(sql);
			
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("INSERCIÓN DE UN EQUIPO");
			System.out.print("Nombre de equipo: ");
			String nomEquipo = sc.nextLine();
			System.out.print("Código de Liga: ");
			String codLiga = sc.nextLine();
			System.out.print("Localidad: ");
			String localidad = sc.nextLine();
			System.out.print("Internacional (SÍ o NO): ");
			String internacionalString = sc.nextLine();
			System.out.println("-------------------------");
			System.out.println("");
			
			boolean internacional = false;
			if (internacionalString.toLowerCase().equals("si")) {
				internacional = true;
			}

			consulta.setString(1, nomEquipo);
			consulta.setString(2, codLiga);
			consulta.setString(3, localidad);
			consulta.setBoolean(4, internacional);
			
			consulta.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void borrarEquipo() {
	
	}
	
	public static void modificarEquipo() {
		
	}
}
	