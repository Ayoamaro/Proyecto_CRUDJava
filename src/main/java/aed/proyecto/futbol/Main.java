package aed.proyecto.futbol;

import java.util.Scanner;

/**
 * @author Ayoze Amaro
 *
 */
public class Main {

	private static boolean salir = false;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		while (salir == false) {
			menuServidores();
		}
	}
	
	private static void menuServidores() {
		try {
			System.out.println("Bienvenido al Proyecto Java de 'dbFutbol'");
			System.out.println("ELIGE LA BASE DE DATOS");
			System.out.println("1. MySQL");
			System.out.println("2. Access");
			System.out.println("3. SQL Server");
			System.out.println("4. Salir");
			System.out.print("Opción: ");
			String select = sc.nextLine();

			switch (select) {
				case "1":
					menuOpciones("MySQL");
					break;
				case "2":
					break;
				case "3":
					menuOpciones("SQLServer");
					break;
				case "4":
					salir = true;
					System.out.println("");
					System.out.println("¡ADIÓS!");
					break;
				default:
					break;
			}
			
		} catch(Exception ex) {
			System.out.println("ERROR: " + ex);
		}
	}
	
	private static void menuOpciones(String servidor) {
		try {
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("OPCIONES DISPONIBLES");
			System.out.println("1. Listar equipos");
			System.out.println("2. Insertar un equipo");
			System.out.println("3. Modificar un equipo");
			System.out.println("4. Borrar un equipo");
			System.out.println("5. Utilizar procedimientos");
			System.out.println("6. Salir");
			System.out.print("Opción: ");
			String select = sc.nextLine();

			if (servidor == "MySQL") {
				switch (select) {
					case "1":
						FunctionsMySQL.visualizarEquipos();
						break;
					case "2":
						FunctionsMySQL.insertarEquipo();
						break;
					case "3":
						FunctionsMySQL.visualizarEquipos();
						System.out.print("¿Qué equipo quieres borrar? (0 para cancelar): ");
						String codModificar = sc.nextLine();
						FunctionsMySQL.modificarEquipo(codModificar);
						break;
					case "4":
						FunctionsMySQL.visualizarEquipos();
						System.out.print("¿Qué equipo quieres borrar? (0 para cancelar): ");
						String codBorrado = sc.nextLine();
						FunctionsMySQL.borrarEquipo(codBorrado);
						break;
					case "5":
						menuProcedimientos("MySQL");
						break;
					case "6":
						salir = true;
						System.out.println("");
						System.out.println("¡ADIÓS!");
						break;
					default:
						salir = true;
						System.out.println("");
						System.out.println("¡ADIÓS!");
						break;
				}
			}
			else if (servidor == "SQLServer") {
				switch (select) {
					case "1":
						FunctionsSQLServer.visualizarEquipos();
						break;
					case "2":
						FunctionsSQLServer.insertarEquipo();;
						break;
					case "3":
						FunctionsSQLServer.visualizarEquipos();
						System.out.print("¿Qué equipo quieres borrar? (0 para cancelar): ");
						String codModificar = sc.nextLine();
						FunctionsSQLServer.modificarEquipo(codModificar);
						break;
					case "4":
						FunctionsSQLServer.visualizarEquipos();
						System.out.print("¿Qué equipo quieres borrar? (0 para cancelar): ");
						String codBorrado = sc.nextLine();
						FunctionsSQLServer.borrarEquipo(codBorrado);
						break;
					case "5":
						menuProcedimientos("SQLServer");
						break;
					case "6":
						salir = true;
						System.out.println("");
						System.out.println("¡ADIÓS!");
						break;
					default:
						salir = true;
						System.out.println("");
						System.out.println("¡ADIÓS!");
						break;
				}
			}
	
			
		} catch(Exception ex) {
			System.out.println("ERROR: " + ex);
		}
	}
	
	private static void menuProcedimientos(String servidor) {
		try {
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("PROCEDIMIENTOS");
			System.out.println("1. Insertar un equipo");
			System.out.println("2. Listar contratos según DNI");
			System.out.println("3. Futbolistas activos en un equipo");
			System.out.println("4. Cantidad de meses jugados por un jugador");
			System.out.println("5. Salir");
			System.out.print("Opción: ");
			String select = sc.nextLine();

			if (servidor == "MySQL") {
				switch (select) {
					case "1":
						break;
					case "2":
						break;
					case "3":
						break;
					case "4":
						break;
					case "5":
						break;
					case "6":
						salir = true;
						System.out.println("");
						System.out.println("¡ADIÓS!");
						break;
					default:
						salir = true;
						System.out.println("");
						System.out.println("¡ADIÓS!");
						break;
				}
			}
			else if (servidor == "SQLServer") {
				switch (select) {
					case "1":
						break;
					case "2":
						break;
					case "3":
						break;
					case "4":
						break;
					case "5":
						break;
					case "6":
						salir = true;
						System.out.println("");
						System.out.println("¡ADIÓS!");
						break;
					default:
						salir = true;
						System.out.println("");
						System.out.println("¡ADIÓS!");
						break;
				}
			}
			
		} catch(Exception ex) {
			System.out.println("ERROR: " + ex);
		}
	}
}
