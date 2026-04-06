package com.veterinaria;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Cadastro {
    public static Parent criarCena(String atendente){
        TabPane painelDeAbas = new TabPane();

        Tab abaCliente = new Tab("Dados do Cliente");
        abaCliente.setClosable(false);
        
        abaCliente.setContent(CadastroCliente.criarCena(atendente));

        Tab abaPet = new Tab("Dados do Pet");
        abaPet.setClosable(false);

        abaPet.setContent(CadastroPet.criarCena(atendente));

        painelDeAbas.getTabs().addAll(abaCliente, abaPet);

        Button btnSalvarTudo = new Button("Salvar Cliente e Pet");
        btnSalvarTudo.getStyleClass().add("btn_salvar");

        Button btnCancelar = new Button("Cancelar");

         btnCancelar.setOnAction(evento -> {
            Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Atenção");
            confirmar.setHeaderText("CANCELAR CADASTRO!!!");
            confirmar.setContentText("TEM CERTEZA QUE QUER CANCELAR?");

            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmar.getButtonTypes().setAll(btnSim, btnNao);
    
            confirmar.showAndWait().ifPresent(resposta -> {
                if (resposta == btnSim){
                    CadastroPet.getCadastro().clear();
                    App.inserirCena(Home.criarCena(atendente));
                }
            });
            
    
         });

        HBox rodape = new HBox(50);
        rodape.setAlignment(Pos.CENTER);
        
        
        rodape.setPadding(new Insets(20, 30, 30, 30)); 
        
        
        rodape.getChildren().addAll(btnSalvarTudo, btnCancelar);

        btnSalvarTudo.setOnAction(evento -> {
             String cadastroAtual = CadastroPet.getCadastro().getText();
            if(cadastroAtual.isEmpty()){
                Alert aviso = new Alert(Alert.AlertType.WARNING);
                aviso.setTitle("Erro");
                aviso.setHeaderText("Nenhum cadastro realizado");
                aviso.setContentText("Faça pelo menos um cadastro para poder finalizado");
                aviso.showAndWait();
            }else{
                Cliente c = CadastroCliente.pegarDados(CadastroPet.getListaPet());

                Alert concluido = new Alert(Alert.AlertType.INFORMATION);
                concluido.setTitle("Cadastro Finalizado");
                concluido.setHeaderText("Imprimindo Cadastro");
                String nomes = ""; 
                for(Pet pet : CadastroPet.getListaPet()){
                   nomes += pet.getNome() + ", ";
                }
                concluido.setContentText(c.getNome() + " seus(s) pet(s) " + nomes + "foi cadastrado com Sucesso");
                concluido.showAndWait();
                CadastroPet.getCadastro().clear();
                App.inserirCena(Home.criarCena(atendente));
            }
            
        });
            

        VBox telaPrincipal = new VBox();
        telaPrincipal.setAlignment(Pos.CENTER);
        VBox.setVgrow(painelDeAbas, Priority.ALWAYS);

        telaPrincipal.getChildren().addAll(painelDeAbas, rodape);

        return telaPrincipal;
    }
}

