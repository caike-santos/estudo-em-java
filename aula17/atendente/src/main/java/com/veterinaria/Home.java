package com.veterinaria;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
public class Home {
    private static TextField txtCpf;
    public static Parent criarCena(String nomeAtendente){
        Image logo = new Image(Login.class.getResource("/imagens/pet.png").toExternalForm());
        ImageView mostarLogo = new ImageView(logo);
        mostarLogo.setFitHeight(60);
        mostarLogo.setPreserveRatio(true);

        Label lblTitulo = new Label("Tela de cadastro");
        lblTitulo.getStyleClass().add("titulo");

        Region mola = new Region();
        HBox.setHgrow(mola, Priority.ALWAYS);

        Button btnDeslogar = new Button("Deslogar");
        btnDeslogar.getStyleClass().add("btnSecundario");

        HBox linhaHeader = new HBox(20);
        linhaHeader.setPadding(new Insets(30, 40, 40, 40)); 
        linhaHeader.setAlignment(Pos.CENTER_LEFT);
        linhaHeader.getChildren().addAll(mostarLogo, lblTitulo, mola, btnDeslogar);
        

        Label lblSubTitulo = new Label("Bem-Vindo(a) " + nomeAtendente);
        lblSubTitulo.getStyleClass().add("subtitulo");

        HBox linhaSubTitulo = new HBox(10);
        linhaSubTitulo.getChildren().add(lblSubTitulo);

        Label lblBuscar = new Label("Buscar cliente, insira o CPF");
        lblBuscar.getStyleClass().add("subtitulo");
        HBox linhaBuscar = new HBox();
        linhaBuscar.getChildren().add(lblBuscar);

        txtCpf = new TextField();
        txtCpf.setMaxWidth(250);
        txtCpf.textProperty().addListener((observable, valorAntigo, valorNovo) -> {

        if (valorNovo.length() > 11) {
            txtCpf.setText(valorAntigo);
        }   
    
        if (!valorNovo.matches("\\d*")) {
            txtCpf.setText(valorNovo.replaceAll("[^\\d]", ""));
        }
         });
        HBox linhaCpf = new HBox();
        linhaCpf.getChildren().add(txtCpf);

        Button btnBuscar = new Button("Buscar");
        HBox linhaBtnBuscar = new HBox();
        linhaBtnBuscar.getChildren().add(btnBuscar);
        btnBuscar.getStyleClass().add("btnSecundario");

        VBox colunaBuscar = new VBox(15);
        colunaBuscar.getChildren().addAll(linhaBuscar, linhaCpf, linhaBtnBuscar);
        


        Label lblClientes = new Label("Exibir clientes cadastrados");
        lblClientes.getStyleClass().add("subtitulo");
        HBox linhaClientes = new HBox();
        linhaClientes.getChildren().add(lblClientes);

        Button btnClientes = new Button("Exibir clientes");

        HBox linhaBtn = new HBox(10);
        linhaBtn.getChildren().addAll(btnClientes);

        VBox colunaCadastrar = new VBox(15);
        colunaCadastrar.setAlignment(Pos.CENTER);
        colunaCadastrar.getChildren().addAll(linhaClientes, linhaBtn);


        HBox linhaHome = new HBox(150);
        linhaHome.getChildren().addAll(colunaBuscar, colunaCadastrar);

         Arrays.asList(linhaSubTitulo, linhaBtn, linhaBtnBuscar, linhaBuscar, linhaClientes, linhaCpf, linhaHome).forEach(linha -> linha.setAlignment(Pos.CENTER));

        VBox verticalHome = new VBox(120);
        verticalHome.getChildren().addAll(linhaHeader, linhaHome);

        btnClientes.setOnAction(evento ->{
            App.inserirCena(ExibirClientes.criarCena());
        });
        
         btnDeslogar.setOnAction(evento -> {
            App.inserirCena(Login.criarCena());
        });

        btnBuscar.setOnAction(evento -> {
            if(txtCpf.getText().isEmpty()){
                CadastroCliente.mostarNaTela("Campo Vazio", nomeAtendente + ", preencha o campo corretamente");
                return;
            }else if(txtCpf.getText().length() < 11 || txtCpf.getText().length() > 11){
                CadastroCliente.mostarNaTela("Quantidade de caracteres invalidos", nomeAtendente + ", preencha o campo corretamente");
                return;
            }
            String cpf = txtCpf.getText();
            if(BancoDeDados.getClientes().isEmpty()){
                Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Atenção!!!");
            confirmar.setHeaderText("Cliente não encontrado!");
            confirmar.setContentText("O cpf de numero: " + cpf.substring(0, 3) + "." + cpf.substring(3 , 6)+ "." + cpf.substring(6, 9) + "-" + cpf.substring(9) + " não foi encontrado, quer cadastrá-lo");

            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmar.getButtonTypes().setAll(btnSim, btnNao);
    
            confirmar.showAndWait().ifPresent(resposta -> {
                if (resposta == btnSim){
                    
                    App.inserirCena(Cadastro.criarCena(nomeAtendente));
                }});
            }else{
                for(Cliente cliente : BancoDeDados.getClientes()){
                    if(cpf.equals(cliente.getCpf())){
                        CadastroCliente.mostarNaTela("Cliente encontrado", "Cliente " + cliente.getNome() + " está cadastrado");
                        return;
                }
            }
            Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Atenção");
            confirmar.setHeaderText("Cliente não encontrado!");
            confirmar.setContentText("O cpf de numero: " + cpf.substring(0, 3) + "." + cpf.substring(3 , 6)+ "." + cpf.substring(6, 9) + "-" + cpf.substring(9) + " não foi encontrado, quer cadastrá-lo");

            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmar.getButtonTypes().setAll(btnSim, btnNao);
    
            confirmar.showAndWait().ifPresent(resposta -> {
                if (resposta == btnSim){
                    
                    App.inserirCena(Cadastro.criarCena(nomeAtendente));
                }});
                
            
            }
            
        });

        return verticalHome;

    }

    public static TextField getTxtCpf() {
        return txtCpf;
    }
}
