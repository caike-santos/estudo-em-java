package aula15;

import java.util.Scanner;

public class excecaoAritmetica {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        boolean flag = false;
        double valorTotal = 0.0;
        int qtPessoas = 0;
        double valorInd = 0.0;

        while(!flag){
             try{
            System.out.println("Digite o Valor total:");
            valorTotal = leitor.nextDouble();
            System.out.println("Insira a quantidade de pessoas na mesa:");
            qtPessoas = leitor.nextInt();
            int teste = 1/qtPessoas;
            valorInd = valorTotal/qtPessoas;
            System.out.println("Valor para cada pessoa: " + valorInd);
            flag = true;
        }catch(ArithmeticException erro){
            System.err.println("Erro quantidade de pessoas nao pode ser 0 tente novamente");
            leitor.nextLine();
        }catch (Exception erro) {
                System.err.println("Erro de digitação: Insira apenas números válidos.");
                leitor.nextLine(); 
            }
        }
        

        

        leitor.close();
    
    }
}
