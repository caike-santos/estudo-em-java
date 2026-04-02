package aula15;
import java.util.Scanner;
public class excecaoArray {
    public static void main(String[] args) {
        String[] pratos = {"Arroz", "Feijao", "Macarrao"};
        boolean flag = false;
        Scanner leitor = new Scanner(System.in);
        int indice = -1;
        System.out.println("----------Menu---------");
        for(int i = 0; i<pratos.length; i++){
            System.out.println("Indice("+i+"): " + pratos[i]);
        }

        while(!flag){
            try{
                System.out.println("Insira o indice do Prato:");
                indice = leitor.nextInt();
                System.out.println("Prato escolhido: "+ pratos[indice]);
                flag = true;
            }catch(ArrayIndexOutOfBoundsException erro){
                System.err.println("Prato nao encontrado digite o indice novamente");
            }catch(Exception erro){
                 System.err.println("Erro de digitação: Insira apenas números válidos.");
                leitor.nextLine();
            }
        }

        leitor.close();

    }
}
