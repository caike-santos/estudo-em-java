package aula05;

public class Vetores {
    public static void exibirCardapio(String[] pratos, double[] valores){
        System.out.println("Cardapio do Restaurante");
        System.out.println("Prato ---- Preço");
        int i;
        for(i=0; i<pratos.length; i++){
            System.out.printf("%s --> R$ %.2f\n", pratos[i], valores[i]);
        }
        System.out.println("É cobrada uma taxa de serviço de 10% para pedidos acima de R$100");
    }

    public static void exibirPreco(int mesa, double valorFinal, String[] pratos, int[][] comanda){
        int i;
        System.out.printf("A mesa %d pediu: \n", mesa);
        for(i = 0; i<comanda[mesa].length; i++){
            if(comanda[mesa][i]>0){
            System.err.printf("%d Prato(s) de %s;\n", comanda[mesa][i], pratos[i]);
        }
        }
        if(valorFinal>100){
            System.out.println("Foi cobrada uma taxa adicional de 10%");
        }
        System.out.printf("Valor Total: R$ %.2f", valorFinal);
    }

    public static double calcularPreço(int mesa, int[][] comanda, double[] valores){
        int i;
        double valor = 0;
        for(i=0; i<comanda[mesa].length; i++){
            valor += (comanda[mesa][i] * valores[i]);
        }

        if(valor > 100){
            valor *= 1.1;
        }

        return valor;
    }

    public static void main(String[] args) {
        
       
        String[] nomesPratos = {"Arroz com Batata", "Feijoada", "Macarronada", "Salada da Casa"};
        double[] precosPratos = {20.50, 30.00, 25.00, 15.00};

       
        int[][] comandas = new int[3][4];

        
        comandas[0][0] = 2; 
        comandas[0][3] = 1;
        comandas[0][1] = 4;
       
        
       
        comandas[1][2] = 3; 
        comandas[1][3] = 2; 

        exibirCardapio(nomesPratos, precosPratos);
        
        System.out.println("\n---------------------------");

        double valor = calcularPreço(0, comandas, precosPratos);
        exibirPreco(0, valor, nomesPratos, comandas);
       
    }
}
