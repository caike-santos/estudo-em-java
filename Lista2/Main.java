package Lista2;

import Lista2.View.FrotaView;

public class Main {
    public static void main(String[] args) {
        // Instancia a camada de visualização e inicia o ciclo de vida da aplicação
        FrotaView interfaceUsuario = new FrotaView();
        interfaceUsuario.iniciarFluxoHomologacao();
    }
}
