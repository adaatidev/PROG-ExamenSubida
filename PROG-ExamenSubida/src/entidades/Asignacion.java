package entidades;

import java.time.LocalDate;

/**
 * Clase Asignacion que referencia a Voluntario, Proyecto, fecha_asignacion y
 * estado
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public class Asignacion {
	private Voluntario voluntario;
	private Proyecto proyecto;
	private LocalDate fechaAsignacion;
	private EstadoAsignacion estado;

	// Constructor con parámetros
	public Asignacion(Voluntario voluntario, Proyecto proyecto, LocalDate fechaAsignacion, EstadoAsignacion estado) {
		this.voluntario = voluntario;
		this.proyecto = proyecto;
		this.fechaAsignacion = fechaAsignacion;
		this.estado = estado;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve al voluntario
	 */
	public Voluntario getVoluntario() {
		return voluntario;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve el proyecto
	 */
	public Proyecto getProyecto() {
		return proyecto;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve el estado de asignación
	 */
	public EstadoAsignacion getEstado() {
		return estado;
	}

	/**
	 * Método que vuelve la información en un texto CSV
	 * 
	 * @return texto CSV para el fichero
	 */
	public String toCSV() {
		return voluntario.getId() + "," + proyecto.getId() + "," + fechaAsignacion + "," + estado;
	}

	/**
	 * Método toString que devuelve una cadena de texto con la información de la
	 * asignación
	 */
	@Override
	public String toString() {
		return "Asignacion | Voluntario: " + voluntario + " | Proyecto: " + proyecto + " | Fecha de asignación: "
				+ fechaAsignacion + " | Estado: " + estado;
	}

}
