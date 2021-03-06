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
public class FunctionsSQLServer {

	private static String url = "jdbc:sqlserver://DESKTOP-7OFJ0P6;DataBaseName=dbFutbol";
	private static String usr = "sa_cliente";
	private static String pswd = "2020informatica";
	private static String tipoInternacional;
	private static Scanner sc = new Scanner(System.in);
	
	/*
	 * --- MOSTRAR ---
	 * Mostrado de todos los equipos de la base de datos (SQL Server)
	 */
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
	
	/*
	 * --- INSERTAR ---
	 * Insercción de un nuevo equipo en la base de datos (SQL Server)
	 */
	public static void insertarEquipo() {
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
			String newInternacional = sc.nextLine();
			System.out.println("-------------------------");
			System.out.println("");
			
			if (newInternacional.toLowerCase().equals("si")) {
				newInternacional = "1";
			} else {
				newInternacional = "0";
			}

			consult.setString(1, nomEquipo);
			consult.setString(2, codLiga);
			consult.setString(3, localidad);
			consult.setString(4, newInternacional);
			consult.executeUpdate();
			con.close();
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/*
	 * --- MODIFICACIÓN ---
	 * Modificación de un equipo existente en la base de datos (SQL Server)
	 */
	public static void modificarEquipo(String codEquipo) {
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
				System.out.println("EQUIPO ACTUAL");
				System.out.println("Nombre de equipo: " + result.getString("nomEquipo"));
				System.out.println("Código de liga: " + result.getString("codLiga"));
				System.out.println("Localidad: " + result.getString("localidad"));
				System.out.println("Internacional: " + result.getString("internacional"));
			}
			
			System.out.println("-------------------------");
			System.out.println("MODIFICACIÓN DE UN EQUIPO");
			System.out.print("Nombre de equipo: ");
			String newNomEquipo = sc.nextLine();
			System.out.print("Código de liga: ");
			String newCodLiga = sc.nextLine();
			System.out.print("Localidad: ");
			String newLocalidad = sc.nextLine();
			System.out.print("Internacional (SÍ o NO): ");
			String newInternacional = sc.nextLine();
			System.out.println("-------------------------");
			System.out.println("");
			
			if (newInternacional.toLowerCase().equals("si")) {
				newInternacional = "1";
			} else {
				newInternacional = "0";
			}

			consult.setString(1, newNomEquipo);
			consult.setString(2, newCodLiga);
			consult.setString(3, newLocalidad);
			consult.setString(4, newInternacional);
			
			consult.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/*
	 * --- BORRAR ---
	 * Borrado de un equipo existente en la base de datos (SQL Server)
	 */
	public static void borrarEquipo(String codEquipo) {
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
	
	/*
	 * --- 1. PROCEDIMIENTOS ---
	 * Crear un procedimiento almacenado que liste todos los contratos de cierto futbolista pasando por parámetro 
	 * de entrada el dni o nie del futbolista, ordenados por fecha de inicio. Los datos a visualizar serán: 
	 * Código de contrato, nombre de equipo, nombre de liga, fecha de inicio, fecha de fin, precio anual y precio de recisión del contrato. 
	 */
	public static void listarDNI() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			CallableStatement consult = con.prepareCall("EXEC dbo.ejerc_1 @codDnionie = ?");
			
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
	
	/*
	 * --- 2. PROCEDIMIENTOS ---
	 * Crear un procedimiento almacenado que inserte un equipo, de modo que se le pase como parámetros todos los datos. 
	 * Comprobar que el código de liga pasado exista en la tabla ligas. En caso de que no exista la liga que no se inserte.
	 * 		- Devolver en un parámetro de salida: 0 si la liga no existe y 1 si la liga existe.
	 * 		- Devolver en otro parámetro de salida: 0 si el equipo no se insertó y 1 si la inserción fue correcta. 
	 */
	public static void insertarProcedimiento() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			CallableStatement consult = con.prepareCall("exec dbo.ejerc_2 @nomEquipo = ?, @codLiga = ?, @localidad = ?, @internacional = ?, @ligaExiste = ?, @insercion = ?");

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
	
	/*
	 * --- 3. PROCEDIMIENTOS ---
	 * Procedimiento almacenado que indicándole un equipo, precio anual y un precio recisión, devuelva dos parámetros. 
	 * En un parámetro de salida la cantidad de futbolistas en activo (con contrato vigente) que hay en dicho equipo. 
	 * En otro parámetro de salida la cantidad de futbolistas en activo de dicho equipo con precio anual y de recisión menor de los indicados.
	 */
	public static void futbolistasActivos() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);
			CallableStatement consult = con.prepareCall("exec dbo.ejerc_3 @codEquipo = ?, @precioAnual = ?, @precioResicion = ?, @futbolistasPreciso = ?, @futbolistasActivo = ?");
			
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
			consult.setInt(2, Integer.parseInt(precioAnual));
			consult.setInt(3, Integer.parseInt(precioResicion));
			consult.registerOutParameter(4, Types.INTEGER);
			consult.registerOutParameter(5, Types.INTEGER);
			
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
	
	/*
	 * --- 4. FUNCIÓN ---
	 * Función que dándole un DNI o NIE de un futbolista nos devuelva en número de meses total que ha estado en equipos.
	 */
	public static void mesesJugados() {
		try {
			Connection con = DriverManager.getConnection(url, usr, pswd);

			String sql = "SELECT dbo.ejerc_4 (?) as meses";
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
