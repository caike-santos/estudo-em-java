package ex8;

public class SaldoInsuficienteException extends Exception{

    public SaldoInsuficienteException(double saldo, double valor){
        super("Saldo insuficiente! " + "Saldo disponivel: R$ " + saldo + " Valor solicitado: R$ " + valor);
    }
}
