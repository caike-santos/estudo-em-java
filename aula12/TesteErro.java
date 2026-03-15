package aula12;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TesteErro {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int idade = 0;
        boolean flag = false;

        while(!flag){
            try {
                System.out.println("Digite sua idade(apenas numeros): ");
                idade = leitor.nextInt();
                flag = true;
            } catch (InputMismatchException erro) {
                System.err.println("ERRO-Digite apenas numeros!!!");
                leitor.nextLine();
            }

            if(idade >= 18){
                    System.out.println("Maior de idade");
                }else{
                    System.out.println("Menor de idade");
                }
        }

        System.out.println("Sucesso a idade registrada foi: "+ idade);
        leitor.close();

    }
}
