package entidades;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase Proyecto que hereda de EntidadBase. Representa un proyecto de la ONG
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public class Proyecto extends EntidadBase {
	private String descripcion;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private List<String> habilidadesRequeridas;
	private int voluntariosNecesarios;
	private Prioridad prioridad;

	// Constructor con parámetros
	public Proyecto(String id, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin,
			List<String> habilidadesRequeridas, int voluntariosNecesarios, Prioridad prioridad) {
		super(id, nombre);
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.habilidadesRequeridas = habilidadesRequeridas;
		this.voluntariosNecesarios = voluntariosNecesarios;
		this.prioridad = prioridad;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve la lista de las habilidades requeridas
	 */
	public List<String> getHabilidadesRequeridas() {
		return habilidadesRequeridas;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve el número de voluntarios necesarios
	 */
	public int getVoluntariosNecesarios() {
		return voluntariosNecesarios;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve el nivel de prioridad
	 */
	public Prioridad getPrioridad() {
		return prioridad;
	}

	/**
	 * Método toString que devuelve una cadena de texto con la información del
	 * proyecto
	 */
	@Override
	public String toString() {
		return "Proyecto | ID: " + id + " | Nombre: " + nombre + " | Descripción: " + descripcion + " | Prioridad: "
				+ prioridad;
	}

}
