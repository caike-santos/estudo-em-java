package aula03;
import java.util.Scanner;
public class Variaveis {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        double preco = 0;
        int quant;
        double taxa;
        double precoFinal;
        boolean validacao = false;
        int cod = 0;
        
        while(!validacao){
        System.out.println("Codigo--Prato--Preço");
        System.out.println("1. Arroz com batata: 20.50");
        System.out.println("2. Feijoada com carne: 30.00");

        System.out.println("Escolha o codigo do seu prato: ");
        cod = leitor.nextInt();
        
        switch (cod) {
            case 1:
                preco = 20.50;
                validacao = true;
                break;
            case 2:
                preco = 30.00;
                validacao = true;
                break;
            default:
                System.out.println("Codigo Invalido");
                validacao = false;
                break;
        }
    }
        System.out.println("Digite a quantidade: ");
        quant = leitor.nextInt();

        preco *= quant;

        taxa = preco * 0.1;

        precoFinal = preco + taxa;

        switch (cod) {
            case 1:
                System.out.printf("Voce pediu  %d  Arroz com batata, o preço do(s) prato(s) ficou  %.2f Reais e foi adicionada uma taxa de serviço de %.2f Reais totalizando: %.2f Reais, Obrigado volte sempre!\n", quant, preco, taxa, precoFinal);
                break;
            case 2:
                System.out.printf("Voce pediu  %d  Feijoada, o preço do(s) prato(s) ficou  %.2f Reais e foi adicionada uma taxa de serviço de %.2f Reais totalizando: %.2f Reais, Obrigado volte sempre!\n", quant, preco, taxa, precoFinal);
                break;
            default:
                break;
        }

        leitor.close();

    }
}
