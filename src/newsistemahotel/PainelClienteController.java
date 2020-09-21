/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pichau
 */
public class PainelClienteController implements Initializable {
    
    ArrayList<String> pP = new ArrayList<String>();
    ArrayList<String> pN = new ArrayList<String>();
    
    ObservableList<Person> gastos;
    
    //atributos de gasto
    String nomeGasto, precoGasto;
    
    int clientId, somaTotal = 0;
    
    ArrayList<Integer> gastosInt = new ArrayList<Integer>();
    
    @FXML
    TableColumn gastoNome, gastoPreco;
    
    @FXML
    TableView<Person> tabelaGastosCliente;
    
    
    @FXML
    Label nomeCliente, rgCliente, tipoAcomodacao, enderecoCliente, descricao, dataEntrada, dataSaida, telefoneCliente, idCliente;
    
    String nomeCliente1, rgCliente1, tipo_acomodacao1, endereco1, descricao1, data_entrada1, data_saida1, telefone1,idString;
    
    public void getVariables(int clientId,String nomeCliente1,String rgCliente1, String tipo_acomodacao1, String endereco1, 
            String descricao1, String data_entrada1, String data_saida1, String telefone1){
            idString = Integer.toString(clientId);
            idCliente.setText(idString);
            this.clientId = clientId;
            this.nomeCliente1 = nomeCliente1;
            nomeCliente.setText(nomeCliente1);
            this.rgCliente1 = rgCliente1;
            rgCliente.setText(rgCliente1);
            this.tipo_acomodacao1 = tipo_acomodacao1;
            tipoAcomodacao.setText(tipo_acomodacao1);
            this.endereco1 = endereco1;
            enderecoCliente.setText(endereco1);
            this.descricao1 = descricao1;
            descricao.setText(descricao1);
            this.data_entrada1 = data_entrada1;
            dataEntrada.setText(data_entrada1);
            this.data_saida1 = data_saida1;
            dataSaida.setText(data_saida1);
            this.telefone1 = telefone1;
            telefoneCliente.setText(telefone1);
    }
    
    @FXML
    Button voltar;
    
    @FXML
    public void refreshCliente(){
        Connection c = null;
        Statement stmt = null;
        
        try{
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
             c.setAutoCommit(false);
             System.out.println("Opened database successfully");
             
             stmt = c.createStatement();
             
             String sql2 = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = '"+clientId+"';";
             ResultSet rs2 = stmt.executeQuery(sql2);
             
             while(rs2.next()){
                nomeCliente.setText(rs2.getString("nome"));
                rgCliente.setText(rs2.getString("rg"));
                tipoAcomodacao.setText(rs2.getString("tipo_acomodacao"));
                enderecoCliente.setText(rs2.getString("endereco"));
                descricao.setText(rs2.getString("descricao_acomodacao"));
                dataEntrada.setText(rs2.getString("data_entrada_reserva"));
                dataSaida.setText(rs2.getString("data_saida_reserva"));
                telefoneCliente.setText(rs2.getString("telefone"));
             }
             rs2.close();
             stmt.close();
             c.commit();
             c.close();
             
        }catch(Exception e){
             System.out.println("Something went wrong on refreshCliente()");
        }
        
    }
    
    @FXML
    public void refresh(){
     // select + update na tabela de gastos e nos dados do cliente
     
     //1) atualiza tabela gasto do cliente
     Connection c = null;
     Statement stmt = null;
     
     int gasto;
     
     gastosInt.removeAll(gastosInt);
     somaTotal = 0;
     pP.removeAll(pP);
     pN.removeAll(pN);
     try{
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
             c.setAutoCommit(false);
             System.out.println("Opened database successfully");
             
             stmt = c.createStatement();
             
             String sql1 = "SELECT * FROM GASTOS WHERE ID_CLIENTE = '"+clientId+"';";
             ResultSet rs1 = stmt.executeQuery(sql1);
             
             tabelaGastosCliente.getItems().clear();
             
             //
             gastos.add(new Person("Estadia","150"));
             gastosInt.add(Integer.parseInt("150"));
             
             while(rs1.next()){
                 gastos.add(new Person(rs1.getString("produto"),rs1.getString("preco")));
                 ////
                 gasto = Integer.parseInt(rs1.getString("preco"));
                 gastosInt.add(gasto);
                 //here
                 pP.add(rs1.getString("produto"));
                 pN.add(rs1.getString("preco"));
             }
             rs1.close();
             stmt.close();
             c.commit();
             c.close();
             
     }catch(Exception e){
         System.out.println("Something went wrong on refresh()");
     }

     
    }
    
    @FXML
    public void voltar(){
        Stage stage = (Stage) voltar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void cadastrarGasto(){
        try {
            FXMLLoader painelCadastrarGasto = new FXMLLoader(getClass().getResource("cadastrarGasto.fxml"));
            //Parent root1 = (Parent) fxmlLoader.load();
            Pane ap = painelCadastrarGasto.load();
            CadastrarGastoController controller = painelCadastrarGasto.getController();
            controller.getClientId(clientId);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(new Scene(ap));  
            stage.show();
        } catch(Exception e) {
            System.out.println("Can not open window!");
        }
        
        //refresh to update the table gastos
    }
    
    @FXML
    public void excluirCliente(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("excluirCliente.fxml"));
            //Parent root1 = (Parent) fxmlLoader.load();
            Pane ap = fxmlLoader.load();
            ExcluirClienteController controller = fxmlLoader.getController();
            controller.getClientIdEx(this.clientId);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(new Scene(ap));  
            stage.show();
        } catch(Exception e) {
            System.out.println("Can not open window!");
        }
        //drop cliente and gasto (não excluir sem fecar conta)
    }
    
    @FXML
    public void alterarDados(){
        try {
            FXMLLoader painelAlterar = new FXMLLoader(getClass().getResource("alterarDados.fxml"));
            //Parent root1 = (Parent) fxmlLoader.load();
            Pane ap = painelAlterar.load();
            AlterarDadosController controller = painelAlterar.getController();
            controller.getClientIdA(this.clientId);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(new Scene(ap));  
            stage.show();
        } catch(Exception e) {
            System.out.println("Can not open window!");
        }
        //update
    }
    
    @FXML
    public void fecharConta(){
        try {
            this.somaTotal = 0;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fecharConta.fxml"));
            //Parent root1 = (Parent) fxmlLoader.load();
            Pane ap = fxmlLoader.load();
            FecharContaController controller = fxmlLoader.getController();
            controller.getClientIdF(this.clientId);
            
            for(int i=0;i<gastosInt.size();i++){
                this.somaTotal += gastosInt.get(i);
            }
            
            controller.getGastoTotal(this.somaTotal);
            controller.getHashPP(this.pP,this.pN);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(new Scene(ap));  
            stage.show();
        } catch(Exception e) {
            System.out.println("Can not open window!");
        }
        //drop gasto
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gastoNome.setCellValueFactory( new PropertyValueFactory<>("firstName"));
        gastoPreco.setCellValueFactory( new PropertyValueFactory<>("lastName"));
        gastos = FXCollections.observableArrayList(new Person("Estadia", "150"));
        tabelaGastosCliente.setItems(gastos);
    }    
    
    //
    
    public static class Person {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
 
        private Person(String fName, String lName) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
    }
    
}
