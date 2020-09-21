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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Pichau
 */
public class ExcluirClienteController implements Initializable {
    
    int clientId;
    
    public void getClientIdEx(int clientId){
        this.clientId = clientId;
    }
    
    @FXML
    Button confirmar, voltarExB;
    
    @FXML
    Label confL;
    
    @FXML
    public void voltarEx(){
        Stage stage = (Stage) voltarExB.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void confirmarE(){
        Connection c = null;
        Statement stmt = null;
         try{
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
             c.setAutoCommit(false);
             System.out.println("Opened database successfully");
             
             stmt = c.createStatement();
             
             String sql1 = "DELETE FROM CLIENTE WHERE ID_CLIENTE = '"+clientId+"';";
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
         
        Stage stage = (Stage) confirmar.getScene().getWindow();
        stage.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
