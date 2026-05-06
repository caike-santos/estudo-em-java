package dominio;

public abstract class Funcionario{
    private String cpf;
    private double salario;

    public Funcionario(String c, double s){
        cpf = c;
        salario = s;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getSalario() {
        return salario;
    }

    public void exibir(){
        System.out.println("CPF: " + getCpf());
        System.out.println("Salario: " + getSalario());
    }
}