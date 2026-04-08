package com.veterinaria;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    private static List<Cliente> clientes = new ArrayList<>();
    public static void adicionarCliente(Cliente c){
        clientes.add(c);
    }

    public static List<Cliente> getCliente() {
        return clientes;
    }
    public static List<Cliente> getClientes() {
        return clientes;
    }
    public static String exibirClientes(){
         String mostrar = "";
        if(clientes.isEmpty()){
            mostrar = "Nenhum cliente cadastrado";
        }
        for(Cliente cliente : clientes){
            mostrar += "\nCliente "+ (clientes.indexOf(cliente) + 1);
            mostrar += Cadastro.exibirDados(cliente);
        }
        return mostrar;
    }

}
