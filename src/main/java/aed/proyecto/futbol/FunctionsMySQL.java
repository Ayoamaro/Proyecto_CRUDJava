package aed.proyecto.futbol;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
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
	private static Scanner sc = new Scanner(System.in);
	
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
			String newInternacional = sc.nextLine();
			System.out.println("-------------------------");
			System.out.println("");
			
			if (newInternacional.toLowerCase().equals("si")) {
				newInternacional = "1";
			} else {
				newInternacional = "0";
			}

			consulta.setString(1, nomEquipo);
			consulta.setString(2, codLiga);
			consulta.setString(3, localidad);
			consulta.setString(4, newInternacional);
			
			consulta.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	

	public static void modificarEquipo(String codEquipo) {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			String sql = "UPDATE equipos SET nomEquipo=?, codLiga=?, localidad=?, internacional=? WHERE codEquipo = " + codEquipo;
			PreparedStatement consulta = con.prepareStatement(sql);

			String sql2 = "SELECT * FROM equipos WHERE codEquipo = " + codEquipo;
			PreparedStatement modificar = con.prepareStatement(sql2);
			ResultSet resultado = modificar.executeQuery();

			if (resultado.next()) {
				System.out.println("");
				System.out.println("-------------------------");
				System.out.println("MODIFICACIÓN DE UN EQUIPO");
				System.out.print("Nombre de equipo (Actual: " + resultado.getString("nomEquipo") + "): ");
				String newNomEquipo = sc.nextLine();
				System.out.print("Código de liga (Actual: " + resultado.getString("codLiga") + "): ");
				String newCodLiga = sc.nextLine();
				System.out.print("Localidad (Actual: " + resultado.getString("localidad") + "): ");
				String newLocalidad = sc.nextLine();
				System.out.print("Internacional (SÍ o NO) (Actual: " + resultado.getString("internacional") + "): ");
				String newInternacional = sc.nextLine();
				System.out.println("-------------------------");
				System.out.println("");
				
				if (newInternacional.toLowerCase().equals("si")) {
					newInternacional = "1";
				} else {
					newInternacional = "0";
				}

				consulta.setString(1, newNomEquipo);
				consulta.setString(2, newCodLiga);
				consulta.setString(3, newLocalidad);
				consulta.setString(4, newInternacional);
			}
			consulta.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void borrarEquipo(String codEquipo) {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);

			String sql = "SELECT * FROM equipos WHERE codEquipo = " + codEquipo;
			PreparedStatement consulta = con.prepareStatement(sql);
			ResultSet resultEquipo = consulta.executeQuery();

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
	
	public static void listarDNI() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			CallableStatement consult = con.prepareCall("CALL ejerc_1 (?)");
			
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("CONTRATOS SEGÚN DNI (PROCEDIMIENTO)");
			System.out.print("Introduzca el DNI: ");
			String newDni = sc.nextLine();	
			consult.setString(1, newDni);
			ResultSet result = consult.executeQuery();
			System.out.println("");
			while (result.next()) {
	            String codContrato = result.getString("codContrato");
	            String nomEquipo = result.getString("nomEquipo");
	            String nomLiga = result.getString("nomLiga");
	            String fechaInicio = result.getString("fechaInicio");
	            String fechaFin = result.getString("fechaFin");
	            String precioAnual = result.getString("precioAnual");
	            String precioResicion = result.getString("precioResicion");    

	            System.out.println("{" + codContrato + "}:" + " " + nomEquipo + " - " + nomLiga + " - " + fechaInicio + " - " + fechaFin + " - " + precioAnual + " - " + precioResicion);
	        }
			System.out.println("-------------------------");
			System.out.println("");
			
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void insertarProcedimiento() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			CallableStatement consult = con.prepareCall("call ejerc_2 (?, ?, ?, ?, ?, ?)");

			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("INSERCIÓN DE UN EQUIPO (PROCEDIMIENTO)");
			System.out.print("Nombre de equipo: ");
			String nomEquipo = sc.nextLine();
			System.out.print("Código de Liga: ");
			String codLiga = sc.nextLine();
			System.out.print("Localidad: ");
			String localidad = sc.nextLine();
			System.out.print("Internacional (SÍ o NO): ");
			String newInternacional = sc.nextLine();
			System.out.println("-------------------------");

			if (newInternacional.toLowerCase().equals("si")) {
				newInternacional = "1";
			} else {
				newInternacional = "0";
			}
			
			consult.setString(1, nomEquipo);
			consult.setString(2, codLiga);
			consult.setString(3, localidad);
			consult.setString(4, newInternacional);
			consult.registerOutParameter(5, Types.TINYINT);
			consult.registerOutParameter(6, Types.TINYINT);

			consult.execute();
			System.out.println("Existe liga: " + consult.getString(5));
			System.out.println("Inserción: " + consult.getString(6));
			System.out.println("-------------------------");
			System.out.println("");

			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void futbolistasActivos() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			CallableStatement consult = con.prepareCall("CALL ejerc_3 (?,?,?,?,?)");
			
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("FUTBOLISTAS ACTIVOS EN UN EQUIPO (PROCEDIMIENTO)");
			System.out.print("Código de equipo: ");
			String codEquipo = sc.nextLine();
			System.out.print("Precio anual: ");
			String precioAnual = sc.nextLine();
			System.out.print("Precio rescisión: ");
			String precioResicion = sc.nextLine();
			
			consult.setString(1, codEquipo);
			consult.setString(2, precioAnual);
			consult.setString(3, precioResicion);
			
			consult.execute();
			System.out.println("");
			System.out.println("Contratos activos: " + consult.getString(4));
			System.out.println("Precios menores: " + consult.getString(5));
			System.out.println("-------------------------");
			System.out.println("");
			
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void mesesJugados() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);

			String sql = "SELECT ejerc_4 (?) as meses";
			PreparedStatement consult = con.prepareStatement(sql);
			
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("MESES JUGADOS POR UN JUGADOR (PROCEDIMIENTO)");
			System.out.print("Introduzca el DNI: ");
			String newDni = sc.nextLine();	
			consult.setString(1, newDni);
			ResultSet result = consult.executeQuery();
			System.out.println("");
			
			if (result.next()) {
				System.out.println("El jugador ha jugado: " + result.getString(1) + " meses");
			}
			System.out.println("-------------------------");
			System.out.println("");
			
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}