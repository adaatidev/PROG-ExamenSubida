package excepciones;

/**
 * Excepción que se lanza cuando hay una asignación duplicada.
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public class AsignacionDuplicadaException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que define el mensaje descriptivo del error.
	 * 
	 * @param mensaje Detalle del motivo por el que ha sido duplicado.
	 */
	public AsignacionDuplicadaException(String mensaje) {
		super(mensaje);
	}

}
