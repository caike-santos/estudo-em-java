package Lista1.Parte2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorFrota {
    
    private List<Veiculo> frota = new ArrayList<>();

    // Adiciona um veículo à coleção em memória
    public void adicionarVeiculo(Veiculo v) {
        if (v != null) {
            frota.add(v);
        }
    }

    // Escrever dados formatados por linha utilizando BufferedWriter
    public void salvarDadosEmArquivo(String nomeArquivo) {
        // O uso do try-with-resources garante o fechamento automático do BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            
            for (Veiculo v : frota) {
                // Identifica o tipo do veículo para salvar o atributo correspondente
                if (v instanceof Carro) {
                    Carro c = (Carro) v;
                    writer.write("Carro;" + c.getPlaca() + ";" + c.getCustoFixo() + ";" + c.getQuilometragem());
                } else if (v instanceof Caminhao) {
                    Caminhao cam = (Caminhao) v;
                    writer.write("Caminhao;" + cam.getPlaca() + ";" + cam.getCustoFixo() + ";" + cam.getToneladasCarga());
                }
                writer.newLine(); // Pula para a próxima linha do arquivo
            }
            System.out.println("Dados salvos com sucesso no arquivo: " + nomeArquivo);
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    // Ler e processar dados do arquivo em tela utilizando BufferedReader
    public void lerDadosDoArquivo(String nomeArquivo) {
        // O uso do try-with-resources garante o fechamento automático do BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            System.out.println("\n--- LENDO E PROCESSANDO DADOS DO ARQUIVO NA TELA ---");
            
            while ((linha = reader.readLine()) != null) {
                // Separa os dados da linha com base no delimitador ";"
                String[] dados = linha.split(";");
                
                if (dados.length >= 4) {
                    String tipo = dados[0];
                    String placa = dados[1];
                    double custoFixo = Double.parseDouble(dados[2]);

                    if (tipo.equals("Carro")) {
                        int quilometragem = Integer.parseInt(dados[3]);
                        System.out.printf("[TIPO: Carro] Placa: %s | Custo Fixo: R$ %.2f | Quilometragem: %d KM\n", 
                                          placa, custoFixo, quilometragem);
                    } else if (tipo.equals("Caminhao")) {
                        double toneladasCarga = Double.parseDouble(dados[3]);
                        System.out.printf("[TIPO: Caminhão] Placa: %s | Custo Fixo: R$ %.2f | Carga: %.2f Toneladas\n", 
                                          placa, custoFixo, toneladasCarga);
                    }
                }
            }
            System.out.println("---------------------------------------------------\n");
            
        } catch (IOException e) {
            System.err.println("Erro ao ler os dados do arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao processar/converter os dados numéricos do arquivo: " + e.getMessage());
        }
    }
}
