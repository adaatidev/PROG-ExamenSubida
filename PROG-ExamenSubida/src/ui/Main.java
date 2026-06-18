package ui;

import java.util.Scanner;

import entidades.*;
import excepciones.*;
import servicio.ServiciosONG;

/**
 * Clase Main donde se ejecuta todo el código
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {

		// Creación de los servicios para su uso
		ServiciosONG servicio = new ServiciosONG();
		Scanner sc = new Scanner(System.in);
		int opcion;

		do {
			// He copiado y pegado los corazoncitos de mi proyecto final
			System.out.println("-------------♡ MENÚ ♡-------------");
			System.out.println("♡ 1. Listar voluntarios");
			System.out.println("♡ 2. Listar proyectos");
			System.out.println("♡ 3. Asignar voluntario a proyecto");
			System.out.println("♡ 4. Listar asignaciones");
			System.out.println("♡ 5. Ver estadísticas");
			System.out.println("♡ 6. Buscar voluntario por ID");
			System.out.println("♡ 7. Proyectos con disponibilidad");
			System.out.println("♡ 8. Salir");
			System.out.print("> ");
			opcion = Integer.parseInt(sc.nextLine());

			switch (opcion) {
			case 1:
				// Lista los voluntarios
				servicio.getVoluntarios().forEach(System.out::println);
				break;
			case 2:
				// Lista los proyectos
				servicio.getProyectos().forEach(System.out::println);
				break;
			case 3:
				// Asigna un voluntario a un proyecto realizando las comprobaciones
				System.out.print("♡ ID del voluntario: ");
				String idV = sc.nextLine();
				System.out.print("♡ ID del proyecto: ");
				String idP = sc.nextLine();
				try {
					servicio.asignarVoluntarioAProyecto(idV, idP);
				} catch (VoluntarioNoEncontradoException | AsignacionDuplicadaException | IllegalArgumentException e) {
					System.err.println("♡ Error en asignación: " + e.getMessage());
				}
				break;
			case 4:
				// Lista las asignaciones por estados
				System.out.print("♡ Estado (ACEPTADA/PENDIENTE/RECHAZADA): ");
				EstadoAsignacion est = EstadoAsignacion.valueOf(sc.nextLine().toUpperCase());
				servicio.listarAsignacionesPorEstado(est).forEach(System.out::println);
				break;
			case 5:
				// Muestra las estadísticas que pide, los 3 voluntarios más experimentados, la
				// media de experiencia y los proyectos por disponibilidad
				System.out.println("---♡ Top 3 voluntarios más experimentados ♡---");
				servicio.voluntariosMasExperimentados().forEach(System.out::println);
				System.out.println("---♡ Media de experiencia ♡---");
				System.out.println(servicio.mediaExperienciaVoluntarios() + " años.");
				System.out.println("---♡ Proyectos por prioridad ♡---");
				servicio.proyectosPorPrioridad().forEach((prio, lista) -> {
					System.out.println(prio + ": " + lista.size() + " proyectos.");
				});
				break;
			case 6:
				// Busca a un voluntario mediante su ID
				System.out.print("♡ ID del Voluntario a buscar: ");
				try {
					Voluntario v = servicio.buscarVoluntarioPorId(sc.nextLine());
					System.out.println(v);
				} catch (VoluntarioNoEncontradoException e) {
					System.err.println(e.getMessage());
				}
				break;
			case 7:
				// Imprime los proyectos disponibles
				servicio.proyectosConDisponibilidad().forEach(System.out::println);
				break;
			case 8:
				// Sale del programa guardando todos los datos en los ficheros
				System.out.println("♡ Guardando datos y saliendo...");
				servicio.guardarDatos();
				break;
			default:
				System.out.println("♡ Opción no válida.");
			}
		} while (opcion != 8);

		System.out.println("♡ Has salido del programa! :^)");

		sc.close();
	}
}
