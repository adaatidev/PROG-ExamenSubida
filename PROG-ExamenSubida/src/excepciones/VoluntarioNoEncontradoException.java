package excepciones;

/**
 * Excepción que se lanza cuando un voluntario no ha sido encontrado.
 * 
 * @author Ada Atienza Devós
 * @version 1.0
 */
public class VoluntarioNoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que define el mensaje descriptivo del error.
	 * 
	 * @param mensaje Detalle del motivo por el que no se ha encontrado.
	 */
	public VoluntarioNoEncontradoException(String mensaje) {
		super(mensaje);
	}

}
