package entidades;

/**
 * Clase abstracta auxiliar para el resto de las clases
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public abstract class EntidadBase {
	// Variables que comparten la clase Voluntario y Proyecto
	protected String id;
	protected String nombre;

	// Constructor con parámetros
	public EntidadBase(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve el id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Método get necesario
	 * 
	 * @return devuelve el nombre
	 */
	public String getNombre() {
		return nombre;
	}

}
