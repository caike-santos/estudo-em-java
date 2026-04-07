package com.veterinaria;

public abstract class Pessoa {
    private String nome = "null";
    private int idade = 0;
    private String cpf = "null";
    private Endereço endereço = null;
    private Telefone telefone = null;
    private String email = "null";

    public Pessoa(String nome, int idade, String cpf, Endereço endereço, Telefone telefone, String email){
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.endereço = endereço;
        this.telefone = telefone;
        this.email = email;
    }

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

    public String exibeCpf(){
        return getCpf().substring(0, 3) + "." + getCpf().substring(3 , 6)+ "." + getCpf().substring(6, 9) + "-" + getCpf().substring(9);
    }

    public String exibeDados(){
        return "\nDados da pessoa:" +
        "\nNome: " + getNome() +
        "\nIdade: " + getIdade() +
        "\ncpf: " + exibeCpf() +
        this.endereço.exibeEndereço() +
        this.telefone.exibeTelefone() +
        "\nEmail: " + getEmail() + "\n";
    }
}
