package com.veterinaria;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    private String nome = "null";
    private int idade = 0;
    private LocalDate dataNascimento;
    private String cpf = "null";
    private Endereço endereço = null;
    private Telefone telefone = null;
    private String email = "null";

    public Pessoa(String nome, LocalDate dataNascimento, String cpf, Endereço endereço, Telefone telefone, String email){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereço = endereço;
        this.telefone = telefone;
        this.email = email;
        this.idade = getIdade();
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.idade = getIdade();
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
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(this.dataNascimento, dataAtual);
        setIdade(periodo.getYears());
        return periodo.getYears();
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
