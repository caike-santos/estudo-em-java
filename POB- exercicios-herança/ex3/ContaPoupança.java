package ex3;

public class ContaPoupança extends Conta{
    public ContaPoupança(String d, double s){
       super(d, s);
    }

    @Override
    public double saque(int x){
        x *= 1.1;
        if(x > getSaldo()){
            System.err.println("Erro, Saldo insuficiente");
            return 0;
        }
        setSaldo(getSaldo() - x);
        return getSaldo();
    }

    @Override
    public void exibirDados(){
        super.exibirDados();
        System.out.println("Tipo da conta: Conta Poupança");
    }
}
