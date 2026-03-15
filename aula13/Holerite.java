package aula13;

public class Holerite implements Imprimivel{
    private String nome;
    private double salario;

    public Holerite(String n, double s){
        this.nome = n;
        this.salario = s;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getSalario() {
        return salario;
    }

    @Override
    public void mostrarNaTela(){
        System.out.println("Funcionario: "+ this.getNome());
        System.out.println("Salario: "+ this.getSalario());
        System.out.println("-------------------------------");
    }
}


