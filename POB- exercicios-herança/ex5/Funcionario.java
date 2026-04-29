package ex5;

public class Funcionario {
    private String nome;
    private double salario;

    public Funcionario(String n, double s){
        nome = n;
        salario = s;
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
}
