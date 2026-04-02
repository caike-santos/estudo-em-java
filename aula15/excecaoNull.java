package aula15;

public class excecaoNull {
    public static void main(String[] args) {
        String nome = null;

        try{
            System.out.println("Nome: " + nome.length());
        }catch(NullPointerException erro){
            System.out.println("Erro: O cliente não foi registado no sistema (valor nulo).");
        }
    }
}
