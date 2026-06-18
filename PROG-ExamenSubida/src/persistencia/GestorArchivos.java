package persistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entidades.Asignacion;
import entidades.Prioridad;
import entidades.Proyecto;
import entidades.Voluntario;

/**
 * Clase GestorArchivos que se encarga de toda la gestión de los archivos. Esta
 * clase la he podido sacar de ejercicios que he estado trabajando en las
 * prácticas y en casa
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public class GestorArchivos {

	// Creación de la lista para cargar a los voluntarios
	public static List<Voluntario> cargarVoluntarios(String ruta) {
		ruta = "C:/Users/ada.atienza/eclipse-workspace/PROG-ExamenSubida/src/voluntarios.csv";
		// Lista de los voluntarios
		List<Voluntario> voluntarios = new ArrayList<>();
		// Ruta
		Path path = Paths.get(ruta);
		// Si la ruta no existe salta un error y devuelve la lista de voluntarios
		if (!Files.exists(path)) {
			System.err.println("Archivo de voluntarios no encontrado.");
			return voluntarios;
		}

		// Bloque try-catch
		try {
			// Lee todas las lines
			List<String> lineas = Files.readAllLines(path);

			// Recorre las lineas y por cada palabra (datos) la separa con una coma y al
			// llegar a la habilidad separa con ;, después añade a la lista de voluntarios
			// un nuevo voluntario con los datos adquiridos leyendo las líneas
			for (int i = 1; i < lineas.size(); i++) {
				String[] datos = lineas.get(i).split(",");
				List<String> habilidades = Arrays.asList(datos[5].split(";"));
				voluntarios.add(new Voluntario(datos[0], datos[1], datos[2], datos[3], Integer.parseInt(datos[4]),
						habilidades));
			}
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error leyendo voluntarios: " + e.getMessage());
		}
		return voluntarios;
	}

	// Creación de la lista de proyectos para cargar el fichero (explicación más
	// detallada en el anterior)
	public static List<Proyecto> cargarProyectos(String ruta) {
		ruta = "C:/Users/ada.atienza/eclipse-workspace/PROG-ExamenSubida/src/proyectos.csv";
		List<Proyecto> proyectos = new ArrayList<>();
		Path path = Paths.get(ruta);
		if (!Files.exists(path)) {
			System.err.println("Archivo de proyectos no encontrado.");
			return proyectos;
		}

		try {
			// Almacena los datos en el fichero
			List<String> lineas = Files.readAllLines(path);
			for (int i = 1; i < lineas.size(); i++) {
				String[] datos = lineas.get(i).split(",");
				List<String> habs = Arrays.asList(datos[5].split(";"));
				Prioridad prio = Prioridad.valueOf(datos[7].toUpperCase());
				proyectos.add(new Proyecto(datos[0], datos[1], datos[2], LocalDate.parse(datos[3]),
						LocalDate.parse(datos[4]), habs, Integer.parseInt(datos[6]), prio));
			}
		} catch (Exception e) {
			System.err.println("Error leyendo proyectos: " + e.getMessage());
		}
		return proyectos;
	}

	// Almacena los datos de las asignaciones en el fichero
	public static void guardarAsignaciones(List<Asignacion> asignaciones, String ruta) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
			bw.write("idVoluntario,idProyecto,fechaAsignacion,estado\n");
			for (Asignacion a : asignaciones) {
				bw.write(a.toCSV() + "\n");
			}
			System.out.println("Asignaciones guardadas correctamente en " + ruta);
		} catch (IOException e) {
			System.err.println("Error al guardar asignaciones: " + e.getMessage());
		}
	}
}
