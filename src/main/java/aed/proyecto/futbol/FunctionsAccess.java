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
public class FunctionsAccess {

	private static String nom = "C:\\Users\\Ayoze\\eclipse-workspace\\Proyecto_CRUDJava\\src\\main\\resources\\report\\dbFutbol.accdb";
	private static String url = "jdbc:ucanaccess://";
	private static String tipoInternacional;
	private static Scanner sc = new Scanner(System.in);
	
	/*
	 * --- MOSTRAR ---
	 * Mostrado de todos los equipos de la base de datos (Access)
	 */
	public static void visualizarEquipos() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url + nom);
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
				
				System.out.println("{" + codEquipo + "}:" + " " + nomEquipo + " - " + nombreLiga + " - " + localidad + " - " + tipoInternacional);
			}
			System.out.println("-------------------------");
			System.out.println("");
			con.close();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	/*
	 * --- INSERTAR ---
	 * Insercción de un nuevo equipo en la base de datos (Access)
	 */
	public static void insertarEquipo() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url + nom);
			String sql = "INSERT INTO equipos (nomEquipo, codLiga, localidad, internacional) VALUES (?,?,?,?)";
			PreparedStatement consult = con.prepareStatement(sql);
			
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
			String internacional = sc.nextLine();
			System.out.println("-------------------------");
			System.out.println("");
			
			boolean internacionalBool = false;
			if (internacional.toLowerCase().equals("si")) {
				internacionalBool = true;
			}

			consult.setString(1, nomEquipo);
			consult.setString(2, codLiga);
			consult.setString(3, localidad);
			consult.setBoolean(4, internacionalBool);
			consult.executeUpdate();
			con.close();
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/*
	 * --- MODIFICACIÓN ---
	 * Modificación de un equipo existente en la base de datos (Access)
	 */
	public static void modificarEquipo(String codEquipo) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url + nom);
			String sql = "UPDATE equipos SET nomEquipo=?, codLiga=?, localidad=?, internacional=? WHERE codEquipo = " + codEquipo;
			PreparedStatement consult = con.prepareStatement(sql);

			String sql2 = "SELECT * FROM equipos WHERE codEquipo = " + codEquipo;
			PreparedStatement modify = con.prepareStatement(sql2);
			ResultSet result = modify.executeQuery();

			if (result.next()) {
				System.out.println("");
				System.out.println("-------------------------");
				System.out.println("MODIFICACIÓN DE UN EQUIPO");
				System.out.print("Nombre de equipo (Actual: " + result.getString("nomEquipo") + "): ");
				String newNomEquipo = sc.nextLine();
				System.out.print("Código de liga (Actual: " + result.getString("codLiga") + "): ");
				String newCodLiga = sc.nextLine();
				System.out.print("Localidad (Actual: " + result.getString("localidad") + "): ");
				String newLocalidad = sc.nextLine();
				System.out.print("Internacional (SÍ o NO) (Actual: " + result.getString("internacional") + "): ");
				String newInternacional = sc.nextLine();
				System.out.println("-------------------------");
				System.out.println("");
				
				if (newInternacional.toLowerCase().equals("si")) {
					newInternacional = "1";
				} else if (newInternacional.toLowerCase().equals("no")) {
					newInternacional = "0";
				}

				consult.setString(1, newNomEquipo);
				consult.setString(2, newCodLiga);
				consult.setString(3, newLocalidad);
				consult.setString(4, newInternacional);
			}
			consult.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/*
	 * --- BORRAR ---
	 * Borrado de un equipo existente en la base de datos (Access)
	 */
	public static void borrarEquipo(String codEquipo) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url + nom);
			String sql = "SELECT * FROM equipos WHERE codEquipo = " + codEquipo;
			PreparedStatement consult = con.prepareStatement(sql);
			ResultSet resultEquipo = consult.executeQuery();

			String sql2 = "SELECT * FROM contratos WHERE codEquipo = " + codEquipo;
			PreparedStatement consulta2 = con.prepareStatement(sql2);
			ResultSet resultContratos = consulta2.executeQuery();

			boolean borrado = false;
			if (resultEquipo.next()) {
				if (resultContratos.isBeforeFirst() == false) {
					System.out.println("");
					System.out.println("-------------------------");
					System.out.println("BORRADO DE UN EQUIPO");
					System.out.print("¿Seguro que quiere borrar este equipo? (SÍ o NO): ");
					String select = sc.nextLine();
					System.out.println("-------------------------");
					System.out.println("");

					if (select.toLowerCase().equals("si")) {
						borrado = true;
					}
				} else {
					System.out.println("");
					System.out.println("-------------------------");
					System.out.println("BORRADO DE UN EQUIPO");
					System.out.print("Si quiere borrar el (" + resultEquipo.getString("nomEquipo") + "), debe borrar estos contratos: \n");
					
					while (resultContratos.next()) {
						String codContrato = resultContratos.getString("codContrato");
						String codDNI = resultContratos.getString("codDnionie");
						String fechaInicio = resultContratos.getString("fechaInicio");
						String fechaFin = resultContratos.getString("fechaFin");
						String precioAnual = resultContratos.getString("precioAnual");
						String precioResicion = resultContratos.getString("precioResicion");

						System.out.println("{" + codContrato + "}:" + " " + codDNI + " | " + fechaInicio + " | " + fechaFin + " | " + precioAnual + " | " + precioResicion);
					}
					System.out.println("");
					System.out.print("¿Seguro que quiere borrar este equipo y estos contratos? (SÍ o NO): ");
					String eleccion = sc.nextLine();
					System.out.println("-------------------------");
					System.out.println("");

					if (eleccion.toLowerCase().equals("si")) {
						borrado = true;
					}
				}
			}

			if (borrado == true) {
				String sqlDelete = "DELETE FROM contratos WHERE codEquipo = " + codEquipo;
				PreparedStatement borradoContratos = con.prepareStatement(sqlDelete);
				borradoContratos.executeUpdate();

				String sqlDelete2 = "DELETE FROM equipos WHERE codEquipo = " + codEquipo;
				PreparedStatement borradoEquipos = con.prepareStatement(sqlDelete2);
				borradoEquipos.executeUpdate();
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
