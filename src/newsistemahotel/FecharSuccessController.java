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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pichau
 */
public class FecharSuccessController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button fecharB;
    
    @FXML
    public void fechar(){
        Stage stage = (Stage) fecharB.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
