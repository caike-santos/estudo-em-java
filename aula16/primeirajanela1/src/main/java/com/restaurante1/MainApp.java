package com.restaurante1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        
        Label lblTitulo = new Label("Cadastre seus pets na nossa veterinaria");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: darkred;");

        Label lblNome = new Label("Nome do pet:");
        TextField txtNome = new TextField();
        txtNome.setMaxWidth(250);
        

        Label lblIdade = new Label("Idade do pet:");
        TextField txtIdade = new TextField();
        txtIdade.setMaxWidth(250);

        Label lblRaca = new Label("Raça do pet:");
        TextField txtRaca = new TextField();
        txtRaca.setMaxWidth(250);

        Label lblSexo = new Label("Sexo do pet:");
        ComboBox<String> cbmSexo = new ComboBox<>();
        cbmSexo.getItems().addAll("Macho", "Femea");
        cbmSexo.getSelectionModel().selectFirst();

        Button btnCadastrar = new Button("Cadastrar Pet");

        TextArea cadastro = new TextArea();
        cadastro.setEditable(false);
        cadastro.setMaxWidth(300);
        cadastro.setPrefHeight(150);

        btnCadastrar.setOnAction(evento -> 
            {
                String nome = txtNome.getText();
                String idade = txtIdade.getText();
                String raca = txtRaca.getText();
                String sexo = cbmSexo.getValue();

                if(nome.isEmpty() || idade.isEmpty() || raca.isEmpty() || sexo.isEmpty()){
                    Alert aviso = new Alert(Alert.AlertType.WARNING);
                    aviso.setTitle("Erro");
                    aviso.setHeaderText("Erro de Preenchimento");
                    aviso.setContentText("Preencha todos os campos");
                    aviso.showAndWait();
                }else{
                    System.out.println("nome: "+ nome);
                System.out.println("idade: "+ idade);
                System.out.println("raca: "+ raca);
                System.out.println("sexo: "+ sexo);

                cadastro.appendText("\nCadastro concluido...\n");
                cadastro.appendText("nome: "+ nome+"\n");
                cadastro.appendText("idade: "+ idade+"\n");
                cadastro.appendText("raca: "+ raca+"\n");
                cadastro.appendText("sexo: "+ sexo+"\n");

                txtIdade.clear();
                txtNome.clear();
                txtRaca.clear();
                
                }
                
        });

        Button btnFinalizar = new Button("Finalizar Cadastro");

        btnFinalizar.setOnAction(evento -> {
            String cadastroAtual = cadastro.getText();
            if(cadastroAtual.isEmpty()){
                Alert aviso = new Alert(Alert.AlertType.WARNING);
                aviso.setTitle("Erro");
                aviso.setHeaderText("Nenhum cadastro realizado");
                aviso.setContentText("Faça pelo menos um cadastro para poder finalizado");
                aviso.showAndWait();
            }else{
                Alert concluido = new Alert(Alert.AlertType.INFORMATION);
                concluido.setTitle("Cadastro Finalizado");
                concluido.setHeaderText("Imprimindo Cadastro");
                concluido.setContentText("Seu pet foi cadastrado com Sucesso");
                concluido.showAndWait();
                cadastro.clear();
            }
        });

        HBox linhaNome = new HBox(10);
        HBox linhaIdade = new HBox(10);
        HBox linhaRaca = new HBox(10);
        HBox linhaSexo = new HBox(10); 
        HBox linhaBtn = new HBox(10);

        linhaNome.getChildren().addAll(lblNome, txtNome);
        linhaIdade.getChildren().addAll(lblIdade, txtIdade);
        linhaRaca.getChildren().addAll(lblRaca, txtRaca);
        linhaSexo.getChildren().addAll(lblSexo, cbmSexo);
        linhaBtn.getChildren().addAll(btnCadastrar, btnFinalizar);
       

        VBox vertical = new VBox(20);

        vertical.getChildren().addAll(lblTitulo, linhaNome, linhaIdade, linhaRaca, linhaSexo, linhaBtn, cadastro);

        Scene scene = new Scene(vertical, 1000, 600);

        stage.setTitle("Cadastrar Pet");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
