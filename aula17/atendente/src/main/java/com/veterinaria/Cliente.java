package com.veterinaria;

import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class Cliente {
    public static Parent criarCena(String atendente){
         //header
        Image logo = new Image(Pet.class.getResource("/imagens/pet.png").toExternalForm());
        ImageView visulogoCadastro = new ImageView(logo);
        visulogoCadastro.setFitHeight(60);
        visulogoCadastro.setPreserveRatio(true);

        Label lblTituloCadastro = new Label("Cadastro do Cliente");
        lblTituloCadastro.getStyleClass().add("titulo");

         Button btnVoltar = new Button("Voltar");

        HBox header = new HBox(20);
        header.getChildren().addAll(visulogoCadastro, lblTituloCadastro, btnVoltar);
        header.getStyleClass().add("header");
        //fimHeader

        GridPane gridDados = new GridPane();
        gridDados.setHgap(10);
        gridDados.setVgap(15);

        Label lblSubTituloDados = new Label("Dados Pessoais");
        lblSubTituloDados.getStyleClass().add("subtitulo");

        //HBox linhaSubTituloDados = new HBox(10);
        //linhaSubTituloDados.getChildren().add(lblSubTituloDados);

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();
        txtNome.setMaxWidth(150);
        //HBox linhaNome = new HBox(10);
        //linhaNome.getChildren().addAll(lblNome, txtNome);

        Label lblIdade = new Label("Idade:");
        TextField txtIdade = new TextField();
        txtIdade.setMaxWidth(150);
        //HBox linhaIdade = new HBox(10);
        //linhaIdade.getChildren().addAll(lblIdade, txtIdade);

        Label lblCpf = new Label("Cpf:");
        TextField txtCpf = new TextField();
        txtCpf.setMaxWidth(150);
        //HBox linhaCpf = new HBox(10);
        //linhaCpf.getChildren().addAll(lblCpf, txtCpf);

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        txtEmail.setMaxWidth(150);
        //HBox linhaEmail = new HBox(10);
        //linhaEmail.getChildren().addAll(lblEmail, txtEmail);

        Label lblTelefone = new Label("Telefone:");
        TextField txtTelefone = new TextField();
        txtTelefone.setMaxWidth(150);
        //HBox linhaTelefone = new HBox(10);
        //linhaTelefone.getChildren().addAll(lblTelefone, txtTelefone);

        //VBox dadosPessoais = new VBox(20);
        //dadosPessoais.getChildren().addAll(linhaSubTituloDados, linhaNome, linhaIdade, linhaCpf, linhaEmail, linhaTelefone);

        gridDados.add(lblSubTituloDados, 0, 0, 2, 1);
        GridPane.setHalignment(lblSubTituloDados, javafx.geometry.HPos.CENTER);

        gridDados.add(lblNome, 0, 1);
        gridDados.add(txtNome, 1, 1);

        gridDados.add(lblIdade, 0, 2);
        gridDados.add(txtIdade, 1, 2);

        gridDados.add(lblCpf, 0, 3);
        gridDados.add(txtCpf, 1, 3);

        gridDados.add(lblEmail, 0, 4);
        gridDados.add(txtEmail, 1, 4);

        gridDados.add(lblTelefone, 0, 5);
        gridDados.add(txtTelefone, 1, 5);

        GridPane gridEndereco = new GridPane();
        gridEndereco.setHgap(10);
        gridEndereco.setVgap(15);

        Label lblSubTituloEndereco = new Label("Endereço");
        lblSubTituloEndereco.getStyleClass().add("subtitulo");

        //HBox linhaSubTituloEndereco = new HBox(10);
        //linhaSubTituloEndereco.getChildren().add(lblSubTituloEndereco);

        Label lblBairro = new Label("Bairro:");
        TextField txtBairro = new TextField();
        txtBairro.setMaxWidth(150);
        //HBox linhaBairro = new HBox(10);
        //linhaBairro.getChildren().addAll(lblBairro, txtBairro);

        Label lblRua = new Label("Rua:");
        TextField txtRua = new TextField();
        txtRua.setMaxWidth(150);
       // HBox linhaRua = new HBox(10);
        //linhaRua.getChildren().addAll(lblRua, txtRua);

        Label lblNumero = new Label("Numero:");
        TextField txtNumero = new TextField();
        txtNumero.setMaxWidth(150);
        //HBox linhaNumero = new HBox(10);
        //linhaNumero.getChildren().addAll(lblNumero, txtNumero);

        Label lblCep = new Label("Cep:");
        TextField txtCep = new TextField();
        txtCep.setMaxWidth(150);
        //HBox linhaCep = new HBox(10);
        //linhaCep.getChildren().addAll(lblCep, txtCep);

        Label lblComplemento = new Label("Complemento:");
        TextArea areaComplemento = new TextArea();
        areaComplemento.setEditable(true);
        areaComplemento.setMaxWidth(150);
        areaComplemento.setPrefHeight(75);
        //VBox colunaComplemento = new VBox(10);
       // colunaComplemento.getChildren().addAll(lblComplemento, areaComplemento);
        //HBox linhaComplemento = new HBox(10);
        //linhaComplemento.getChildren().addAll(lblComplemento, areaComplemento);

        //VBox endereco = new VBox(20);
        //endereco.getChildren().addAll(linhaSubTituloEndereco, linhaBairro, linhaRua, linhaNumero, linhaCep, linhaComplemento);

        gridEndereco.add(lblSubTituloEndereco, 0, 0, 2, 1);
        GridPane.setHalignment(lblSubTituloEndereco, javafx.geometry.HPos.CENTER);

        gridEndereco.add(lblBairro, 0, 1);
        gridEndereco.add(txtBairro, 1, 1);

        gridEndereco.add(lblRua, 0, 2);
        gridEndereco.add(txtRua, 1, 2);

        gridEndereco.add(lblCep, 0, 3);
        gridEndereco.add(txtCep, 1, 3);

        gridEndereco.add(lblNumero, 0, 4);
        gridEndereco.add(txtNumero, 1, 4);

        gridEndereco.add(lblComplemento, 0, 5);
        gridEndereco.add(areaComplemento, 1, 5);
        
        HBox cadastro = new HBox(100);
        cadastro.getChildren().addAll(gridDados, gridEndereco);

        Button btnFinalizar = new Button("Finalizar Cadastro");
        HBox linhaBtn = new HBox(10);
        linhaBtn.getChildren().add(btnFinalizar);
        btnFinalizar.setOnAction(evento -> {
            Alert concluido = new Alert(Alert.AlertType.INFORMATION);
            concluido.setTitle("Sucesso");
            concluido.setHeaderText(":)");
            concluido.setContentText("Cadastro Finalizado");
            concluido.showAndWait();
            Arrays.asList(txtBairro, txtCep, txtCpf, txtEmail, txtIdade, txtNome, txtNumero, txtRua, txtTelefone).forEach(txt -> txt.clear());
        });

        btnVoltar.setOnAction(evento -> {
            App.inserirCena(Home.criarCena(atendente));
        });

        Arrays.asList(header, cadastro, linhaBtn).forEach(linha -> linha.setAlignment(Pos.CENTER));

        VBox vertical = new VBox(50);
        vertical.setAlignment(Pos.CENTER);
        vertical.getChildren().addAll(header, cadastro, linhaBtn);
        

        return vertical;
    }
}
