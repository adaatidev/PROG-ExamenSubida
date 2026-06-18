package entidades;

import java.util.List;

/**
 * Clase Voluntario que hereda de la clase EntidadBase. Representa un voluntario
 * de la ONG
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public class Voluntario extends EntidadBase {
	private String email;
	private String telefono;
	private int experienciaAnos;
	private List<String> habilidades;

	// Constructor con parámetros
	public Voluntario(String id, String nombre, String email, String telefono, int experiencia_anos,
			List<String> habilidades) {
		super(id, nombre);
		this.email = email;
		this.telefono = telefono;
		this.experienciaAnos = experiencia_anos;
		this.habilidades = habilidades;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve los años de experiencia del voluntario
	 */
	public int getExperienciaAnos() {
		return experienciaAnos;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve una lista de las habilidades
	 */
	public List<String> getHabilidades() {
		return habilidades;
	}

	/**
	 * Método toString que devuelve una cadena de texto con la información del
	 * voluntario
	 */
	@Override
	public String toString() {
		return "Voluntario | ID: " + id + " | Nombre: " + nombre + " | Años de experiencia: " + experienciaAnos;
	}

}
