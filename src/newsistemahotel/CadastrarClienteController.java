/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;

/**
 * FXML Controller class
 *
 * @author Pichau
 */
public class CadastrarClienteController implements Initializable {

    @FXML
    TextField nome, rg, endereco, telefone, data_entrada, data_saida, tipo, descricao;
    
    @FXML
    Button button, cancelar;
    
    String rgClient;
    
    @FXML
    public void cancelar(ActionEvent event){
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void cadastrar(ActionEvent event){
        this.rgClient = rg.getText();
        
        //checkClient();
        
        Connection c = null;
        Statement stmt = null;
        
        try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         
         stmt = c.createStatement();
         
             String sql1 = "SELECT COUNT(*) FROM CLIENTE WHERE RG = '"+rgClient+"';";
             ResultSet rs = stmt.executeQuery(sql1);
             
             int count    = rs.next() ? rs.getInt(1) : 0;

             if(count != 0){
                 try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clientExists.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setScene(new Scene(root1));  
                    stage.show();
                } catch(Exception e) {
                    System.out.println("Can not open window!");
                }
             }else{
                 //Stage stage = (Stage) button.getScene().getWindow();
                 //stage.close();
                 String sql = "INSERT INTO CLIENTE (NOME,RG,ENDERECO,TELEFONE,DATA_ENTRADA_RESERVA,DATA_SAIDA_RESERVA,TIPO_ACOMODACAO,DESCRICAO_ACOMODACAO) VALUES ('"+
                 nome.getText()+"','"+rg.getText()+"','"+endereco.getText()+"','"+telefone.getText()+
                 "','"+data_entrada.getText()+"','"+data_saida.getText()+"','"+tipo.getText()+
                 "','"+descricao.getText()+"');";
         
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
                c.close();
         
                try {
                   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clienteSuccess.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setScene(new Scene(root1));  
                    stage.show();
                } catch(Exception e) {
                    System.out.println("Can not open window!");
                }
             }
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.out.println("Something went wrong!");
      }
        // ...
        
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
