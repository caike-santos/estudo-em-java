package Lista2.View;

import java.io.IOException;
import java.util.List;
import Lista2.Model.Caminhao;
import Lista2.Model.Carro;
import Lista2.Model.PlacaInvalidaException;
import Lista2.Model.Veiculo;
import Lista2.Control.FrotaController;

public class FrotaView {
    private final FrotaController controller = new FrotaController();

    public void iniciarFluxoHomologacao() {
        System.out.println("=================================================");
        System.out.println("      SISTEMA DE GESTÃO DE FROTA (PADRÃO MVC)    ");
        System.out.println("=================================================\n");

        System.out.println("👉 Ação 1: Testando validação de placa incorreta...");
        try {
            Carro carroInvalido = new Carro("ABC12", 200.0, 1000);
            System.out.println("Erro: O sistema permitiu uma placa inválida.");
        } catch (PlacaInvalidaException e) {
            System.out.println("Sucesso! Exceção capturada elegantemente na View.");
            System.out.println("   Mensagem: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------------\n");

       
        System.out.println("Ação 2: Inserindo veículos válidos através do Controller...");
        try {
            controller.adicionarVeiculo(new Carro("BRA2E19", 400.0, 12000));
            controller.adicionarVeiculo(new Caminhao("XYZ9999", 1500.0, 25.4));
            System.out.println("Veículos instanciados e alocados na memória do Controller.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------------\n");

        
        String arquivoAlvo = "frota_mvc.txt";
        System.out.println("Ação 3: Gravando dados no arquivo '" + arquivoAlvo + "'...");
        try {
            controller.salvarFrota(arquivoAlvo);
            System.out.println("Estado da frota exportado com sucesso via Repository!");
        } catch (IOException e) {
            System.err.println("Falha crítica de I/O ao gravar o arquivo: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------------\n");

        
        System.out.println("Ação 4: Carregando arquivo e renderizando linhas processadas...");
        try {
            controller.carregarFrota(arquivoAlvo);
            List<Veiculo> dadosCarregados = controller.getFrota();

            System.out.println("\n--- RELATÓRIO RENDERIZADO PELA VIEW ---");
            for (Veiculo v : dadosCarregados) {
                if (v instanceof Carro) {
                    Carro c = (Carro) v;
                    System.out.printf("[CARRO] Placa: %s | Custo Fixo: R$ %.2f | KM: %d | Custo Total: R$ %.2f | IPVA: R$ %.2f\n",
                            c.getPlaca(), c.getCustoFixo(), c.getQuilometragem(), c.calcularCustoTotal(), c.calcularIPVA());
                } else if (v instanceof Caminhao) {
                    Caminhao cam = (Caminhao) v;
                    System.out.printf("[CAMINHÃO] Placa: %s | Custo Fixo: R$ %.2f | Carga: %.2f Ton | Custo Total: R$ %.2f | IPVA: R$ %.2f\n",
                            cam.getPlaca(), cam.getCustoFixo(), cam.getToneladasCarga(), cam.calcularCustoTotal(), cam.calcularIPVA());
                }
            }
            System.out.println("----------------------------------------\n");

        } catch (IOException e) {
            System.err.println("Falha crítica de I/O ao ler o arquivo: " + e.getMessage());
        }

        System.out.println("=================================================");
        System.out.println("          HOMOLOGAÇÃO CONCLUÍDA COM SUCESSO      ");
        System.out.println("=================================================");
    }
}
