package com.veterinaria;

import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Pet {
    public static Parent criarCena(String atendente){
        //header
        Image logo = new Image(Pet.class.getResource("/imagens/pet.png").toExternalForm());
        ImageView visulogoCadastro = new ImageView(logo);
        visulogoCadastro.setFitHeight(60);
        visulogoCadastro.setPreserveRatio(true);

        Label lblTituloCadastro = new Label("Cadastro do Pet");
        lblTituloCadastro.getStyleClass().add("titulo");

         Button btnVoltar = new Button("Voltar");

        HBox header = new HBox(20);
        header.getChildren().addAll(visulogoCadastro, lblTituloCadastro, btnVoltar);
        header.getStyleClass().add("header");
        //fimHeader

        //formulario
        GridPane gridPet = new GridPane();
        gridPet.setAlignment(Pos.CENTER);
        gridPet.setHgap(10);
        gridPet.setVgap(15);


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

        gridPet.add(lblNome, 0, 0);
        gridPet.add(txtNome, 1, 0);

        gridPet.add(lblIdade, 0, 1);
        gridPet.add(txtIdade, 1, 1);

        gridPet.add(lblRaca, 0, 2);
        gridPet.add(txtRaca, 1, 2);

        gridPet.add(lblSexo, 0, 3);
        gridPet.add(cbmSexo, 1, 3);

        Button btnCadastrar = new Button("Cadastrar Pet");

        TextArea cadastro = new TextArea();
        cadastro.setEditable(false);
        cadastro.setMaxWidth(300);
        cadastro.setPrefHeight(150);

          btnVoltar.setOnAction(evento -> {
            App.inserirCena(Home.criarCena(atendente));
        });

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
                    if(!idade.matches("\\d*")){

                        Alert avisonum = new Alert(Alert.AlertType.WARNING);
                        
                        avisonum.setTitle("Erro");
                        avisonum.setHeaderText("Erro de Preenchimento");
                        avisonum.setContentText("Preencha o campo idade com numeros");
                        avisonum.showAndWait();
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
        //fimCadastro
        

        //montandoCena
        //HBox linhaNome = new HBox(10);
        //HBox linhaIdade = new HBox(10);
        //HBox linhaRaca = new HBox(10);
        //HBox linhaSexo = new HBox(10); 
        HBox linhaBtn = new HBox(10);
        HBox linhaCadastro = new HBox(10);

        //linhaNome.getChildren().addAll(lblNome, txtNome);
        //linhaIdade.getChildren().addAll(lblIdade, txtIdade);
        //linhaRaca.getChildren().addAll(lblRaca, txtRaca);
        //linhaSexo.getChildren().addAll(lblSexo, cbmSexo);
        linhaBtn.getChildren().addAll(btnCadastrar, btnFinalizar);
        linhaCadastro.getChildren().add(cadastro);
       

        VBox vertical = new VBox(30);

        Arrays.asList(header, linhaBtn, linhaCadastro)
      .forEach(linha -> linha.setAlignment(Pos.CENTER));
        
        vertical.setAlignment(Pos.CENTER); 

        vertical.getChildren().addAll(header, gridPet, linhaBtn, linhaCadastro);
        

        //VBox.setVgrow(linhaCadastro, Priority.ALWAYS);
        //HBox.setHgrow(linhaNome, Priority.ALWAYS);

        //vertical.setMaxWidth(2000);
         return vertical;
         
        //fimMontandoCena
        //fimCadastro
    }
}
