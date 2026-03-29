package aula14;

public abstract class Funcionario extends Pessoa{
    private int id;
    private float salario;
    private String funcao;

    public Funcionario(String nome, int idade, String cpf, Endereço endereço, Telefone telefone, String email, int id, float salario, String funcao){
        super(nome, idade, cpf, endereço, telefone, email);
        this.id = id;
        this.salario = salario;
        this.funcao = funcao;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setSalario(float salario) {
        this.salario = salario;
    }
    public float getSalario() {
        return salario;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public String getFuncao() {
        return funcao;
    }

    @Override
    public void exibeDados(){
        super.exibeDados();
        System.out.println("Id: " + getId());
        System.out.println("Salário: " + getSalario());
        System.out.println("Função: " + getFuncao());
    }
}
