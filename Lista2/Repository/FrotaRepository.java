package Lista2.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Lista2.Model.Caminhao;
import Lista2.Model.Carro;
import Lista2.Model.Veiculo;

public class FrotaRepository {

    public void salvar(List<Veiculo> frota, String nomeArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Veiculo v : frota) {
                if (v instanceof Carro) {
                    Carro c = (Carro) v;
                    writer.write("Carro;" + c.getPlaca() + ";" + c.getCustoFixo() + ";" + c.getQuilometragem());
                } else if (v instanceof Caminhao) {
                    Caminhao cam = (Caminhao) v;
                    writer.write("Caminhao;" + cam.getPlaca() + ";" + cam.getCustoFixo() + ";" + cam.getToneladasCarga());
                }
                writer.newLine();
            }
        }
    }

    public List<Veiculo> ler(String nomeArquivo) throws IOException {
        List<Veiculo> listaCarregada = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    String tipo = dados[0];
                    String placa = dados[1];
                    double custoFixo = Double.parseDouble(dados[2]);

                    if (tipo.equals("Carro")) {
                        int km = Integer.parseInt(dados[3]);
                        listaCarregada.add(new Carro(placa, custoFixo, km));
                    } else if (tipo.equals("Caminhao")) {
                        double toneladas = Double.parseDouble(dados[3]);
                        listaCarregada.add(new Caminhao(placa, custoFixo, toneladas));
                    }
                }
            }
        }
        return listaCarregada;
    }
}
