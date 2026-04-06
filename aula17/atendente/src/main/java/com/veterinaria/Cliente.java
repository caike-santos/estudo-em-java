package com.veterinaria;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Pet> pet = new ArrayList<>();
    public Cliente(String nome, int idade, String cpf, Endereço endereço, Telefone telefone, String email, List<Pet> pet){
        super(nome, idade, cpf, endereço, telefone, email);
        this.pet = pet;
    }
    public List<Pet> getPet() {
        return pet;
    }
    public void setPet(List<Pet> pet) {
        this.pet = pet;
    }

    
}
