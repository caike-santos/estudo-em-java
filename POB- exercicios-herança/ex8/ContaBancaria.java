package ex8;

public class ContaBancaria {
    
    private String titular;
    private double saldo;

    public ContaBancaria(String t, double s){
        titular = t;
        saldo = s;
    }

    public void sacar(double valor) throws SaldoInsuficienteException{
        if(valor > saldo){
            throw new SaldoInsuficienteException(saldo, valor);
        }

        saldo -= valor;

        System.out.println("Saque realizado com sucesso!");
        System.out.println("Novo saldo: R$ "+ saldo);
    }
}
