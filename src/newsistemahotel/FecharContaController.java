/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author Pichau
 */
public class FecharContaController implements Initializable {
    
    ArrayList<String> pP = new ArrayList<String>();
    ArrayList<String> pN = new ArrayList<String>();
    
    String pP1, pN1;
    
    ObservableList<Person> gastos2;
    
    public void getHashPP(ArrayList<String> pP, ArrayList<String> pN){
        this.pP = pP;
        this.pN = pN;
        for(int i=0;i<pP.size();i++){
            System.out.println(pP.get(i)+" : "+pN.get(i));
        }
    }
    
    @FXML
    TableView<Person> tableFechar;
    
    @FXML
    TableColumn fecharProduto, fecharPreco;
    
    @FXML
    Label totalConta;
    
    @FXML
    Button voltarFeB;
    
    @FXML
    public void voltarFe(){
        Stage stage = (Stage) voltarFeB.getScene().getWindow();
        stage.close();
    }
    
    int clientId, total;
    
    @FXML
    public void refresh(){
        tableFechar.getItems().clear();
        gastos2.add(new Person("Estadia","150"));
        for(int i=0;i<pP.size();i++){
            pP1 = pP.get(i);
            pN1 = pN.get(i);
            gastos2.add(new Person(pP1,pN1));
        }
    }
    
    public void getClientIdF(int clientId){
        this.clientId = clientId;
    }
    
    public void getGastoTotal(int total){
        this.total = total;
        totalConta.setText(Integer.toString(total));
    }
    
    @FXML
    Button fecharButton;
    
    @FXML
    public void fechar(){
        Connection c = null;
        Statement stmt = null;
         try{
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
             c.setAutoCommit(false);
             System.out.println("Opened database successfully");
             
             stmt = c.createStatement();
             
             String sql1 = "DELETE FROM GASTOS WHERE ID_CLIENTE = '"+clientId+"';";
             stmt.executeUpdate(sql1);
             stmt.close();
             c.commit();
             c.close();
             
             try {
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fecharSuccess.fxml"));
                 Parent root1 = (Parent) fxmlLoader.load();
                 Stage stage = new Stage();
                 stage.initModality(Modality.APPLICATION_MODAL);
                 stage.initStyle(StageStyle.DECORATED);
                 stage.setScene(new Scene(root1));  
                 stage.show();
            } catch(Exception e) {
                System.out.println("Can not open window!");
            }
             
        }catch(Exception e){
             System.out.println("Something went wrong on refresh()");
        }
        
        //
        Stage stage = (Stage) fecharButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fecharProduto.setCellValueFactory( new PropertyValueFactory<>("firstName"));
        fecharPreco.setCellValueFactory( new PropertyValueFactory<>("lastName"));
        gastos2 = FXCollections.observableArrayList(new Person("Estadia", "150"));
        tableFechar.setItems(gastos2);
    }
    
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
