package com.veterinaria;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class Home {
    public static Parent criarCena(String nomeAtendente){
        Image logo = new Image(Login.class.getResource("/imagens/pet.png").toExternalForm());
        ImageView mostarLogo = new ImageView(logo);
        mostarLogo.setFitHeight(60);
        mostarLogo.setPreserveRatio(true);

        Label lblTitulo = new Label("Tela de cadastro");
        lblTitulo.getStyleClass().add("titulo");

        Button btnDeslogar = new Button("Deslogar");

        HBox linhaHeader = new HBox(20);
        linhaHeader.setPadding(new Insets(30, 40, 40, 40)); 
        linhaHeader.setAlignment(Pos.CENTER_LEFT);
        linhaHeader.getChildren().addAll(mostarLogo, lblTitulo, btnDeslogar);
        

        Label lblSubTitulo = new Label("Bem-Vindo(a) " + nomeAtendente);
        lblSubTitulo.getStyleClass().add("subtitulo");

        HBox linhaSubTitulo = new HBox(10);
        linhaSubTitulo.getChildren().add(lblSubTitulo);

        Label lblBuscar = new Label("Buscar cliente, insira o CPF");
        lblBuscar.getStyleClass().add("subtitulo");
        HBox linhaBuscar = new HBox();
        linhaBuscar.getChildren().add(lblBuscar);

        TextField txtCpf = new TextField();
        txtCpf.setMaxWidth(250);
        HBox linhaCpf = new HBox();
        linhaCpf.getChildren().add(txtCpf);

        Button btnBuscar = new Button("Buscar");
        HBox linhaBtnBuscar = new HBox();
        linhaBtnBuscar.getChildren().add(btnBuscar);

        VBox colunaBuscar = new VBox(15);
        colunaBuscar.getChildren().addAll(linhaBuscar, linhaCpf, linhaBtnBuscar);
        


        Label lblCadastro = new Label("Cadastre um cliente");
        lblCadastro.getStyleClass().add("subtitulo");
        HBox linhaCadastro = new HBox();
        linhaCadastro.getChildren().add(lblCadastro);

        Button btnCadastro = new Button("Cadastrar");

        HBox linhaBtn = new HBox();
        linhaBtn.getChildren().add(btnCadastro);

        VBox colunaCadastrar = new VBox(15);
        colunaCadastrar.setAlignment(Pos.CENTER);
        colunaCadastrar.getChildren().addAll(linhaCadastro, linhaBtn);


        HBox linhaHome = new HBox(150);
        linhaHome.getChildren().addAll(colunaBuscar, colunaCadastrar);

         Arrays.asList(linhaSubTitulo, linhaBtn, linhaBtnBuscar, linhaBuscar, linhaCadastro, linhaCpf, linhaHome).forEach(linha -> linha.setAlignment(Pos.CENTER));

        VBox verticalHome = new VBox(120);
        verticalHome.getChildren().addAll(linhaHeader, linhaHome);
        

        btnCadastro.setOnAction(evento -> {
            App.inserirCena(Cadastro.criarCena(nomeAtendente));
        });

    
         btnDeslogar.setOnAction(evento -> {
            App.inserirCena(Login.criarCena());
        });

        return verticalHome;

    }
}
