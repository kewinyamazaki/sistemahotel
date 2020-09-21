/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Pichau
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public void cadastrarCliente(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cadastrarCliente.fxml"));
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
    
    @FXML
    public void pesquisarCliente(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pesquisarCliente.fxml"));
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

    @FXML
    public void sair(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
