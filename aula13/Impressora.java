package aula13;

public class Impressora {
    public static void imprimir(Imprimivel documento){
        System.out.println("---------Imprimindo----------");
        documento.mostrarNaTela();
        System.out.println("-------Fim da Impressao------");
    }
}
