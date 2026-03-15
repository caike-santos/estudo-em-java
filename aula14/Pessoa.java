package aula14;

public abstract class Pessoa {
    private String nome;
    private int idade;
    private String cpf;
    private Endereço endereço;
    private Telefone telefone;
    private String email;

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEndereço(Endereço endereço) {
        this.endereço = endereço;
    }
    public Endereço getEndereço() {
        return endereço;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public int getIdade() {
        return idade;
    }
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    public Telefone getTelefone() {
        return telefone;
    }

    public void exibeDados(){
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("cpf: " + getCpf());
        System.out.println("Rua: " + getEndereço().getRua());
        System.out.println("Bairro: " + getEndereço().getBairro());
        System.out.println("Numero: " + getEndereço().getNumero());
        System.out.println("Cep: " + getEndereço().getCep());
        System.out.println("Complemento: " + getEndereço().getComplemento());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Email: " + getEmail());
    }
}
