package cordero.melchor.stationerysystem.infra.exceptions;

public class VentaNoEncontradaException extends RuntimeException {
    public VentaNoEncontradaException(String message) {
        super(message);
    }
}
