package Lista1.Parte2;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("      HOMOLOGAÇÃO DO SISTEMA DE FROTA            ");
        System.out.println("=================================================\n");

        
        System.out.println("Ação 1: Testando validação de placa incorreta...");
        try {
            Carro carroInvalido = new Carro("ABC12", 250.0, 1000);
            System.out.println("Erro: O sistema permitiu criar o veículo com placa inválida!");
        } catch (PlacaInvalidaException e) {
            System.out.println("Sucesso! Exceção capturada conforme planejado.");
            System.out.println("   Mensagem da exceção: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------------\n");

       
        System.out.println("Ação 2: Instanciando e adicionando veículos válidos...");
        GerenciadorFrota gerenciador = new GerenciadorFrota();

       
        Carro carroValido = new Carro("BRA2E19", 350.0, 15000);      
        Caminhao caminhaoValido = new Caminhao("HQW4321", 1200.0, 18.5); 

        gerenciador.adicionarVeiculo(carroValido);
        gerenciador.adicionarVeiculo(caminhaoValido);
        System.out.println("Veículos adicionados com sucesso à memória do gerenciador.");

        System.out.println("\n-------------------------------------------------\n");

        
        System.out.println("Ação 3: Gravando dados no arquivo...");
        String arquivoAlvo = "frota.txt";
        gerenciador.salvarDadosEmArquivo(arquivoAlvo);

        System.out.println("\n-------------------------------------------------\n");

       
        System.out.println("Ação 4: Carregando e exibindo dados em tela...");
        gerenciador.lerDadosDoArquivo(arquivoAlvo);

        System.out.println("=================================================");
        System.out.println("          FIM DA EXECUÇÃO COM SUCESSO            ");
        System.out.println("=================================================");
    }
}