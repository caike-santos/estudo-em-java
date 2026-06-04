package Lista2.Model;

public class PlacaInvalidaException extends RuntimeException {
    public PlacaInvalidaException(String mensagem) {
        super(mensagem);
    }
}