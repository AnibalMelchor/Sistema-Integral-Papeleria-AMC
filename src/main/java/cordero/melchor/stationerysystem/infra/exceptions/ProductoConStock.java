package cordero.melchor.stationerysystem.infra.exceptions;

public class ProductoConStock extends RuntimeException {
    public ProductoConStock(String message) {
        super(message);
    }
}
