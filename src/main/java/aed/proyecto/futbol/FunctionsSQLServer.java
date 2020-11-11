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
public class FunctionsSQLServer {

	private static String url = "jdbc:sqlserver://DESKTOP-7OFJ0P6;DataBaseName=dbFutbol";
	private static String usr = "sa_cliente";
	private static String pswd = "2020informatica";
	private static String tipoInternacional;
	
	public static void visualizarEquipos() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			String sql = "SELECT * FROM equipos INNER JOIN ligas ON ligas.codLiga = equipos.codLiga ORDER BY codEquipo ASC";
			PreparedStatement consult = con.prepareStatement(sql);
			ResultSet result = consult.executeQuery();
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("LISTADO DE LOS EQUIPOS");

			while (result.next()) {
				String codEquipo = result.getString("codEquipo");
				String nomEquipo = result.getString("nomEquipo");
				String nombreLiga = result.getString("nomLiga");
				String localidad = result.getString("localidad");
				int internacional = result.getInt("internacional");
				if (internacional == 1) { tipoInternacional = "SÍ"; }
				else { tipoInternacional = "NO"; }
				
				System.out.println("{" + codEquipo + "}:" + " " + nomEquipo + " - " + nombreLiga + " - " + localidad + " - " + tipoInternacional);
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
			
			if (internacional.toLowerCase().equals("si")) {
				internacional = "1";
			}

			consult.setString(1, nomEquipo);
			consult.setString(2, codLiga);
			consult.setString(3, localidad);
			consult.setString(4, internacional);
			consult.executeUpdate();
			con.close();
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	

	public static void modificarEquipo(String codEquipo) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
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

	public static void borrarEquipo(String codEquipo) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);

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
	
	public static void insertarProcedimiento() {
		
	}
	
	public static void listarDNI() {
		
	}
	
	public static void futbolistasActivos() {
		
	}
	
	public static void mesesJugados() {
		
	}

}
