package com.restaurante1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;  
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import java.util.Arrays;


public class MainApp extends Application {
    
    private Scene cenaLogin;
    private Scene cenaCadastro;
    

    @Override
    public void start(Stage stage) {

       //login

       //header
       Image logo = new Image(getClass().getResourceAsStream("/images/pet.png"));
        ImageView visulogo = new ImageView(logo);
        visulogo.setFitHeight(60);
        visulogo.setPreserveRatio(true);

        Label lblTitulo = new Label("Logue para se cadastrar");
        lblTitulo.getStyleClass().add("titulo");
        //lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: darkred;");

        Button btnDeslogar = new Button("Deslogar");

        btnDeslogar.setOnAction(evento -> {
            stage.setTitle("Login");
            stage.setScene(cenaLogin);
            stage.show();
        });
        

        HBox headerLogin = new HBox(10);
        headerLogin.getChildren().addAll(visulogo, lblTitulo);
        headerLogin.getStyleClass().add("header");
        //fimHeader

        //formularioLogin
        Label lblusuario = new Label("Usuario");
        TextField txtUsuario = new TextField();
        txtUsuario.setMaxWidth(250); 
       
        Label lblsenha = new Label("Senha");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setMaxWidth(250);

        Button btnLogar = new Button("Entrar");
        

        HBox linhaUsuario = new HBox(10);
        linhaUsuario.getChildren().addAll(lblusuario, txtUsuario);

        HBox linhaSenha = new HBox(10);
        linhaSenha.getChildren().addAll(lblsenha, txtSenha);

        HBox linhaLogar = new HBox(10);
        linhaLogar.getChildren().add(btnLogar);
        //fimFormularioLogin

        //montandoCena
        VBox verticalLogin = new VBox(15);
        verticalLogin.getChildren().addAll(headerLogin, linhaUsuario, linhaSenha, btnLogar);

        verticalLogin.setAlignment(Pos.CENTER);

        Arrays.asList(headerLogin, linhaUsuario, linhaSenha, linhaLogar).forEach(linha -> linha.setAlignment(Pos.CENTER));

        cenaLogin = new Scene(verticalLogin, 1000, 600);
        //fimMontandoCena
        
        //Cadastro

        //header
        ImageView visulogoCadastro = new ImageView(logo);
        visulogoCadastro.setFitHeight(60);
        visulogoCadastro.setPreserveRatio(true);

        Label lblTituloCadastro = new Label("Cadastre seus pets na nossa veterinaria");
        lblTituloCadastro.getStyleClass().add("titulo");

        HBox header = new HBox(20);
        header.getChildren().addAll(visulogoCadastro, lblTituloCadastro, btnDeslogar);
        header.getStyleClass().add("header");
        //fimHeader

        //formulario
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
        HBox linhaNome = new HBox(10);
        HBox linhaIdade = new HBox(10);
        HBox linhaRaca = new HBox(10);
        HBox linhaSexo = new HBox(10); 
        HBox linhaBtn = new HBox(10);
        HBox linhaCadastro = new HBox(10);

        linhaNome.getChildren().addAll(lblNome, txtNome);
        linhaIdade.getChildren().addAll(lblIdade, txtIdade);
        linhaRaca.getChildren().addAll(lblRaca, txtRaca);
        linhaSexo.getChildren().addAll(lblSexo, cbmSexo);
        linhaBtn.getChildren().addAll(btnCadastrar, btnFinalizar);
        linhaCadastro.getChildren().add(cadastro);
       

        VBox vertical = new VBox(30);

        Arrays.asList(header, linhaNome, linhaIdade, linhaRaca, linhaSexo, linhaBtn, linhaCadastro)
      .forEach(linha -> linha.setAlignment(Pos.CENTER));
        
        vertical.setAlignment(Pos.CENTER); 

        vertical.getChildren().addAll(header, linhaNome, linhaIdade, linhaRaca, linhaSexo, linhaBtn, linhaCadastro);
        

        //VBox.setVgrow(linhaCadastro, Priority.ALWAYS);
        //HBox.setHgrow(linhaNome, Priority.ALWAYS);

        //vertical.setMaxWidth(2000);
         cenaCadastro = new Scene(vertical, 1000, 600);
         String css = getClass().getResource("/styles/style.css").toExternalForm();
        cenaCadastro.getStylesheets().add(css);
        //fimMontandoCena
        //fimCadastro

        btnLogar.setOnAction(evento -> {
            
            if(txtUsuario.getText().equals("caike") && txtSenha.getText().equals("123")){
                
                stage.setTitle("Cadastrar Pet");
                stage.setScene(cenaCadastro);
                stage.show();

                //vertical.prefWidthProperty().bind(scene.widthProperty());
                //vertical.prefHeightProperty().bind(scene.heightProperty());
               
            }else{

                Alert erroLogin = new Alert(Alert.AlertType.WARNING);
                erroLogin.setTitle("Erro");
                erroLogin.setHeaderText("Usuario ou Senha invalidos");
                erroLogin.setContentText("Digite o usuario e a senha corretamente");
                erroLogin.showAndWait();
            }
        
        });

        String cssLogin = getClass().getResource("/styles/style.css").toExternalForm();
        cenaLogin.getStylesheets().add(cssLogin);
        stage.setTitle("Login");
        stage.setScene(cenaLogin);
        stage.show();
       //fimLogin

       
    }

    public static void main(String[] args) {
        launch(args);
    }

}
