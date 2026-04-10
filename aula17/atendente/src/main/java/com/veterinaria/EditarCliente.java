package com.veterinaria;

import java.util.Arrays;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class EditarCliente {
    private static TextField txtNome;
    private static TextField txtIdade;
    private static TextField txtCpf;
    private static TextField txtEmail;
    private static TextField txtDdd;
    private static TextField txtTelefone;
    private static TextField txtBairro;
    private static TextField txtRua;
    private static TextField txtCep;
    private static TextField txtNumero;
    private static TextArea areaComplemento;
    private static Cliente c;
    public static Parent criarCena(Cliente c1){
        c = c1;
         //header
        Image logo = new Image(CadastroPet.class.getResource("/imagens/pet.png").toExternalForm());
        ImageView visulogoCadastro = new ImageView(logo);
        visulogoCadastro.setFitHeight(60);
        visulogoCadastro.setPreserveRatio(true);

        Label lblTituloCadastro = new Label("Editar Cliente: " + c1.getNome());
        lblTituloCadastro.getStyleClass().add("titulo");

        HBox header = new HBox(20);
        header.getChildren().addAll(visulogoCadastro, lblTituloCadastro);
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
        txtNome = new TextField();
        txtNome.setMaxWidth(150);
        //HBox linhaNome = new HBox(10);
        //linhaNome.getChildren().addAll(lblNome, txtNome);

        Label lblIdade = new Label("Idade:");
        txtIdade = new TextField();
        txtIdade.setMaxWidth(150);
        txtIdade.textProperty().addListener((observable, valorAntigo, valorNovo) -> {

        if (!valorNovo.matches("\\d*")) {
            txtIdade.setText(valorNovo.replaceAll("[^\\d]", ""));
        }
         });
        //HBox linhaIdade = new HBox(10);
        //linhaIdade.getChildren().addAll(lblIdade, txtIdade);

        Label lblCpf = new Label("Cpf:");
        txtCpf = new TextField();
        txtCpf.setMaxWidth(150);
        
        txtCpf.textProperty().addListener((observable, valorAntigo, valorNovo) -> {

        if (valorNovo.length() > 11) {
            txtCpf.setText(valorAntigo);
        }   
    
        if (!valorNovo.matches("\\d*")) {
            txtCpf.setText(valorNovo.replaceAll("[^\\d]", ""));
        }
         });
        //HBox linhaCpf = new HBox(10);
        //linhaCpf.getChildren().addAll(lblCpf, txtCpf);

        Label lblEmail = new Label("Email:");
        txtEmail = new TextField();
        txtEmail.setMaxWidth(150);
        //HBox linhaEmail = new HBox(10);
        //linhaEmail.getChildren().addAll(lblEmail, txtEmail);

        Label lblDdd = new Label("Ddd:");
        txtDdd = new TextField();
        txtDdd.setMaxWidth(150);
        txtDdd.textProperty().addListener((observable, valorAntigo, valorNovo) -> {
        
            if (valorNovo.length() > 2) {
            txtDdd.setText(valorAntigo);
        }   

        if (!valorNovo.matches("\\d*")) {
            txtDdd.setText(valorNovo.replaceAll("[^\\d]", ""));
        }
         });

        Label lblTelefone = new Label("Telefone:");
        txtTelefone = new TextField();
        txtTelefone.setMaxWidth(150);
        txtTelefone.textProperty().addListener((observable, valorAntigo, valorNovo) -> {
            if (valorNovo.length() > 9) {
            txtTelefone.setText(valorAntigo);
        }   

        if (!valorNovo.matches("\\d*")) {
            txtTelefone.setText(valorNovo.replaceAll("[^\\d]", ""));
        }
         });
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

         gridDados.add(lblDdd, 0, 5);
        gridDados.add(txtDdd, 1, 5);

        gridDados.add(lblTelefone, 0, 6);
        gridDados.add(txtTelefone, 1, 6);

        GridPane gridEndereco = new GridPane();
        gridEndereco.setHgap(10);
        gridEndereco.setVgap(15);

        Label lblSubTituloEndereco = new Label("Endereço");
        lblSubTituloEndereco.getStyleClass().add("subtitulo");

        //HBox linhaSubTituloEndereco = new HBox(10);
        //linhaSubTituloEndereco.getChildren().add(lblSubTituloEndereco);

        Label lblBairro = new Label("Bairro:");
        txtBairro = new TextField();
        txtBairro.setMaxWidth(150);
        //HBox linhaBairro = new HBox(10);
        //linhaBairro.getChildren().addAll(lblBairro, txtBairro);

        Label lblRua = new Label("Rua:");
        txtRua = new TextField();
        txtRua.setMaxWidth(150);
       // HBox linhaRua = new HBox(10);
        //linhaRua.getChildren().addAll(lblRua, txtRua);

        Label lblNumero = new Label("Numero:");
        txtNumero = new TextField();
        txtNumero.setMaxWidth(150);
        txtNumero.textProperty().addListener((observable, valorAntigo, valorNovo) -> {

        if (!valorNovo.matches("\\d*")) {
            txtNumero.setText(valorNovo.replaceAll("[^\\d]", ""));
        }
         });
        //HBox linhaNumero = new HBox(10);
        //linhaNumero.getChildren().addAll(lblNumero, txtNumero);

        Label lblCep = new Label("Cep:");
        txtCep = new TextField();
        txtCep.setMaxWidth(150);
        txtCep.textProperty().addListener((observable, valorAntigo, valorNovo) -> {

        if (valorNovo.length() > 8) {
            txtCep.setText(valorAntigo);
        }   

        if (!valorNovo.matches("\\d*")) {
            txtCep.setText(valorNovo.replaceAll("[^\\d]", ""));
        }
         });
        //HBox linhaCep = new HBox(10);
        //linhaCep.getChildren().addAll(lblCep, txtCep);

        Label lblComplemento = new Label("Complemento:");
        areaComplemento = new TextArea();
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

        GridPane gridPets = new GridPane();
        gridPets.setVgap(15);
        gridPets.setHgap(10);

        Label lblPet = new Label("Pets Cadastrados");
        lblPet.getStyleClass().add("subtitulo");
        GridPane.setHalignment(lblPet, javafx.geometry.HPos.CENTER);
        gridPets.add(lblPet, 0, 0);

        for(Pet pet : c1.getPet()){
            int num = c1.getPet().indexOf(pet) + 1;
            gridPets.add(new Label("Pet " + num + ": " + pet.getNome()), 0, num);
        }

        Button btnCadastrar = new Button("Novo Pet");

        gridPets.add(btnCadastrar, 0, gridPets.getRowCount());
        GridPane.setHalignment(btnCadastrar, javafx.geometry.HPos.CENTER);

        Button btnRemover = new Button("Remover Pet");
        gridPets.add(btnRemover, 1, gridPets.getRowCount() - 1);
        
        HBox cadastro = new HBox(100);
        cadastro.getChildren().addAll(gridDados, gridEndereco, gridPets);


        Arrays.asList(header, cadastro).forEach(linha -> linha.setAlignment(Pos.CENTER));

         Button btnSalvarTudo = new Button("Salvar dados do cliente");
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
                    App.inserirCena(Home.criarCena(Login.getAtendente()));
                }
            });
            
    
         });

         btnCadastrar.setOnAction(e -> {
            CadastroPet.criarCena();
            AddPet.abrirJanela();
         });

            btnRemover.setOnAction(e -> {
                RemoverPet.abrirJanela();
            });

        HBox rodape = new HBox(50);
        rodape.setAlignment(Pos.CENTER);
        
        
        rodape.setPadding(new Insets(20, 30, 30, 30)); 
        
        
        rodape.getChildren().addAll(btnSalvarTudo, btnCancelar);

        btnSalvarTudo.setOnAction(evento -> {
            
             
            if(EditarCliente.temCampoVazioEndereco()){
                EditarCliente.mostarNaTela("Faltam dados do endereço do Cliente!", "Por favor, volte na aba 'Dados do Cliente' e preencha todos os campos.");
                return;
            }else if(EditarCliente.temCampoVazioDados()){
                EditarCliente.mostarNaTela("Faltam dados pessoais do Cliente!", "Por favor, volte na aba 'Dados do Cliente' e preencha todos os campos.");
                return;
            }
            
             int idadeNum = Integer.parseInt(EditarCliente.getTxtIdade().getText());
            
            if(EditarCliente.getTxtTelefone().getLength() < 9 || EditarCliente.getTxtDdd().getLength() < 2){
                EditarCliente.mostarNaTela("Quantidade de caracteres do ddd e telefone invalido", "Por favor, preencha o campo ddd e telefone corretamente");
            }else if(idadeNum < 18 || idadeNum > 120){
                EditarCliente.mostarNaTela("Idade do cliente invalida", "Por favor, preencha o campo idade corretamente(Apenas maiores de idade podem ter cadastro)");
            }else if(EditarCliente.getTxtCpf().getLength() < 11){
                EditarCliente.mostarNaTela("Quantidade de caracteres do cpf invalido", "Por favor, preencha o campo cpf corretamente");
            }else if(EditarCliente.getTxtCep().getLength() < 8){
                EditarCliente.mostarNaTela("Quantidade de caracteres do cep invalido", "Por favor, preencha o campo cep corretamente");
            }else if(EditarCliente.emailInvalido()){
                 EditarCliente.mostarNaTela("E-mail Inválido", "Por favor, digite um e-mail no formato correto (exemplo@dominio.com).");
            }else{
                c1.setCpf(txtCpf.getText());
                c1.getEndereço().setBairro(txtBairro.getText());
                c1.getEndereço().setCep(txtCep.getText());
                c1.getTelefone().setDdd(txtDdd.getText());
                c1.setEmail(txtEmail.getText());
                c1.setIdade(Integer.parseInt(txtIdade.getText()));
                c1.setNome(txtNome.getText());
                c1.getEndereço().setNumero(Integer.parseInt(txtNumero.getText()));
                c1.getEndereço().setRua(txtRua.getText());
                c1.getTelefone().setNumero(txtTelefone.getText());
                c1.getEndereço().setComplemento(areaComplemento.getText());
               


                Alert concluido = new Alert(Alert.AlertType.INFORMATION);
                concluido.setTitle("Cadastro Finalizado");
                concluido.setHeaderText("Imprimindo Cadastro");
                //String nomes = ""; 
                /*for(Pet pet : CadastroPet.getListaPet()){
                   nomes += pet.getNome() + ", ";
                }*/
                //concluido.setContentText(c.getNome() + " seus(s) pet(s) " + nomes + "foi cadastrado com Sucesso");
                
                concluido.setContentText(exibirDados(c1));
                concluido.showAndWait();
                App.inserirCena(Home.criarCena(Login.getAtendente()));

            }
            
        });
            
        


   

        VBox vertical = new VBox(50);
        vertical.setAlignment(Pos.CENTER);
        vertical.getChildren().addAll(header, cadastro, rodape);

        String num1 = String.valueOf(c1.getIdade());
        String num2 = String.valueOf(c1.getEndereço().getNumero());
        txtCpf.setText(Home.getTxtCpf().getText());
        txtBairro.setText(c1.getEndereço().getBairro());
        txtCep.setText(c1.getEndereço().getCep());
        txtCpf.setText(c1.getCpf());
        txtDdd.setText(c1.getTelefone().getDdd());
        txtEmail.setText(c1.getEmail());
        txtIdade.setText(num1);
        txtNome.setText(c1.getNome());
        txtNumero.setText(num2);
        txtRua.setText(c1.getEndereço().getRua());
        txtTelefone.setText(c1.getTelefone().getNumero());
        areaComplemento.setText(c1.getEndereço().getComplemento());

        //Arrays.asList(txtBairro, txtCep, txtDdd, txtEmail, txtNumero, txtTelefone).forEach(e -> e.);
        Arrays.asList(txtCpf, txtIdade, txtNome).forEach(e -> e.setEditable(false));
        Arrays.asList(txtCpf, txtIdade, txtNome).forEach(e -> e.getStyleClass().add("naoEditavel"));
        

        return vertical;
    }

    public static Cliente pegarDados(List<Pet> p){
    String nome = txtNome.getText();
    int idade = Integer.parseInt(txtIdade.getText());
    String cpf = txtCpf.getText();
    String email = txtEmail.getText();
    String telefone = txtTelefone.getText();
    String ddd = txtDdd.getText();
    String bairro = txtBairro.getText();
    String rua = txtRua.getText();
    int numero = Integer.parseInt(txtNumero.getText());
    String cep = txtCep.getText();
    String complemento = areaComplemento.getText();
    Endereço e = new Endereço(bairro, rua, numero, cep, complemento);
    Telefone t = new Telefone(ddd, telefone);
    
    
    return new Cliente(nome, idade, cpf, e, t, email, p);
    }

    public static TextArea getAreaComplemento() {
        return areaComplemento;
    }
    public static TextField getTxtBairro() {
        return txtBairro;
    }
    public static TextField getTxtCep() {
        return txtCep;
    }
    public static TextField getTxtCpf() {
        return txtCpf;
    }
    public static TextField getTxtEmail() {
        return txtEmail;
    }
    public static TextField getTxtIdade() {
        return txtIdade;
    }
    public static TextField getTxtNome() {
        return txtNome;
    }
    public static TextField getTxtNumero() {
        return txtNumero;
    }
    public static TextField getTxtRua() {
        return txtRua;
    }
    public static TextField getTxtTelefone() {
        return txtTelefone;
    }
    public static TextField getTxtDdd() {
        return txtDdd;
    }

    public static void mostarNaTela(String header, String texto){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText(header);
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

    public static boolean temCampoVazioEndereco(){
        if(txtBairro.getText().trim().isEmpty() || txtCep.getText().trim().isEmpty() || txtNumero.getText().trim().isEmpty() || txtRua.getText().trim().isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean temCampoVazioDados(){
        if(txtCpf.getText().trim().isEmpty() || txtDdd.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty() || txtIdade.getText().trim().isEmpty() || txtNome.getText().trim().isEmpty() || txtTelefone.getText().trim().isEmpty()){
            return true;
        }
        return false;
    }


    public static boolean emailInvalido(){
        String emailDigitado = txtEmail.getText().trim();
        String regraEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    
    if (!emailDigitado.matches(regraEmail)) {
        return true;
    }
    return false;
    }
     public static String exibirDados(Cliente c){
        String exibirDados = "";
                exibirDados = c.exibeDados();
                for(Pet pet : c.getPet()){
                  exibirDados += "\nPet "+ (c.getPet().indexOf(pet) + 1) + pet.exibePet();
                }
                return exibirDados;
    }
    public static Cliente getC() {
        return c;
    }
}
