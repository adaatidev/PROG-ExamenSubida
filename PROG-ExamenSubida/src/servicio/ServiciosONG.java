package servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;

import entidades.Asignacion;
import entidades.EstadoAsignacion;
import entidades.Prioridad;
import entidades.Proyecto;
import entidades.Voluntario;
import excepciones.AsignacionDuplicadaException;
import excepciones.VoluntarioNoEncontradoException;
import persistencia.GestorArchivos;

/**
 * Clase ServiciosONG que se encarga de los servicios que ofrece el sistema:
 * asignar un voluntario a un proyecto, busqueda por id, busqueda de proyectos
 * con disponibilidad, listar todas las asignaciones del estado especificado...
 * 
 * Para hacer esta clase he usado ejercicios que tenía hecho para practicar de
 * casa y en las prácticas (igual que en la de gestión vaya)
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public class ServiciosONG {
	private List<Voluntario> voluntarios;
	private List<Proyecto> proyectos;
	private List<Asignacion> asignaciones;

	public ServiciosONG() {
		this.voluntarios = GestorArchivos.cargarVoluntarios("voluntarios.csv");
		this.proyectos = GestorArchivos.cargarProyectos("proyectos.csv");
		this.asignaciones = new ArrayList<>();
	}

	/**
	 * Método para poder asignar un voluntario a un proyecto con sus comprobaciones
	 * + uso de lambdas
	 * 
	 * @param idVoluntario ID del voluntario
	 * @param idProyecto   ID del proyecto
	 * @throws VoluntarioNoEncontradoException excepción personalizada
	 * @throws AsignacionDuplicadaException    excepción personalizada
	 * @throws IllegalArgumentException
	 */
	public void asignarVoluntarioAProyecto(String idVoluntario, String idProyecto)
			throws VoluntarioNoEncontradoException, AsignacionDuplicadaException, IllegalArgumentException {

		Voluntario v = buscarVoluntarioPorId(idVoluntario);
		Proyecto p = proyectos.stream().filter(pr -> pr.getId().equals(idProyecto)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado."));

		boolean duplicado = asignaciones.stream().anyMatch(
				a -> a.getVoluntario().getId().equals(idVoluntario) && a.getProyecto().getId().equals(idProyecto));
		if (duplicado)
			throw new AsignacionDuplicadaException("El voluntario ya está asignado a este proyecto.");

		boolean tieneHabilidad = v.getHabilidades().stream()
				.anyMatch(hab -> p.getHabilidadesRequeridas().contains(hab));

		if (!tieneHabilidad) {
			throw new IllegalArgumentException("El voluntario no tiene las habilidades requeridas.");
		}

		asignaciones.add(new Asignacion(v, p, LocalDate.now(), EstadoAsignacion.PENDIENTE));
		System.out.println("Asignación creada con éxito.");
	}

	/**
	 * Método que busca a un voluntario por su ID
	 * 
	 * @param id ID del voluntario
	 * @return devuelve si el voluntario con ese ID existe o no
	 * @throws VoluntarioNoEncontradoException excepción personalizada
	 */
	public Voluntario buscarVoluntarioPorId(String id) throws VoluntarioNoEncontradoException {
		return voluntarios.stream().filter(v -> v.getId().equals(id)).findFirst()
				.orElseThrow(() -> new VoluntarioNoEncontradoException("No existe voluntario con el ID: " + id));
	}

	/**
	 * Método para comprobar si un proyecto está disponible o no
	 * 
	 * @return devuelve si un proyecto está disponible o no
	 */
	public List<Proyecto> proyectosConDisponibilidad() {
		return proyectos.stream().filter(p -> {
			long asignados = asignaciones.stream().filter(
					a -> a.getProyecto().getId().equals(p.getId()) && a.getEstado() != EstadoAsignacion.RECHAZADA)
					.count();
			return asignados < p.getVoluntariosNecesarios();
		}).collect(Collectors.toList());
	}

	/**
	 * Método que lista las asignaciones según su estado de asignación
	 * 
	 * @param estado
	 * @return
	 */
	public List<Asignacion> listarAsignacionesPorEstado(EstadoAsignacion estado) {
		return asignaciones.stream().filter(a -> a.getEstado() == estado).collect(Collectors.toList());
	}

	/**
	 * Método que lista los tres primeros voluntarios más experimentados
	 * 
	 * @return devuelve los tres voluntarios más experimentados
	 */
	public List<Voluntario> voluntariosMasExperimentados() {
		return voluntarios.stream().sorted(Comparator.comparingInt(Voluntario::getExperienciaAnos).reversed()).limit(3)
				.collect(Collectors.toList());
	}

	/**
	 * Método para listar los proyectos por prioridad
	 * 
	 * @return devuelve los proyectos por prioridad (alta, media, baja)
	 */
	public Map<Prioridad, List<Proyecto>> proyectosPorPrioridad() {
		return proyectos.stream().collect(Collectors.groupingBy(Proyecto::getPrioridad));
	}

	/**
	 * Método para calcular la media de la experiencia de los voluntarios
	 * 
	 * @return devuelve la media en double de los años de experiencia de los
	 *         voluntarios
	 */
	public double mediaExperienciaVoluntarios() {
		return voluntarios.stream().mapToInt(Voluntario::getExperienciaAnos).average().orElse(0.0);
	}

	/**
	 * Método que devuelve los voluntarios que están disponibles para un proyecto
	 * 
	 * @param p proyecto al que se quiere meter el voluntario
	 * @return devuelve si está disponible para el proyecto o no
	 */
	public List<Voluntario> voluntariosDisponiblesParaProyecto(Proyecto p) {
		return voluntarios.stream()
				.filter(v -> v.getHabilidades().stream().anyMatch(h -> p.getHabilidadesRequeridas().contains(h)))
				.collect(Collectors.toList());
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve los voluntarios de la lista de voluntarios
	 */
	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve los proyectos de la lista de proyectos
	 */
	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	/**
	 * Método que guarda los datos almacenados llamando al gestor de archivos
	 */
	public void guardarDatos() {
		GestorArchivos.guardarAsignaciones(asignaciones, "asignaciones.csv");
	}
}
