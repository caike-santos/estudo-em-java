package aula15;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class excecaoFicheiro {
    public static void main(String[] args) {
        File ficheiro = new File("cardapio_secreto.txt");

        try(Scanner leitorArquivo = new Scanner(ficheiro);){

            System.out.println("Ficheiro aberto e pronto para leitura.");

        }catch(IOException erro){

            System.out.println("Aviso: O ficheiro do cardápio não foi encontrado no disco.");
        }
        
    }
}
