package aula02;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nome = leitor.nextLine();

        System.out.println("Seu nome é: " + nome);
        leitor.close();


    }
}
