package ex6;

public abstract class Funcionario {
    private String nome;
    private double salarioBase;

    public Funcionario(String n, double s){
        nome = n;
        salarioBase = s;
    }

    public abstract double calcularSalario();
    public void exibir(){
        System.out.println("Nome: " + getNome() + "\n" + "Salario Base: " + getSalarioBase());
    };

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    public double getSalarioBase() {
        return salarioBase;
    }


}
