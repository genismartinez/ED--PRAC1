package excepcions;

public class CopyListException extends IndexOutOfBoundsException{

	private static final String mensajeError = "Error al copiar la lista";
	
	public CopyListException() {
		super(mensajeError);
	}
	
	public CopyListException(int posicio) {
		super(mensajeError + " : " + posicio);
	}
}