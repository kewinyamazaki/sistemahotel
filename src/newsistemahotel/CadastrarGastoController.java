/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
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
public class CadastrarGastoController implements Initializable {

    int clientId;    
    
    public void getClientId(int clientId){
        this.clientId = clientId;
    }
    
    @FXML
    TextField valor, dataHora, nomeGasto;
    
    @FXML
    Button cadastrarButton, voltarCG;
    
    @FXML
    public void voltarCadastrarGasto(){
        Stage stage = (Stage) voltarCG.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void cadastrarGasto(){
        
        Connection c = null;
        Statement stmt = null;
        
        try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");
                
                stmt = c.createStatement();

                //Stage stage = (Stage) button.getScene().getWindow();
                //stage.close();
                String sql = "INSERT INTO GASTOS (ID_CLIENTE,PRODUTO,PRECO) VALUES ("+clientId+",'"+
                nomeGasto.getText()+"','"+valor.getText()+"');";
                stmt.executeUpdate(sql);
                System.out.println("I've got here!");
                stmt.close();
                c.commit();
                c.close();
                
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cadastroSuccess.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch(Exception e) {
                    System.out.println("Can not open window!");
                }

        }catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.out.println("Something went wrong!");
        }
        
        //...
        Stage stage = (Stage) cadastrarButton.getScene().getWindow();
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
