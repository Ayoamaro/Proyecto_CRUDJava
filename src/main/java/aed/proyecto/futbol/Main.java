package aed.proyecto.futbol;

import java.util.Scanner;

/**
 * @author Ayoze Amaro
 *
 */
public class Main {

	private static boolean exit = false;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		while (exit == false) {
			menuServer();
		}
	}
	
	private static void menuServer() {
		try {
			System.out.println("Bienvenido al Proyecto Java de 'dbFutbol'");
			System.out.println("Elige una Base de datos");
			System.out.println("1. MySQL");
			System.out.println("2. Access");
			System.out.println("3. SQL Server");
			System.out.println("4. Salir");
			System.out.print("Opción: ");
			String select = sc.nextLine();

			switch (select) {
				case "1": {
					menuOpciones();
					break;
				}
				case "2": {
					break;
				}
				case "3": {
					break;
				}
				case "4": {
					exit = true;
					System.out.println("");
					System.out.println("¡ADIÓS!");
					break;
				}
				default:
					break;
			}
			
		} catch(Exception ex) {
			System.out.println("Volvio a ocurrir");
		}
	}
	
	private static void menuOpciones() {
		try {
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("¿Qué deseas hacer?");
			System.out.println("1. Listar equipos");
			System.out.println("2. Insertar un equipo");
			System.out.println("3. Borrar un equipo");
			System.out.println("4. Modificar un equipo");
			System.out.println("5. Utilizar procedimientos");
			System.out.println("6. Salir");
			System.out.print("Opción: ");
			String select = sc.nextLine();

			switch (select) {
				case "1":
					AccesoBD.visualizarEquipos();
					break;
				case "2":
					AccesoBD.insertarEquipo();
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					break;
				case "6": {
					exit = true;
					System.out.println("");
					System.out.println("¡ADIÓS!");
					break;
				}
				default:
					exit = true;
					System.out.println("");
					System.out.println("¡ADIÓS!");
					break;
			}
			
		} catch(Exception ex) {
			System.out.println("Volvio a ocurrir");
		}
	}
}
