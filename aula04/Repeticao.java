package aula04;
import java.util.Scanner;
public class Repeticao {
    public static void main(String[] args){
        double precoTotal = 0.0;
        double valor = 0;
        Scanner leitor = new Scanner(System.in);
        do{
            
            System.out.println("Digite um valor: ");
            valor = leitor.nextDouble();
            precoTotal += valor;

        }while(valor != 0);
        
        if(precoTotal>100){
            precoTotal *= 0.9;
        }

        System.out.printf("O valor total foi %.2f", precoTotal);

        leitor.close();
    }
}
